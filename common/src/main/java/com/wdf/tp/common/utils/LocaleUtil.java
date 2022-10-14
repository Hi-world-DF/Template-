package com.wdf.tp.common.utils;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Locale;

/**
 * 语言工具类
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 14:39
 * @since 1.0.0
 */
public final class LocaleUtil {

    /** 支持的语言 */
    private static final List<Locale> SUPPORT_LOCALES = ImmutableList.of(
        new Locale("en"),
        new Locale("en", "US"),
        new Locale("zh"),
        new Locale("zh", "CN")
    );

    /**
     * 根据Accept-Language获取最匹配的语言
     *
     * @param acceptLanguage 客户端语言
     * @return 匹配语言
     */
    public static Locale matchedLocale(String acceptLanguage, Locale defaultLocale) {
        if (StringUtils.isBlank(acceptLanguage)) {
            return defaultLocale;
        }
        final List<Locale.LanguageRange> ranges = Locale.LanguageRange.parse(acceptLanguage);
        Locale locale = Locale.lookup(ranges, SUPPORT_LOCALES);
        return null != locale ? locale : defaultLocale;
    }

    private LocaleUtil() {}
}
