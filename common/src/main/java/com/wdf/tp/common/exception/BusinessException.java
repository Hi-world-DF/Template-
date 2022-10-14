package com.wdf.tp.common.exception;

import com.wdf.tp.common.pojo.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 业务异常
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/8 11:54
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public class BusinessException extends RuntimeException {

    /** 错误码 */
    private ErrorCodeEnum errorCode;
    /** i18n参数 */
    private Object[] args;
    /** 需要返回的data */
    private Object data;

    /**
     * 构造业务异常
     *
     * @param errorCode 错误码
     * @param args      i18n参数
     */
    public BusinessException(ErrorCodeEnum errorCode, Object... args) {
        super(errorCode.getErrorCode() + "," + errorCode.getErrorMessage(), null, true, true);
        this.errorCode = errorCode;
        this.args = args;
    }
}
