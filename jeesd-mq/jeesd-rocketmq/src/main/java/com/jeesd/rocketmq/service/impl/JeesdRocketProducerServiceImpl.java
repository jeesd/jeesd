package com.jeesd.rocketmq.service.impl;

import com.alibaba.fastjson.JSON;
import com.jeesd.rocketmq.domain.Message;
import com.jeesd.rocketmq.service.JeesdRocketProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JeesdRocketProducerServiceImpl implements JeesdRocketProducerService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void sendSyncMsg(String topic, Message message) {

        //rocketmq提供了多种方式（延迟消息，顺序消息，事务消息等）
        rocketMQTemplate.syncSend(topic, JSON.toJSON(message).toString());
    }

    @Override
    public void sendAsyncMsg(String topic, Message message) {

        rocketMQTemplate.asyncSend(topic, JSON.toJSON(message).toString(),new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.debug("消息传输成功");
                log.debug(JSON.toJSONString(sendResult));
            }
            @Override
            public void onException(Throwable e) {
                log.error("消息传输失败", e);
            }
        });
    }
}
