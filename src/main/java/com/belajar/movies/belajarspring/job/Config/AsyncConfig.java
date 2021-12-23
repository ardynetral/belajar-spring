package com.belajar.movies.belajarspring.job.Config;

import com.belajar.movies.belajarspring.global.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(Config.BEAN_THREAD_EXECUTOR)
    public Executor taskExecutor(){
        int corePoolSize = 8;
        int maxPoolSize = 10;
        int queueCapacity = 100;

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);

        return executor;
    }
}
