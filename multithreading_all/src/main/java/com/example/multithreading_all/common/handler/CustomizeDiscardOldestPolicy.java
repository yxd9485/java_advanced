package com.example.multithreading_all.common.handler;

import com.example.multithreading_all.common.core.CustomizeThreadPoolExecutorInvoke;

/**
 * module: 应用模块名称<br/>
 * <p>
 * 丢弃当前工作队列中最早入队的任务，然后将当前任务重新提交
 * 评价：适用于后出现的任务能够完全代替之前任务的场合(追求最终一致性)
 *
 * @author XiaoDong.Yang
 * @date: 2023/1/4 16:18
 */
public class CustomizeDiscardOldestPolicy implements CustomizeRejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable command, CustomizeThreadPoolExecutorInvoke executor) {
        if (!executor.isShutdown()) {
            // 如果当前线程池不是shutdown状态，则丢弃当前工作队列中最早入队的任务，然后将当前任务重新提交
            executor.getQueue().poll();
            executor.execute(command);
        }else{
            // 如果已经是shutdown状态了，就什么也不做直接丢弃任务
        }
    }
}
