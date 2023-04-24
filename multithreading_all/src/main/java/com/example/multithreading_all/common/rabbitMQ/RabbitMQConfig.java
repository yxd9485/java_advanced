package com.example.multithreading_all.common.rabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/4/21 14:26
 */
@Configuration
public class RabbitMQConfig {

    // 普通交换机
    public static final String COMMON_EXCHANGE_NAME = "common_exchange_advanced";

    // 死信交换机
    public static final String DEAD_EXCHANGE_NAME = "dead_exchange_advanced";

    // 普通队列
    public static final String COMMON_QUEUE_NAME = "common_queue_advanced";

    // 死信队列
    public static final String DEAD_QUEUE_NAME = "dead_queue_advanced";


    @Bean
    public Exchange commonExchange() {
        return new DirectExchange(COMMON_EXCHANGE_NAME);
    }

    @Bean
    public Exchange deadExchange() {
        return new DirectExchange(DEAD_EXCHANGE_NAME);
    }

    // 普通队列
    @Bean
    public Queue commonQueue() {
        return QueueBuilder.durable(COMMON_QUEUE_NAME).deadLetterExchange(DEAD_EXCHANGE_NAME).deadLetterRoutingKey(DEAD_QUEUE_NAME).ttl(10000).build();
    }

    // 死信队列
    @Bean
    public Queue deadQueue() {
        return QueueBuilder.durable(DEAD_QUEUE_NAME).build();
    }


    @Bean
    public Binding commonBinding(@Qualifier("commonQueue") Queue commonQueue, @Qualifier("commonExchange") Exchange commonExchange) {
        return BindingBuilder.bind(commonQueue).to(commonExchange).with(COMMON_QUEUE_NAME).noargs();
    }


    // 绑定死信交换机和死信队列
    @Bean
    public Binding deadBinding11(@Qualifier("deadQueue") Queue deadQueue, @Qualifier("deadExchange") Exchange deadExchange) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with(DEAD_QUEUE_NAME).noargs();
    }

}
