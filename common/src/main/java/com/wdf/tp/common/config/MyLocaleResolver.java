package com.wdf.tp.common.config;

import com.wdf.tp.common.utils.LocaleUtil;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * 自定义语言解析器.
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 14:39
 * @since 1.0.0
 */
public class MyLocaleResolver extends AcceptHeaderLocaleResolver {

    @Override
    @Nonnull
    public Locale resolveLocale(HttpServletRequest request) {
        return LocaleUtil.matchedLocale(request.getHeader("Accept-Language"), Locale.CHINESE);
    }
}
