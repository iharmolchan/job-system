package by.intexsoft.imolchan.jobsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AsyncExecutorConfig {

    @Value("${job-service.simultaneousJobsLimit}")
    private Integer maxThreads;

    @Bean
    public ExecutorService getTaskExecutor() {
        return Executors.newFixedThreadPool(maxThreads);
    }
}
