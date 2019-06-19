package com.jeesd.websocket.service.impl;

import com.jeesd.websocket.service.WebSocketService;
import org.springframework.stereotype.Service;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WebSocketServiceImpl implements WebSocketService {

    //map存储在线用户session
    private static ConcurrentHashMap<String, Session> websocketList = new ConcurrentHashMap<>();

    @Override
    public void addUserSession(String userId, Session session) {

        websocketList.put(userId, session);
    }

    @Override
    public void removeUserSession(String userId) {

        websocketList.remove(userId);
    }

    /**
     *  指定用户发送消息
     * @param session
     * @param message
     */
    @Override
    public void sendMessageForUser(Session session, String message) {

        if(session == null) {
            return;
        }
        // getAsyncRemote()和getBasicRemote()异步与同步
        RemoteEndpoint.Async async = session.getAsyncRemote();
        //发送消息
        async.sendText(message);
    }

    /**
     * 发送所以用户
     * @param message
     */
    @Override
    public void sendMessageForAll(String message) {

        //循环发送消息
        websocketList.forEach((userId, session) -> {
            sendMessageForUser(session, message);
        });
    }

    @Override
    public void sendMessageForUserId(String userId, String message) {

        Session session = websocketList.get(userId);
        sendMessageForUser(session, message);
    }
}
