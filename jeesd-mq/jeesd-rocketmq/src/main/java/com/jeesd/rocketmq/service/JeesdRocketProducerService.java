package com.jeesd.rocketmq.service;

import com.jeesd.rocketmq.domain.Message;

public interface JeesdRocketProducerService {

    void sendSyncMsg(String topic, Message message);

    void sendAsyncMsg(String topic, Message message);
}
