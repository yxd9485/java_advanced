package com.example.multithreading_all.controller;

import com.example.multithreading_all.common.rabbitMQ.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/4/21 14:53
 */
@RestController
@Slf4j
public class MqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/mq/test")
    public String showName(){
        for (int i = 0;i<10;i++){
            rabbitTemplate.convertAndSend(RabbitMQConfig.DEAD_EXCHANGE_NAME, RabbitMQConfig.COMMON_QUEUE_NAME, ""+ UUID.randomUUID());
        }

        return "index";
    }
}
