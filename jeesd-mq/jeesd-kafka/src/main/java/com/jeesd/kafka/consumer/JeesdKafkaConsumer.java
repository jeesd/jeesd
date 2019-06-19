package com.jeesd.kafka.consumer;

import com.alibaba.fastjson.JSON;
import com.jeesd.kafka.constant.KafkaConstant;
import com.jeesd.kafka.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JeesdKafkaConsumer {

    @KafkaListener(groupId = KafkaConstant.JEESD_GROUP,topics = KafkaConstant.JEESD_TOPIC)
    public void jeesdConsumer(ConsumerRecord<String, Object> record) {
        log.debug("消费者收到消息:" + JSON.parseObject(record.value().toString(), Message.class));
    }
}
