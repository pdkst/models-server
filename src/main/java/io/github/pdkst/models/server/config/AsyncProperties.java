package io.github.pdkst.models.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author pdkst
 * @since 2024/05/13
 */
@Data
@ConfigurationProperties(prefix = "async")
public class AsyncProperties {
    private Pool pool;

    @Data
    static class Pool {
        private int corePoolSize = 10;
        private int maxPoolSize = 20;
        private int queueCapacity = 10000;
        private int keepAliveSeconds = 60;
        private boolean allowCoreThreadTimeOut = false;
        private boolean preStartAllCoreThreads = false;
        private boolean strictEarlyShutdown = false;
        private String threadNamePrefix = "async-";
    }
}
