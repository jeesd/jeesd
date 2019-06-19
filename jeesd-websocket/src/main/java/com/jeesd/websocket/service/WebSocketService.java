package com.jeesd.websocket.service;

import org.springframework.stereotype.Service;
import javax.websocket.Session;

@Service
public interface WebSocketService {


    void addUserSession(String userId, Session session);

    void removeUserSession(String userId);

    /**
     *  指定用户发送消息
     * @param session
     * @param message
     */
    void sendMessageForUser(Session session, String message);

    void sendMessageForAll(String message);

    void sendMessageForUserId(String userId, String message);
}
