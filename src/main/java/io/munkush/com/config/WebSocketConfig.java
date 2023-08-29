package io.munkush.com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/broker"); // when Subscribe "/broker/toSendMessage or "/broker/{receiverName}/queue/private"
        config.setApplicationDestinationPrefixes("/prefix"); // when stompClient.send("/prefix/getMessage".. or "../getMessageForOne
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat") // when connect SockJS new SockJS('/chat');
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}