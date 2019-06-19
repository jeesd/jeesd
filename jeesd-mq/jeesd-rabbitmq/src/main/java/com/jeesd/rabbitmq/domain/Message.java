package com.jeesd.rabbitmq.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String msg;
    /**
     * 发送时间
     */
    private LocalTime sendTime;
}
