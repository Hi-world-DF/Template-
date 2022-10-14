package com.wdf.tp.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ErrorCodeEnum 错误码
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/8 11:54
 * @since 1.0.0
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeEnum implements IErrorCode{
    /** 成功 */
    OK(0, "error_code.ok"),
    UNKNOWN_ERROR(1, "error_code.unknown_error"),
    BAD_REQUEST(2, "error_code.bad_request"),
    UNAUTHORIZED(3, "error_code.unauthorized"),
    FORBIDDEN(4, "error_code.forbidden"),
    METHOD_NOT_ALLOWED(5, "error_code.method_not_allowed"),
    RETRY(6, "error_code.retry"),
    TIMEOUT(7, "error_code.timeout"),
    RATE_LIMITER_EXCEEDED(8, "error_code.rate_limiter_exceeded"),
    INVALID_VERSION(9, "error_code.invalid_version"),
    SERVICE_RATE_LIMITER_EXCEEDED(10, "error_code.service_rate_limiter_exceeded"),
    VERSION_TOO_LOW(11, "error_code.version_too_low"),
    FUNCTION_PAUSE_TEMPORARY(12, "error_code.function_pause_temporary"),
    DELETE_FAILED(80, "error_code.delete_failed"),
    ;

    private final int errorCode;
    private final String errorMessage;
}
