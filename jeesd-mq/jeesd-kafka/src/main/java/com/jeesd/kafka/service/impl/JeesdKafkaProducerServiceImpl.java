package com.jeesd.kafka.service.impl;

import com.alibaba.fastjson.JSON;
import com.jeesd.kafka.domain.Message;
import com.jeesd.kafka.service.JeesdKafKaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Service
@Slf4j
public class JeesdKafkaProducerServiceImpl implements JeesdKafKaProducerService {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void sendSyncMsg(String topic, Message message) {
        if(null == message) {
           return;
        }
        kafkaTemplate.send(topic, JSON.toJSON(message).toString());
    }

    @Override
    public void sendAsyncMsg(String topic, Message message) {

        if(null == message) {
            return;
        }
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, JSON.toJSON(message).toString());

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable e) {
                log.error("发送消息失败:" + e.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> sendResult) {
                log.debug("发送结果:" + sendResult.toString());
            }
        });
    }
}
