package cn.xilikeli.skyblog.vo;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 分页数据统一 VO,带有转换为目标类型的分页数据的功能
 * </p>
 *
 * @param <S> 源数据类型
 * @param <T> 目标数据类型
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/19 - 19:16
 * @since JDK1.8
 */
@Getter
@Setter
@ToString
public class PagingDozerVO<S, T> extends PagingVO {

    /**
     * 构造函数
     * 将查询出来的源数据分页对象构造成目标类型的分页数据视图对象
     *
     * @param pageS  源数据分页对象
     * @param classT 要转换的目标类型
     */
    @SuppressWarnings("unchecked")
    public PagingDozerVO(Page<S> pageS, Class<T> classT) {
        // 初始化基本参数
        this.initPageParameters(pageS);

        // 获取源数据,转换为目标类型数据
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<S> sourceList = pageS.getContent();
        List<T> targetList = new ArrayList<>();
        sourceList.forEach(s -> {
            T targetVO = mapper.map(s, classT);
            targetList.add(targetVO);
        });

        this.setItems(targetList);
    }

}