package cn.xilikeli.skyblog.controller.v1;

import cn.xilikeli.skyblog.common.UnifyResponse;
import cn.xilikeli.skyblog.common.constant.CodeMessageConstant;
import cn.xilikeli.skyblog.common.interceptor.ScopeLevel;
import cn.xilikeli.skyblog.dto.leavemessage.LeaveMessageDTO;
import cn.xilikeli.skyblog.service.LeaveMessageService;
import cn.xilikeli.skyblog.vo.LeaveMessageDetailVO;
import cn.xilikeli.skyblog.vo.PagingVO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * <p>
 * 留言业务接口控制器
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 22:01
 * @since JDK1.8
 */
@RequestMapping("/v1/leave-message")
@Validated
@RestController
@AllArgsConstructor
public class LeaveMessageController {

    private final LeaveMessageService leaveMessageService;

    /**
     * 根据分页查询参数 page、count 获取当前页的留言列表
     *
     * @param page  当前数据索引
     * @param count 每页的数据条数
     * @return 返回封装着获取的留言列表的分页视图对象
     */
    @GetMapping("/latest")
    public PagingVO<LeaveMessageDetailVO> getLatestLeaveMessageList(@RequestParam(name = "page", required = false, defaultValue = "0")
                                                                    @Min(value = 0, message = "{page.page.min}") Integer page,
                                                                    @RequestParam(name = "count", required = false, defaultValue = "2")
                                                                    @Min(value = 1, message = "{page.count.min}")
                                                                    @Max(value = 30, message = "{page.count.max}") Integer count) {
        // 查询出源数据分页对象
        Page<LeaveMessageDetailVO> leaveMessagePage = this.leaveMessageService.getLatestLeaveMessageList(page, count);
        // 转换为目标类型的分页视图对象返回
        return new PagingVO<>(leaveMessagePage);
    }

    /**
     * 获取所有留言
     *
     * @return 返回所有留言组成的列表
     */
    @GetMapping("/list")
    public List<LeaveMessageDetailVO> getAllLeaveMessageList() {
        return this.leaveMessageService.getAllLeaveMessageList();
    }

    /**
     * 添加一个留言
     *
     * @param leaveMessageDTO 留言的数据传输对象
     */
    @PostMapping("")
    @ScopeLevel
    public void createLeaveMessage(@RequestBody @Validated LeaveMessageDTO leaveMessageDTO) {
        this.leaveMessageService.createLeaveMessage(leaveMessageDTO);
        UnifyResponse.createSuccess(CodeMessageConstant.CREATE_LEAVE_MESSAGE_SUCCESS);
    }

}