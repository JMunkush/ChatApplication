package io.munkush.com.entity;

import lombok.Data;

@Data
public class ChatMessage {

    private String receiverName;
    private String senderName;
    private MessageType type;
    private String content;



}