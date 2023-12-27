package com.example.multithreading_all.controller;

import com.example.multithreading_all.common.websocket.SocketEndPoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/8/16 17:42
 */
@RestController
@RequestMapping("/api/ws")
public class WebSocketController {

    /**
     * http://localhost:8080/api/ws/send
     * @param message
     * @return
     */
    @GetMapping("/send")
    public boolean send(@RequestParam String message) {
        SocketEndPoint.sendMessageForAllClient(message);
        return true;
    }

}
