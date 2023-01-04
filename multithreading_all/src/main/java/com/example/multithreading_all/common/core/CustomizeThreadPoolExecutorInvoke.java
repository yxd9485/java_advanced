package com.example.multithreading_all.common.core;


import java.util.concurrent.BlockingQueue;

/**
 * module: customize thread pool<br/>
 * <p>
 * description: executor<br/>
 *
 * @author: XiaoDong.Yang
 * @date: 2023/1/4 13:50
 */
public interface CustomizeThreadPoolExecutorInvoke {

    /**
     * commit job run
     * @param command thread
     */
    void execute(Runnable command);

    /**
     * remove one task
     * @param task task
     * @return true/false
     */
    boolean remove(Runnable task);


    /**
     * get thread queue
     * @return thread queue
     */
    BlockingQueue<Runnable> getQueue();


    /**
     * thread status
     * @return
     */
    boolean isShutdown();

}
