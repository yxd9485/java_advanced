package com.example.multithreading_all.common.handler;

import com.example.multithreading_all.common.core.CustomizeThreadPoolExecutorInvoke;

/**
 * module: 应用模块名称<br/>
 * <p>
 * 令调用者线程自己执行command任务的拒绝策略
 * 评价：在线程池压力过大时，让提交任务的线程自己执行该任务（异步变同步），
 *      能够有效地降低线程池的压力，也不会丢失任务，但可能导致整体业务吞吐量大幅降低
 *
 * @author XiaoDong.Yang
 * @date: 2023/1/4 16:15
 */
public class CustomizeCallerRunsPolicy implements CustomizeRejectedExecutionHandler{
    @Override
    public void rejectedExecution(Runnable command, CustomizeThreadPoolExecutorInvoke executor) {
        if (!executor.isShutdown()) {
            // 如果当前线程池不是shutdown状态，则令调用者线程自己执行command任务
            command.run();
        }else{
            // 如果已经是shutdown状态了，就什么也不做直接丢弃任务
        }
    }
}
