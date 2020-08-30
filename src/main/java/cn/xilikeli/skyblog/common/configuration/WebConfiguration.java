package cn.xilikeli.skyblog.common.configuration;

import cn.xilikeli.skyblog.common.interceptor.PermissionInterceptor;
import cn.xilikeli.skyblog.common.limit.LimitInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * Spring MVC 配置类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/8/22 - 17:43
 * @since JDK1.8
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor getPermissionInterceptor() {
        // 权限拦截器
        return new PermissionInterceptor();
    }

    @Bean
    public LimitInterceptor getLimitInterceptor() {
        // 接口限流拦截器
        return new LimitInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册权限拦截器
        registry.addInterceptor(this.getPermissionInterceptor());
        // 注册接口限流拦截器
        registry.addInterceptor(this.getLimitInterceptor());
    }

    /**
     * 跨域
     * 注意： 跨域问题涉及安全性问题，这里提供的是最方便简单的配置，任何host和任何方法都可跨域
     * 但在实际场景中，这样做，无疑很危险，所以谨慎选择开启或者关闭
     * 如果切实需要，请咨询相关安全人员或者专家进行配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

}