package cn.xilikeli.skyblog.common.configuration;

import cn.xilikeli.skyblog.common.log.MDCAccessServletFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 公用配置类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/23 - 13:48
 * @since JDK1.8
 */
@Configuration(proxyBeanMethods = false)
public class CommonConfiguration {

    /**
     * 用于将 request 相关信息（如请求 url）放入 MDC 中供日志使用
     * 摘自 lin-cms-springboot: https://github.com/TaleLin/lin-cms-spring-boot/blob/master/src/main/java/io/github/talelin/latticy/common/configuration/CommonConfiguration.java
     *
     * @return Logback 的 MDCInsertingServletFilter
     */
    @Bean
    public FilterRegistrationBean<MDCAccessServletFilter> mdcInsertingServletFilter() {
        FilterRegistrationBean<MDCAccessServletFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        MDCAccessServletFilter mdcAccessServletFilter = new MDCAccessServletFilter();
        filterRegistrationBean.setFilter(mdcAccessServletFilter);
        filterRegistrationBean.setName("mdc-access-servlet-filter");
        return filterRegistrationBean;
    }

}