package com.wdf.tp.common.vo;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.wdf.tp.common.pojo.ErrorCodeEnum;
import com.wdf.tp.common.pojo.IErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;

import java.io.Serializable;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * 通用返回值包装类
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 14:28
 * @since 1.0.0
 */
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming
public class ApiResult<T> implements Serializable {
    @ApiModelProperty(value = "是否成功", dataType = "Boolean")
    private boolean success;
    @ApiModelProperty(value = "错误码", example = "0")
    private int errorCode;
    @ApiModelProperty(value = "返回数据", dataType = "Object")
    private T data;
    @ApiModelProperty(value = "错误信息", example = "ok")
    private String errorMsg;

    public static <T> ApiResult<T> success() {
        return new ApiResult<>(true, ErrorCodeEnum.OK.getErrorCode(), null, ErrorCodeEnum.OK.name());
    }

    public static <T> ApiResult<T> success(MessageSource messageSource, Locale locale) {
        return new ApiResult<>(true, ErrorCodeEnum.OK.getErrorCode(), null,
            messageSource.getMessage(ErrorCodeEnum.OK.getErrorMessage(), null, locale));
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(true, ErrorCodeEnum.OK.getErrorCode(), data, ErrorCodeEnum.OK.name());
    }

    public static <T> CompletionStage<ApiResult<T>> successAsync(T data) {
        return CompletableFuture.completedFuture(
            new ApiResult<>(true, ErrorCodeEnum.OK.getErrorCode(), data, ErrorCodeEnum.OK.name()));
    }

    public static <T> ApiResult<T> successForMsg(String msgKey, MessageSource messageSource, Locale locale, Object[] args) {
        return new ApiResult<>(true, ErrorCodeEnum.OK.getErrorCode(), null, messageSource.getMessage(msgKey, args, locale));
    }

    public static <T> ApiResult<T> successForMsg(String msgKey, T data, MessageSource messageSource, Locale locale, Object[] args) {
        return new ApiResult<>(true, ErrorCodeEnum.OK.getErrorCode(), data, messageSource.getMessage(msgKey, args, locale));
    }

    public static <T> ApiResult<T> failed(int errorCode, String errorMsg) {
        return new ApiResult<>(false, errorCode, null, errorMsg);
    }

    public static <T> ApiResult<T> failed(int errorCode, T data, String errorMsg) {
        return new ApiResult<>(false, errorCode, data, errorMsg);
    }

    public static <T> ApiResult<T> failed(int errorCode, String i18nErrorKey, MessageSource messageSource, Locale locale) {
        return new ApiResult<>(false, errorCode, null, messageSource.getMessage(i18nErrorKey, null, locale));
    }

    public static <T> CompletionStage<ApiResult<T>> failedAsync(int errorCode, String i18nErrorKey, MessageSource messageSource, Locale locale) {
        return CompletableFuture.completedFuture(new ApiResult<>(false, errorCode, null,
            messageSource.getMessage(i18nErrorKey, null, locale)));
    }

    public static <T> ApiResult<T> failed(int errorCode, String i18nErrorKey, MessageSource messageSource, Locale locale, Object[] args) {
        return new ApiResult<>(false, errorCode, null, messageSource.getMessage(i18nErrorKey, args, locale));
    }

    public static <T> ApiResult<T> failed(IErrorCode error) {
        return new ApiResult<>(false, error.getErrorCode(), null, error.getErrorMessage());
    }

    public static <T> ApiResult<T> failed(IErrorCode error, MessageSource messageSource, Locale locale) {
        return new ApiResult<>(false, error.getErrorCode(), null, messageSource.getMessage(error.getErrorMessage(), null, locale));
    }

    public static <T> CompletionStage<ApiResult<T>> failedAsync(IErrorCode error, MessageSource messageSource, Locale locale) {
        return CompletableFuture.completedFuture(new ApiResult<>(false, error.getErrorCode(), null,
            messageSource.getMessage(error.getErrorMessage(), null, locale)));
    }

    public static <T> ApiResult<T> failed(IErrorCode error, MessageSource messageSource, Locale locale, Object[] args) {
        return new ApiResult<>(false, error.getErrorCode(), null, messageSource.getMessage(error.getErrorMessage(), args, locale));
    }

    public static <T> ApiResult<T> parameterFailed(IErrorCode error, T data, MessageSource messageSource, Locale locale) {
        return new ApiResult<>(false, error.getErrorCode(), data, messageSource.getMessage(error.getErrorMessage(), null, locale));
    }

    public static <T> CompletionStage<ApiResult<T>> parameterFailedAsync(IErrorCode error, T data, MessageSource messageSource, Locale locale) {
        return CompletableFuture.completedFuture(new ApiResult<>(false, error.getErrorCode(), data,
            messageSource.getMessage(error.getErrorMessage(), null, locale)));
    }

    public static <T> ApiResult<T> parameterFailed(int errorCode, String i18nErrorKey, T data, MessageSource messageSource, Locale locale) {
        return new ApiResult<>(false, errorCode, data, messageSource.getMessage(i18nErrorKey, null, locale));
    }

    public static <T> CompletionStage<ApiResult<T>> parameterFailedAsync(int errorCode, String i18nErrorKey, T data, MessageSource messageSource, Locale locale) {
        return CompletableFuture.completedFuture(new ApiResult<>(false, errorCode, data,
            messageSource.getMessage(i18nErrorKey, null, locale)));
    }

    public static <T> ApiResult<T> failedForMsg(int errorCode, String i18nErrorKey, T data, MessageSource messageSource, Locale locale, Object[] args) {
        return new ApiResult<>(false, errorCode, data, messageSource.getMessage(i18nErrorKey, args, locale));
    }

    public static <T> ApiResult<T> failedForMsg(String msgKey, MessageSource messageSource, Locale locale, Object[] args) {
        return new ApiResult<>(true, ErrorCodeEnum.UNKNOWN_ERROR.getErrorCode(), null, messageSource.getMessage(msgKey, args, locale));
    }
}
