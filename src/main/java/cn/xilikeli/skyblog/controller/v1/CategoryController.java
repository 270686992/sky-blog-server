package cn.xilikeli.skyblog.controller.v1;

import cn.xilikeli.skyblog.model.Category;
import cn.xilikeli.skyblog.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

/**
 * <p>
 * 文章分类业务接口控制器
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/21 - 21:22
 * @since JDK1.8
 */
@RequestMapping("/v1/category")
@RestController
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 获取所有文章分类
     *
     * @return 返回包含所有文章分类的列表
     */
    @GetMapping("/list")
    public List<Category> getAllCategory() {
        return this.categoryService.getAllCategory();
    }

    /**
     * 根据文章分类 ID 获取相应文章分类的信息
     *
     * @param categoryId 文章分类 ID
     * @return 返回获取的文章分类的信息
     */
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable(name = "id") @Positive(message = "{id.positive}") Integer categoryId) {
        return this.categoryService.getCategoryById(categoryId);
    }

}