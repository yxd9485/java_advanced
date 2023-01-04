package com.example.multithreading_all.common.handler;

import com.example.multithreading_all.common.core.CustomizeThreadPoolExecutorInvoke;

import java.util.concurrent.RejectedExecutionException;

/**
 * module: 应用模块名称<br/>
 * <p>
 * 抛出RejectedExecutionException的拒绝策略
 * 评价：能让提交任务的一方感知到异常的策略，比较通用，也是jdk默认的拒绝策略
 *
 * @author XiaoDong.Yang
 * @date: 2023/1/4 16:07
 */
public class CustomizeAbortPolicy implements CustomizeRejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable command, CustomizeThreadPoolExecutorInvoke executor) {
        // 直接抛出异常
        throw new RejectedExecutionException("Task " + command.toString() +
                " rejected from " + executor.toString());
    }

}

