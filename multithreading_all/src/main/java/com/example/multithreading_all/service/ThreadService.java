package com.example.multithreading_all.service;

/**
 * module: public configuration <br/>
 * <p>
 * description: Thread pool configuration <br/>
 *
 * @author: XiaoDong.Yang
 * @date: 2023/1/4 11:22
 */
public interface ThreadService {

    /**
     * SpringBoot Async Thread
     * @param input input
     * @return
     */
    void getThread(Integer input);
}
