package cn.xilikeli.skyblog.service.impl;

import cn.xilikeli.skyblog.common.LocalCustomer;
import cn.xilikeli.skyblog.common.constant.CodeMessageConstant;
import cn.xilikeli.skyblog.common.enumeration.EmailKindEnum;
import cn.xilikeli.skyblog.common.enumeration.LeaveMessageLevelEnum;
import cn.xilikeli.skyblog.common.exception.http.FailedException;
import cn.xilikeli.skyblog.common.exception.http.ForbiddenException;
import cn.xilikeli.skyblog.common.manager.redis.RedisKeyConstant;
import cn.xilikeli.skyblog.common.manager.redis.RedisOperator;
import cn.xilikeli.skyblog.common.util.HttpRequestProxy;
import cn.xilikeli.skyblog.common.util.IpParseUtil;
import cn.xilikeli.skyblog.common.util.PageUtil;
import cn.xilikeli.skyblog.dto.leavemessage.LeaveMessageDTO;
import cn.xilikeli.skyblog.model.Customer;
import cn.xilikeli.skyblog.model.Email;
import cn.xilikeli.skyblog.model.LeaveMessage;
import cn.xilikeli.skyblog.model.LinUser;
import cn.xilikeli.skyblog.repository.CustomerRepository;
import cn.xilikeli.skyblog.repository.LeaveMessageRepository;
import cn.xilikeli.skyblog.repository.LinUserRepository;
import cn.xilikeli.skyblog.service.LeaveMessageService;
import cn.xilikeli.skyblog.service.MailService;
import cn.xilikeli.skyblog.vo.LeaveMessageDetailVO;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 留言业务操作接口类的实现类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 21:59
 * @since JDK1.8
 */
