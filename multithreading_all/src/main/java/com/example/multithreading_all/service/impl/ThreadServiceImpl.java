package com.example.multithreading_all.service.impl;

import com.example.multithreading_all.service.ThreadService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * module: public configuration <br/>
 * <p>
 * description: Thread pool configuration <br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/1/4 11:25
 */
@Service
@Slf4j
public class ThreadServiceImpl implements ThreadService {


    @Override
    @Async("getAsyncExecutor")
    public void getThread(Integer input) {
        for (int i = 0; i < input; i++) {
            log.info("线程" + Thread.currentThread().getName() + " 执行异步任务：" + i);
        }
    }
}
