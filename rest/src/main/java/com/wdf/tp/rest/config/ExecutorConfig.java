package com.wdf.tp.rest.config;

import com.wdf.tp.common.Properties.ExecutorProperties;
import com.wdf.tp.common.config.RejectedPolicy;
import com.wdf.tp.common.config.YamlPropertySourceFactory;
import com.wdf.tp.common.pojo.ThreadPoolType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Executor Config
 *
 * @author Defen Wang
 * @version 1.0, 2022/10/14 13:41
 * @since 1.0.0
 */
@Configuration
@PropertySource(value = "classpath:executor.yaml", factory = YamlPropertySourceFactory.class)
@EnableAsync(proxyTargetClass = true)
@RefreshScope
@Slf4j
public class ExecutorConfig {

    @Bean
    @ConfigurationProperties(prefix = "executor.controller.biz")
    public ExecutorProperties bizProperties() {
        return new ExecutorProperties();
    }

    /**
     * 异步Controller线程池
     *
     * @return 线程池
     */
    @Bean(destroyMethod = "shutdown")
    public Executor servletBizExecutor() {
        ExecutorProperties biz = bizProperties();
        if (biz.getPoolType() == ThreadPoolType.FORKJOIN) {
            return createForkJoinExecutor(biz);
        }
        return createNormalExecutor(biz);
    }

    @Bean
    @ConfigurationProperties(prefix = "executor.default-blocking")
    public ExecutorProperties defaultBlockingProperties() {
        return new ExecutorProperties();
    }

    /**
     * 默认阻塞任务线程池
     *
     * @return 线程池
     */
    @Bean(destroyMethod = "shutdown")
    public Executor defaultBlockingExecutor() {
        ExecutorProperties biz = defaultBlockingProperties();
        if (biz.getPoolType() == ThreadPoolType.FORKJOIN) {
            return createForkJoinExecutor(biz);
        }
        return createNormalExecutor(biz);
    }

    private Executor createForkJoinExecutor(ExecutorProperties properties) {
        int parallelism = null == properties.getParallelism() ? Runtime.getRuntime().availableProcessors() : properties.getParallelism();
        boolean asyncMode = BooleanUtils.isNotFalse(properties.getAsyncMode());
        ForkJoinPool.ForkJoinWorkerThreadFactory factory = new NamedForkJoinWorkerThreadFactory(properties.getThreadNamePrefix());
        return new ForkJoinPool(parallelism, factory, null, asyncMode);
    }

    private Executor createNormalExecutor(ExecutorProperties properties) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setAllowCoreThreadTimeOut(properties.getAllowCoreThreadTimeout());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setQueueCapacity(properties.getQueueCapacity());
        executor.setThreadNamePrefix(properties.getThreadNamePrefix());
        executor.setRejectedExecutionHandler(RejectedPolicy.parseRejectedHandler(properties.getRejectedPolicy()));
        executor.initialize();
        return executor;
    }

    /**
     * 带命名规则的ForkJoinWorkerThreadFactory
     */
    public static class NamedForkJoinWorkerThreadFactory implements ForkJoinPool.ForkJoinWorkerThreadFactory {

        final String prefix;
        final AtomicInteger count = new AtomicInteger();

        public NamedForkJoinWorkerThreadFactory(String prefix) {
            this.prefix = prefix;
        }

        @Override
        public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
            ForkJoinWorkerThread thread = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
            thread.setDaemon(true);
            thread.setName(prefix + count.getAndIncrement());
            return thread;
        }
    }

}
