package com.wdf.tp.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * url编码工具
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 11:55
 * @since 1.0.0
 */
@Slf4j
public final class UrlCodeUtil {
    private static final String UTF_8 = "UTF-8";

    private UrlCodeUtil() {
    }

    /**
     * url encode
     *
     * @param str url编码前的字符串
     * @return url编码后的字符串
     */
    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, UTF_8);
        } catch (UnsupportedEncodingException e) {
            log.error("UTF-8 encoding is not supported.", e);
        }
        return StringUtils.EMPTY;
    }

    /**
     * url decode
     *
     * @param str url解码前的字符串
     * @return url解码后的字符串
     */
    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, UTF_8);
        } catch (UnsupportedEncodingException e) {
            log.error("UTF-8 decoding is not supported.", e);
        }
        return StringUtils.EMPTY;
    }
}
