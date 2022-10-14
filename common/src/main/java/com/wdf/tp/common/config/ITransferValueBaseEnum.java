package com.wdf.tp.common.config;

/**
 * 枚举值的基础类，需要入参的枚举值做转换
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 14:20
 * @since 1.0.0
 */
public interface ITransferValueBaseEnum<T> {

    /**
     * 获取对应的值
     *
     * @return T
     */
    T getTransferValue();
}
