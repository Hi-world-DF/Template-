package com.wdf.tp.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 登录设备枚举
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 11:53
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum DeviceEnum {
    /** 安卓 */
    ANDROID("Android", (byte) 0),
    IOS("iOS", (byte) 1),
    WEB("Web", (byte) 2);

    private final String value;
    private final byte byteValue;

    /** 名称Map */
    private static final Map<String, DeviceEnum> NAME_MAP = Arrays.stream(DeviceEnum.values())
        .collect(Collectors.toMap(DeviceEnum::name, Function.identity()));

    /** byte值Map */
    private static final Map<Byte, DeviceEnum> BYTE_VALUE_MAP = Arrays.stream(DeviceEnum.values())
        .collect(Collectors.toMap(DeviceEnum::getByteValue, Function.identity()));

    /**
     * value to Enum
     *
     * @param device 枚举值
     * @return 枚举
     */
    public static DeviceEnum from(String device) {
        return NAME_MAP.get(device);
    }

    /**
     * value to Enum
     *
     * @param device 枚举值
     * @return 枚举
     */
    public static DeviceEnum from(Byte device) {
        return BYTE_VALUE_MAP.get(device);
    }
}
