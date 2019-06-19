package com.jeesd.rabbitmq.service;

import java.util.Map;

public interface JeesdRabbitProducerService {

    void sendMessage(Map<String, Object> headers, Object message,
                     String messageId, String exchangeName, String key);

    void sendMessage(Object message, String messageId,
                     String exchangeName, String key);

    void sendMessage(Object message, String exchangeName, String key);
}
