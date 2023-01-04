package com.example.multithreading_all.common.handler;

import com.example.multithreading_all.common.core.CustomizeThreadPoolExecutorInvoke;

/**
 * module: 应用模块名称<br/>
 * <p>
 * 直接丢弃任务的拒绝策略
 * 评价：简单的直接丢弃任务，适用于对任务执行成功率要求不高的场合
 *
 * @author XiaoDong.Yang
 * @date: 2023/1/4 16:17
 */
public class CustomizeDiscardPolicy implements CustomizeRejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable command, CustomizeThreadPoolExecutorInvoke executor) {

    }
}
