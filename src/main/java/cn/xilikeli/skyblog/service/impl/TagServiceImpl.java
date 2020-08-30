package cn.xilikeli.skyblog.service.impl;

import cn.xilikeli.skyblog.model.Tag;
import cn.xilikeli.skyblog.repository.TagRepository;
import cn.xilikeli.skyblog.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 标签业务操作接口类的实现类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 21:28
 * @since JDK1.8
 */
@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> getAllTag() {
        return this.tagRepository.findAll();
    }

}