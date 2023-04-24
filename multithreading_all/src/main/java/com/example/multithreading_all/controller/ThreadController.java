package com.example.multithreading_all.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.example.multithreading_all.common.aop.RedisLockAnnotation;
import com.example.multithreading_all.common.enums.RedisLockTypeEnum;
import com.example.multithreading_all.service.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
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
/*@RestController
@RequestMapping("/java")
@Slf4j
@Aspect
@Component*/
public class ThreadController  {

    /*@Autowired*/
    private ThreadService threadService;

    /*@Value("${spring.redis.password}")*/
    public String pass;

/*    @GetMapping("/thread")*/
  //  @RedisLockAnnotation(typeEnum = RedisLockTypeEnum.ONE, lockTime = 3)
    public void getThread(@RequestParam("count") Integer count) {
    //    log.info("--------------->nacos {}",pass);
        threadService.getThread(count);
    }









}
