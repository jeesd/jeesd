package com.jeesd.rabbitmq.service.impl;

import com.jeesd.rabbitmq.service.JeesdRabbitProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class JeesdRabbitProducerServiceImpl implements JeesdRabbitProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(Map<String, Object> headers, Object message, String messageId, String exchangeName, String key) {

        //自定义消息头
        Map<String, Object> heads = new HashMap<>();
        heads.put("jeesdMsg", "自定义消息");
        MessageHeaders messageHeaders = new MessageHeaders(heads);
        // 创建消息
        Message<Object> msg = MessageBuilder.createMessage(message, messageHeaders);
        //确认消息是否到达交换器
        rabbitTemplate.setConfirmCallback((data, ack, cause) -> {
            if (!ack) {
               log.error("data：{} , ack:{}，消息未到达交换器，需要对消息进行补偿！", data.getId(), ack);
            }
        });
        //消息处理失败
        rabbitTemplate.setReturnCallback((messageE, replyCode, replyText, exchange, routingKey) -> {
            log.error("message:{}; replyCode: {}; replyText: {} ; exchange:{} ; routingKey:{}",
                    messageE, replyCode, replyText, exchange, routingKey);
        });
        CorrelationData correlationData = new CorrelationData(messageId);
        rabbitTemplate.convertAndSend(exchangeName, key, msg, correlationData);

    }

    @Override
    public void sendMessage(Object message, String messageId, String exchangeName, String key) {

        //消息id，全局唯一 能够唯一标识消息
        String randomMessageId = UUID.randomUUID().toString();
        sendMessage(null, message, randomMessageId, exchangeName, key);
    }

    @Override
    public void sendMessage(Object message, String exchangeName, String key) {

        sendMessage(message, null, exchangeName, key);
    }
}
