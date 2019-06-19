package com.jeesd.rabbitmq.consumer;

import com.jeesd.rabbitmq.constant.RabbitConstant;
import com.jeesd.rabbitmq.domain.Message;
import lombok.extern.slf4j.Slf4j;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class JeesdRabbitConsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitConstant.JEESD_QUEUE_NAME, durable = RabbitConstant.JEESD_QUEUE_DURABLE),
            exchange = @Exchange(value = RabbitConstant.JEESD_EXCHANGE_NAME, type = RabbitConstant.JEESD_EXCHANGE_TYPE),
            key = RabbitConstant.JEESD_ROUTING_KEY)
    )
    @RabbitHandler
    public void onMessage(@Payload Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        log.debug("message:{} ", message);
        log.debug("jeesdMsg:{} ", headers.get("jeesdMsg"));
        // DELIVERY_TAG是唯一标识ID
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //第二个参数代表是否一次签收多条,当该参数为 true 时，则可以一次性确认 DELIVERY_TAG 小于等于传入值的所有消息
        channel.basicAck(deliveryTag, false);
    }
}
