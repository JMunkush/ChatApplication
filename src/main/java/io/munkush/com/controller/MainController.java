package io.munkush.com.controller;

import io.munkush.com.entity.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final SimpMessagingTemplate messagingTemplate;


    // Подписка на брокер
    //  config.enableSimpleBroker("/broker");
    // 1) QUEUE (публичный)  "/broker/toSendMessage" | return
    // 2) QUEUE (приватный)  "/broker/{receiverName}/queue/private" | convertAndSendToUser

    @MessageMapping("/getMessage")
    @SendTo("/broker/toSendMessage")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/getMessageForOne")
    public ChatMessage sendMessageForOne(@Payload ChatMessage chatMessage){

        System.out.println(chatMessage.getSenderName() + " : " + chatMessage.getReceiverName());

        String destination = "/broker/" + chatMessage.getReceiverName() + "/queue/private";

        messagingTemplate.convertAndSend(destination, chatMessage);

        return chatMessage;
    }

}
