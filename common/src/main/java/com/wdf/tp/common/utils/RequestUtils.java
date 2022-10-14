package com.wdf.tp.common.utils;

import com.wdf.tp.common.dto.RequestInfoDto;
import com.wdf.tp.common.dto.UserFlagDto;
import com.wdf.tp.common.pojo.DeviceEnum;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Request相关工具类
 *
 * @author Zhen Wang
 * @version 1.0.2
 * @since 1.0.2
 */
public final class RequestUtils {

    private static final String CONNECTOR = "&";
    private static final String EQUAL_SIGN = "=";
    /** device id header name */
    public static final String UUID_HEADER_NAME = "X-DEVICE-ID";
    public static final String USER_AGENT_NAME = "User-Agent";
    /** 自定义部分满足 com.xgd.quyan....数字空格即可 */
    private static final Pattern REAL_UA_REGEX = Pattern.compile("^(com\\.xgd\\.quyan\\S+\\d+\\s+).+");
    private static final String AUTH_HEADER = "authorization";
    /** oss的callback中，公钥地址对应的header中的key */
    private static final String PUB_KEY_HEADER_NAME = "x-oss-pub-key-url";
    private static final AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
    private static final String APP_UA_PREFIX = "com.picso.app";
    private static final String IPHONE_DEVICE_1 = "iOS";
    private static final String IPHONE_DEVICE_2 = "iPad";
    private static final String IPHONE_DEVICE_3 = "iPhone";

    private RequestUtils() {
    }

    /**
     * 从request中提取IP。
     *
     * @param request http request
     * @return ip address
     */
    public static String getIpFromRequest(HttpServletRequest request) {
        final String xRealIp = request.getHeader("X-REAL-IP");
        final String xForwardedFor = request.getHeader("X-Forwarded-For");
        final String xForwardedForClientIp = StringUtils.substringBefore(xForwardedFor, ", ");
        return StringUtils.defaultIfBlank(StringUtils.defaultIfBlank(xRealIp, xForwardedForClientIp), request.getRemoteAddr());
    }

    /**
     * 获取user agent.
     *
     * @param request http request
     * @return user agent
     */
    public static String parseUserAgent(HttpServletRequest request) {
        return request.getHeader(USER_AGENT_NAME);
    }

    /**
     * 获取设备。UA示例: facemagic/1.2.0+2 iOS/12.4.5(iPhone) 或者 facemagic/1.2.0+2 android/8.1.0(Redmi 5 Plus)
     *
     * @param userAgent user agent
     * @return device
     */
    public static DeviceEnum parseDevice(String userAgent) {
        if (userAgent.startsWith(APP_UA_PREFIX)) {
            if (StringUtils.containsIgnoreCase(userAgent, DeviceEnum.ANDROID.name())) {
                return DeviceEnum.ANDROID;
            } else if (StringUtils.containsAnyIgnoreCase(userAgent, IPHONE_DEVICE_1, IPHONE_DEVICE_2, IPHONE_DEVICE_3)) {
                return DeviceEnum.IOS;
            }
        }
        return DeviceEnum.WEB;
    }

    /**
     * 获取uuid
     *
     * @param request http request
     * @return uuid or user hash
     */
    public static String parseUuid(HttpServletRequest request) {
        return request.getHeader(UUID_HEADER_NAME);
    }

    /**
     * 用自定义UA解析真UA
     *
     * @param userAgent 自定义UA
     * @return 真UA
     */
    public static String parseRealUserAgent(String userAgent) {
        Matcher matcher = REAL_UA_REGEX.matcher(userAgent);
        if (matcher.find()) {
            return userAgent.substring(matcher.group(1).length());
        }
        return userAgent;
    }

    /**
     * 从request中抽取信息
     *
     * @param request 请求
     * @return dto数据
     */
    public static RequestInfoDto from(HttpServletRequest request) {
        RequestInfoDto info = new RequestInfoDto();
        final String userAgent = parseUserAgent(request);
        info.setUserAgent(userAgent);
        info.setIp(getIpFromRequest(request));
        info.setDevice(parseDevice(userAgent));
        info.setUuid(parseUuid(request));
        return info;
    }

    /**
     * 解析Authorization bearer的值。中间要有空格。
     *
     * @param request http request
     * @return authorization value
     */
    public static String getAuthHeader(HttpServletRequest request) {
        final String header = request.getHeader(AUTH_HEADER);
        if (StringUtils.isBlank(header) || !header.startsWith("Bearer")) {
            return null;
        }
        return header.replace("Bearer ", "");
    }

    /**
     * 获取设备类型
     *
     * @return request
     */
    public static DeviceEnum getDevice() {
        return parseDevice(parseUserAgent(getHttpServletRequest()));
    }

    /**
     * 获取request
     *
     * @return request http request
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    /**
     * 解析locale
     *
     * @param request http request
     * @return Locale
     */
    public static Locale resolveLocale(HttpServletRequest request) {
        return localeResolver.resolveLocale(request);
    }

    public static Map<String, String> getHeaders(HttpServletRequest request) {
        if (request == null) {
            return Collections.emptyMap();
        }
        Map<String, String> headerMap = new HashMap<>(16);
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            headerMap.put(header, request.getHeader(header));
        }
        return headerMap;
    }

    /**
     * 获取UserFlagDto
     *
     * @param request HttpServletRequest
     * @return UserFlagDto
     */
    public static UserFlagDto getUserFlagDto(HttpServletRequest request) {
        return new UserFlagDto()
            .setLocale(resolveLocale(request))
            .setRequestInfo(from(request));
    }

    /**
     * 解析请求中的query map
     *
     * @param request req
     * @return map
     */
    public static Map<String, String> getQueryParametersMap(HttpServletRequest request) {
        return parseQueryString(request.getQueryString());
    }

    /**
     * 解析请求的query字符串
     * <p>多个相同key时以第一个key为准</p>
     *
     * @param queryString requestBody
     * @return queryString 对应的 key,value
     */
    public static Map<String, String> parseQueryString(String queryString) {
        if (StringUtils.isEmpty(queryString)) {
            return Collections.emptyMap();
        }
        return Arrays.stream(queryString.split(CONNECTOR))
            .map(s -> s.split(EQUAL_SIGN))
            .filter(ArrayUtils::isNotEmpty)
            .collect(Collectors.toMap(arr -> UrlCodeUtil.urlDecode(arr[0]),
                arr -> arr.length > 1 ? UrlCodeUtil.urlDecode(arr[1]) : StringUtils.EMPTY,
                (k1, k2) -> k1));
    }

    /**
     * 从request中解析header
     *
     * @param request request
     * @return header map
     */
    public static Map<String, String[]> getHeadersMap(HttpServletRequest request) {
        if (request == null) {
            return Collections.emptyMap();
        }
        Map<String, String[]> headerMap = new HashMap<>(16);
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            List<String> values = new ArrayList<>();
            Enumeration<String> headerValues = request.getHeaders(header);
            while (headerValues.hasMoreElements()) {
                values.add(headerValues.nextElement());
            }
            headerMap.put(header, values.toArray(new String[0]));
        }
        return headerMap;
    }

}
