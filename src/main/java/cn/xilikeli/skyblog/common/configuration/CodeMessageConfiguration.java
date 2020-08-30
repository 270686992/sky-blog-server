package cn.xilikeli.skyblog.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 消息码配置类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/20 - 10:53
 * @since JDK1.8
 */
@ConfigurationProperties(prefix = "sky")
@PropertySource("classpath:config/code-message.properties")
@Component
public class CodeMessageConfiguration {

    /**
     * 用于存储配置文件中的 code 码和提示信息
     * 变量名称 codes 需要和配置文件中保持一致,将会自动注入键值对到这个集合中
     */
    private Map<Integer, String> codes = new HashMap<>();

    /**
     * 通过 code 码获取提示信息
     *
     * @param code 消息码
     * @return 返回 code 码对应的提示信息
     */
    public String getMessageByCode(int code) {
        return codes.get(code);
    }

    public Map<Integer, String> getCodes() {
        return codes;
    }

    public void setCodes(Map<Integer, String> codes) {
        this.codes = codes;
    }

}