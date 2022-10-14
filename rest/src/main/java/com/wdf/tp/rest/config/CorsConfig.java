package com.wdf.tp.rest.config;

import com.wdf.tp.common.config.YamlPropertySourceFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import java.util.List;

/**
 * 跨域配置
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 13:41
 * @since 1.0.0
 */
@Configuration
@PropertySource(value = "classpath:cors.yaml", factory = YamlPropertySourceFactory.class)
@ConditionalOnProperty(name = "switch.service.cors.enabled", havingValue = "true")
@ConfigurationProperties(prefix = "cors")
@Slf4j
@Data
public class CorsConfig implements WebMvcConfigurer {

    /** 跨域配置列表 */
    private List<ConfigItem> configs;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        configs.forEach(item -> registry
            .addMapping(item.pattern).allowedOrigins(item.allowedOrigins.toArray(new String[0])));
    }

    @PostConstruct
    public void init() {
        log.warn("cors configs: {}", configs);
    }

    /**
     * 放行OPTIONS的跨域先验请求
     *
     * @return filter
     */
    @Bean
    public FilterRegistrationBean<Filter> corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedOriginPattern("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new FilterRegistrationBean<>(new CorsFilter(source));
    }

    /**
     * 配置项
     */
    @Data
    public static class ConfigItem {
        /** url patten */
        private String pattern;
        /** 允许跨域的域名 */
        private List<String> allowedOrigins;
    }
}
