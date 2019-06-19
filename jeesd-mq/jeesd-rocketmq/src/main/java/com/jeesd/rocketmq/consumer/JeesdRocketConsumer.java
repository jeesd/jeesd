package com.jeesd.rocketmq.consumer;

import com.jeesd.rocketmq.constant.RocketConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(topic = RocketConstant.JEESD_MSG_TOPIC, consumerGroup = RocketConstant.JEESD_CONSUMER_GROUP,
        consumeMode = ConsumeMode.CONCURRENTLY,messageModel= MessageModel.CLUSTERING,
        selectorExpression="*",selectorType = SelectorType.TAG)
public class JeesdRocketConsumer implements RocketMQListener<String> {


    @Override
    public void onMessage(String message) {

        log.debug("received message: {}", message);
    }
}
