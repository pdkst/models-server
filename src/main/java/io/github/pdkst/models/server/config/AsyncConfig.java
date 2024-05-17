package io.github.pdkst.models.server.config;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author pdkst
 * @since 2024/05/13
 */
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(AsyncProperties.class)
public class AsyncConfig implements AsyncConfigurer {
    private final AsyncProperties asyncProperties;

    @Override
    @Bean("getAsyncExecutor")
    public Executor getAsyncExecutor() {
        return buildTaskExecutor(asyncProperties.getPool());

    }

    @NotNull
    private ThreadPoolTaskExecutor buildTaskExecutor(AsyncProperties.Pool pool) {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(pool.getCorePoolSize());
        executor.setMaxPoolSize(pool.getMaxPoolSize());
        executor.setQueueCapacity(pool.getQueueCapacity());
        executor.setAllowCoreThreadTimeOut(pool.isAllowCoreThreadTimeOut());
        executor.setPrestartAllCoreThreads(pool.isPreStartAllCoreThreads());
        executor.setStrictEarlyShutdown(pool.isStrictEarlyShutdown());
        executor.setThreadNamePrefix(pool.getThreadNamePrefix());
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
