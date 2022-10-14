package com.wdf.tp.common.pojo;

/**
 * 线程池类型
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 14:01
 * @since 1.0.0
 */
public enum ThreadPoolType {
    /** ThreadPoolTaskExecutor */
    NORMAL,
    /** 定时任务 */
    SCHEDULER,
    /** ForkJoinPool */
    FORKJOIN;
}
