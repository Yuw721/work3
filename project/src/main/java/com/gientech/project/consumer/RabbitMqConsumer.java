package com.gientech.project.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * @Author: Yuwei
 * @Date: 2022-10-02-16:45
 * @Description:
 */

@Component
@Slf4j
public class RabbitMqConsumer {

    // 创建队列 queue_work
    @Bean
    public Queue queueWork() {
        return new Queue("queue_work");
    }

    // 创建监听者，监听 queue_work 队列
    @RabbitListener(queues = "queue_work")
    public void receiveMessage1(String msg, Channel channel, Message message) {
        log.info("1---- msg:{}, channel:{}, message:{}", msg, channel, message);
    }

    // 创建监听者，监听 queue_work 队列
    @RabbitListener(queues = "queue_work")
    public void receiveMessage2(Object obj, Channel channel, Message message) {
        log.info("2---- obj:{}, channel:{}, message:{}", obj, channel, message);
    }
}


