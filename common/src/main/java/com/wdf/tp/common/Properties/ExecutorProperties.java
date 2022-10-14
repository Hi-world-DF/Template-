package com.wdf.tp.common.Properties;

import com.wdf.tp.common.config.RejectedPolicy;
import com.wdf.tp.common.pojo.ThreadPoolType;
import lombok.Data;
import lombok.ToString;

/**
 * Executor Properties
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 14:02
 * @since 1.0.0
 */
@Data
@ToString
public class ExecutorProperties {

    /** 线程池类型 */
    private ThreadPoolType poolType = ThreadPoolType.NORMAL;
    /** 核心线程数 */
    private Integer corePoolSize;
    /** 是否允许核心线程超时 */
    private Boolean allowCoreThreadTimeout;
    /** 最大线程数 */
    private Integer maxPoolSize;
    /** 阻塞队列大小 */
    private Integer queueCapacity;
    /** 线程前缀 */
    private String threadNamePrefix;
    /** 饱和策略 */
    private RejectedPolicy rejectedPolicy;

    /* ForkJoin */
    /** the parallelism level */
    private Integer parallelism;
    /** true=FIFO, false=LIFO */
    private Boolean asyncMode;
}
