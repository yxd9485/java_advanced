package com.example.multithreading_all.common.handler;

import com.example.multithreading_all.common.core.CustomizeThreadPoolExecutorInvoke;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author: XiaoDong.Yang
 * @date: 2023/1/4 16:03
 */
public interface CustomizeRejectedExecutionHandler {
    /**
     * 输出异常
     * @param command
     * @param executor
     */
    void rejectedExecution(Runnable command, CustomizeThreadPoolExecutorInvoke executor);

}
