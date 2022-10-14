package com.wdf.tp.common.pojo;

/**
 * 错误码枚举服务接口
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 14:29
 * @since 1.0.0
 */
public interface IErrorCode {
    /**
     * 获取错误码
     *
     * @return 错误码
     */
    int getErrorCode();

    /**
     * 获取错误消息
     *
     * @return 错误码
     */
    String getErrorMessage();
}
