package com.wdf.tp.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

/**
 * Locale Config
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 14:34
 * @since 1.0.0
 */
@Configuration
public class LocaleConfig {

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new MyLocaleResolver();
        resolver.setDefaultLocale(Locale.CHINESE);
        return resolver;
    }
}