@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {

    private final LeaveMessageRepository leaveMessageRepository;

    private final LinUserRepository linUserRepository;

    private final CustomerRepository customerRepository;

    private final MailService mailService;

    private final RedisOperator redisOperator;

    /**
     * 通知邮箱
     */
    @Value("${sky-blog.inform-email}")
    private String informEmail;

    public LeaveMessageServiceImpl(LeaveMessageRepository leaveMessageRepository, LinUserRepository linUserRepository, CustomerRepository customerRepository, MailService mailService, RedisOperator redisOperator) {
        this.leaveMessageRepository = leaveMessageRepository;
        this.linUserRepository = linUserRepository;
        this.customerRepository = customerRepository;
        this.mailService = mailService;
        this.redisOperator = redisOperator;
    }

    @Override
    public Page<LeaveMessageDetailVO> getLatestLeaveMessageList(Integer page, Integer count) {
        Pageable pageable = PageRequest.of(page, count);

        // 获取所有留言
        List<LeaveMessage> allLeaveMessageList = this.leaveMessageRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        // 把 allLeaveMessageList 转换为 VO 列表
        List<LeaveMessageDetailVO> allLeaveMessageVOList = this.getLeaveMessageDetailVOList(allLeaveMessageList);

        return PageUtil.listToPage(allLeaveMessageVOList, pageable);
    }

    @Override
    public List<LeaveMessageDetailVO> getAllLeaveMessageList() {
        // 获取所有留言
        List<LeaveMessage> allLeaveMessageList = this.leaveMessageRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        // 把 allLeaveMessageList 转换为 VO 列表并返回
        return this.getLeaveMessageDetailVOList(allLeaveMessageList);
    }

    /**
     * 将留言列表转换为留言详情信息 VO 列表
     *
     * @param leaveMessageList 留言列表
     * @return 返回转换后的留言详情信息 VO 列表
     */
    private List<LeaveMessageDetailVO> getLeaveMessageDetailVOList(List<LeaveMessage> leaveMessageList) {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<LeaveMessageDetailVO> allLeaveMessageVOList = new ArrayList<>();

        leaveMessageList.forEach(leaveMessage -> {
            LeaveMessageDetailVO leaveMessageDetailVO = mapper.map(leaveMessage, LeaveMessageDetailVO.class);

            if (leaveMessage.getAdminUserId() == null) {
                Customer customer = this.customerRepository.findOneById(leaveMessage.getCustomerId());
                leaveMessageDetailVO.setNickname(customer.getNickname());
                leaveMessageDetailVO.setAvatar(customer.getAvatar());
                leaveMessageDetailVO.setEmail(customer.getEmail());
            } else {
                LinUser linUser = this.linUserRepository.findOneById(leaveMessage.getAdminUserId());
                leaveMessageDetailVO.setNickname(linUser.getNickname());
                leaveMessageDetailVO.setAvatar(linUser.getAvatar());
                leaveMessageDetailVO.setEmail(linUser.getEmail());
            }

            allLeaveMessageVOList.add(leaveMessageDetailVO);
        });

        return allLeaveMessageVOList;
    }

    @Override
    public void createLeaveMessage(LeaveMessageDTO leaveMessageDTO) {
        // 校验留言的数据传输对象中的数据是否符合规则
        this.checkLeaveMessageDTO(leaveMessageDTO);

        // 获取 IP 并解析为相应地址
        String ip = HttpRequestProxy.getRemoteRealIp();
        String address = IpParseUtil.getAddressByIp(ip);

        // 获取当前登录用户
        Customer customer = LocalCustomer.getCustomer();

        // 缓存 key
        String createLeaveMessageKey = RedisKeyConstant.CREATE_LEAVE_MESSAGE_KEY_PREFIX + customer.getId();
        // 缓存 key 对应的缓存内容
        String leaveMessageRecord = this.redisOperator.get(createLeaveMessageKey);

        if (StringUtils.isBlank(leaveMessageRecord)) {
            // 缓存中没有留言记录, 即 10s 内没有重复提交的情况
            // 添加留言
            LeaveMessage leaveMessage = new LeaveMessage();
            BeanUtils.copyProperties(leaveMessageDTO, leaveMessage);
            leaveMessage.setRoot(leaveMessageDTO.getRoot().equals(LeaveMessageLevelEnum.ROOT.getValue()));
            leaveMessage.setCustomerId(customer.getId());
            leaveMessage.setIp(ip);
            leaveMessage.setAddress(address);
            leaveMessage.setDisplayState(true);

            try {
                this.leaveMessageRepository.save(leaveMessage);
            } catch (Exception e) {
                throw new FailedException(CodeMessageConstant.CREATE_LEAVE_MESSAGE_FAILED);
            }

            // 留言成功之后,发送邮件给站长通知
            // 封装邮件信息
            Email email = Email.builder()
                    .subject("博客留言通知")
                    .emailKind(EmailKindEnum.CUSTOMER_LEAVE_MESSAGE.getValue())
                    .recipientEmail(this.informEmail)
                    .content(leaveMessage.getContent())
                    .build();
            // 发送邮件
            this.mailService.sendInformEmail(email);

            // 将数据缓存到 redis 中,过期时间 10 s
            this.redisOperator.set(createLeaveMessageKey, "提交留言", 10);
        } else {
            // 缓存中有留言记录, 即 10s 内有重复提交的情况
            throw new ForbiddenException(CodeMessageConstant.TOO_MANY_SUBMIT_LEAVE_MESSAGE);
        }
    }

    /**
     * 业务校验,校验留言的数据传输对象中的数据是否符合规则
     *
     * @param leaveMessageDTO 留言的数据传输对象
     */
    private void checkLeaveMessageDTO(LeaveMessageDTO leaveMessageDTO) {
        Integer root = leaveMessageDTO.getRoot();
        Integer rootId = leaveMessageDTO.getRootId();
        Integer parentId = leaveMessageDTO.getParentId();

        if (root == 1) {
            // 一级留言, rootId 和 parentId 应为 0
            if (rootId != 0 || parentId != 0) {
                throw new ForbiddenException(CodeMessageConstant.FORBIDDEN_CREATE_LEAVE_MESSAGE);
            }
        } else {
            // 二级留言, rootId 和 parentId 不应为 0
            if (rootId == 0 || parentId == 0) {
                throw new ForbiddenException(CodeMessageConstant.FORBIDDEN_CREATE_LEAVE_MESSAGE);
            }

            // 判断 rootId 和 parentId 是否有对应的数据
            LeaveMessage rootLeaveMessage = this.leaveMessageRepository.findOneById(rootId);
            LeaveMessage parentLeaveMessage = this.leaveMessageRepository.findOneById(parentId);

            if (rootLeaveMessage == null || parentLeaveMessage == null) {
                throw new ForbiddenException(CodeMessageConstant.FORBIDDEN_CREATE_LEAVE_MESSAGE);
            }
        }
    }

}