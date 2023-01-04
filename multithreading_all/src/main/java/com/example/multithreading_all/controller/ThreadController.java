package com.example.multithreading_all.controller;

import com.example.multithreading_all.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * module: public configuration <br/>
 * <p>
 * description: Thread pool configuration <br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/1/4 11:11
 */
@RestController
@RequestMapping("/java")
public class ThreadController  {

    @Autowired
    private ThreadService threadService;

    @GetMapping("/thread")
    public void getThread(@RequestParam("count") Integer count) {
         threadService.getThread(count);
    }









}
