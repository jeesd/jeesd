package com.jeesd.rabbitmq.constant;

public interface RabbitConstant {

    // queue 配置
    String JEESD_QUEUE_NAME = "jeesd.queue";
    String JEESD_QUEUE_DURABLE = "true";

    // exchange 配置
    String JEESD_EXCHANGE_NAME = "jeesd.exchange";
    String JEESD_EXCHANGE_TYPE = "direct";

    // routing key
    String JEESD_ROUTING_KEY = "jeesd.*";
}
