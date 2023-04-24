package com.example.multithreading_all.common.rabbitMQ;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/4/21 15:50
 */
@Component
@Slf4j
public class RabbitMqConsumer {

    @RabbitListener(queues = {RabbitMQConfig.COMMON_QUEUE_NAME})
    public void receiveMsg(Message message, Channel channel) {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("消息内容：" + msg);
    }

}
