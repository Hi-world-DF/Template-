package com.wdf.tp.common.config;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 拒绝策略
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 13:57
 * @since 1.0.0
 */
public enum RejectedPolicy {
    /** 调用主线程执行 */
    CALL_RUNS,
    /** 拒绝抛异常 */
    ABORT,
    /** 丢弃任务 */
    DISCARD,
    /** 丢弃最旧的任务 */
    DISCARD_OLDEST;

    /**
     * 解析拒绝策略
     *
     * @param policy 拒绝策略枚举
     * @return 拒绝策略实现
     */
    public static RejectedExecutionHandler parseRejectedHandler(RejectedPolicy policy) {
        if (null == policy) {
            return new ThreadPoolExecutor.AbortPolicy();
        }
        switch (policy) {
            case CALL_RUNS:
                return new ThreadPoolExecutor.CallerRunsPolicy();
            case DISCARD:
                return new ThreadPoolExecutor.DiscardPolicy();
            case DISCARD_OLDEST:
                return new ThreadPoolExecutor.DiscardOldestPolicy();
            default:
                return new ThreadPoolExecutor.AbortPolicy();
        }
    }
}
