package com.jeesd.websocket.controller;

import com.jeesd.websocket.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Component
@ServerEndpoint(value = "/websocket/{userId}")
@Slf4j
public class WebSocketController {

    @Autowired
    private WebSocketService webSocketService;

    /**
     * 连接事件 加入注解
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam(value = "userId") String userId, Session session) {
        String message = "有新用户[" + userId + "]加入。。。。!";
        log.debug(message);
        webSocketService.addUserSession(userId, session);
        //通知所有用户
        webSocketService.sendMessageForAll(message);
    }

    /**
     * 关闭事件
     * @param userId
     * @param session
     */
    @OnClose
    public void onClose(@PathParam(value = "userId") String userId,Session session) {
        String message = "用户[" + userId + "]退出。。。!";
        log.debug(message);
        webSocketService.removeUserSession(userId);
        //通知所有用户
        webSocketService.sendMessageForAll(message);
    }

    /**
     * 发送消息
     * @param userId
     * @param message
     */
    @OnMessage
    public void OnMessage(@PathParam(value = "userId") String userId, String message) {
        //群发消息
        String info = "用户[" + userId + "]：" + message;
        log.debug(info);
        webSocketService.sendMessageForAll(message);

    }

    /**
     * 异常连接
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        log.error("异常:", throwable);
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }
}
