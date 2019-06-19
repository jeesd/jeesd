package com.jeesd.kafka.controller;

import com.alibaba.fastjson.JSON;
import com.jeesd.kafka.constant.KafkaConstant;
import com.jeesd.kafka.domain.Message;
import com.jeesd.kafka.service.JeesdKafKaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class SendMsgController {

    @Autowired
    private JeesdKafKaProducerService jeesdKafKaProducerService;

    @GetMapping("/send/msg")
    public void sendMessage() {
        Message message = new Message("测试", "这就是队列内容！", LocalTime.now());
        jeesdKafKaProducerService.sendAsyncMsg(KafkaConstant.JEESD_TOPIC, message);
    }
}
