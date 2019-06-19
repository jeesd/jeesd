package com.jeesd.kafka.service;

import com.jeesd.kafka.domain.Message;

public interface JeesdKafKaProducerService {

    void sendSyncMsg(String topic, Message message);

    void sendAsyncMsg(String topic, Message message);

}
