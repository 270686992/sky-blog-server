package cn.xilikeli.skyblog.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <p>
 * JSON 转换工具类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/23 - 23:32
 * @since JDK1.8
 */
@Slf4j
public class JsonUtil {

    /**
     * 定义 jackson 对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成 json 字符串。
     *
     * @param object 要转换为 json 字符串的对象
     * @return 返回对象转换之后的 json 字符串
     */
    public static String objectToJson(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("JsonUtil-objectToJson-转换对象为 json 数据时出现异常: ", e);
        }
        return null;
    }

    /**
     * 将 json 数据转化为对象
     *
     * @param jsonData json 数据
     * @param beanType 对象的类型
     * @return 返回 json 数据转换之后的对象
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
            log.error("JsonUtil-jsonToPojo-转换 json 数据为对象时出现异常: ", e);
        }
        return null;
    }

    /**
     * 将 json 数据转换成对象 List
     *
     * @param jsonData json 数据
     * @param beanType 对象的类型
     * @return 返回 json 数据转换之后的对象 List
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return MAPPER.readValue(jsonData, javaType);
        } catch (Exception e) {
            log.error("JsonUtil-jsonToList-转换 json 数据为对象 List 时出现异常: ", e);
        }
        return null;
    }

}