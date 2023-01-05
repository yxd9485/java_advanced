package com.example.multithreading_all.common.config;

import com.example.multithreading_all.common.core.CustomizeThreadPoolExecutor;
import com.example.multithreading_all.common.handler.CustomizeAbortPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/1/4 17:53
 */
@Configuration
public class CustomizeThreadPoolConfig {

    @Bean
    public CustomizeThreadPoolExecutor customizeThreadPoolExecutor() {
        // Todo
        int corePoolSize = 5;
        int maximumPooSize = 10;
        int queueCapacity = 10;
        return new CustomizeThreadPoolExecutor(
                corePoolSize, maximumPooSize, 60, TimeUnit.SECONDS,
                // 有界队列
                new LinkedBlockingQueue<>(queueCapacity),
                Executors.defaultThreadFactory(),
                new CustomizeAbortPolicy());
    }





}
