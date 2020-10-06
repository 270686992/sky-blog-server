package cn.xilikeli.skyblog.service.impl;

import cn.xilikeli.skyblog.common.constant.CodeMessageConstant;
import cn.xilikeli.skyblog.common.exception.http.NotFoundException;
import cn.xilikeli.skyblog.model.Category;
import cn.xilikeli.skyblog.repository.CategoryRepository;
import cn.xilikeli.skyblog.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章分类业务操作接口类的实现类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 21:19
 * @since JDK1.8
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return this.categoryRepository.findAllCategory();
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        Category category = this.categoryRepository.findOneById(categoryId);

        if (category == null) {
            throw new NotFoundException(CodeMessageConstant.NOT_FOUND_CATEGORY);
        }

        return category;
    }

}