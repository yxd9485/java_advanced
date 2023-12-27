package com.example.multithreading_all.common.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * module: web socket 通信模块<br/>
 * <p>
 * description: <br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/8/16 17:20
 */
@Component
@ServerEndpoint("/ws/test")
@Slf4j
public class SocketEndPoint {


    private static final Map<String, Session> CLIENT_SESSION_MAP = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        log.info("客户端 {} 开启了链接",session.getId());
        CLIENT_SESSION_MAP.put(session.getId(),session);

    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info("客户端 {} 连接异常... 异常信息：->", session.getId(), error);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        // 可根据会话ID或者message中自定义唯一标识从容器中取出会话对象来进行操作
        log.info("收到消息, 来自客户端 {}, 消息内容 -> {}", session.getId(), message);
    }

    @OnClose
    public void onClose(Session session) {
        log.info("客户端 {} 关闭了连接...", session.getId());
        // 客户端关闭时，从保管容器中踢出会话
        CLIENT_SESSION_MAP.remove(session.getId());
    }


    public static void sendMessageForAllClient(String message) {
        CLIENT_SESSION_MAP.values().forEach(session -> {
            try {
                log.info("给客户端 {} 发送消息 消息内容: {}", session.getId(), message);
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
                log.info("给客户端 {} 发送消息失败， 异常信息：", session.getId(), e);
            }
        });
    }









}
