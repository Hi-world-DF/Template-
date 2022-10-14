package com.wdf.tp.common.config;

/**
 * 枚举值的基础类，经测试入参会被转换为String，所以在转换时使用String合适
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 14:20
 * @since 1.0.0
 */
public interface ITransferStringValueEnum extends ITransferValueBaseEnum<String> {

    /**
     * 获取对应的值
     *
     * @return T
     */
    @Override
    String getTransferValue();
}
