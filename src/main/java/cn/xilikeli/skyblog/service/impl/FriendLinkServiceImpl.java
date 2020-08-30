package cn.xilikeli.skyblog.service.impl;

import cn.xilikeli.skyblog.model.FriendLink;
import cn.xilikeli.skyblog.repository.FriendLinkRepository;
import cn.xilikeli.skyblog.service.FriendLinkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 友情链接业务操作接口类的实现类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 21:34
 * @since JDK1.8
 */
@Service
@AllArgsConstructor
public class FriendLinkServiceImpl implements FriendLinkService {

    private final FriendLinkRepository friendLinkRepository;

    @Override
    public List<FriendLink> getFriendLinkListByKind(Integer kind) {
        return this.friendLinkRepository.findByKind(kind);
    }

}