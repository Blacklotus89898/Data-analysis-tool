package com.blacklotus.data_analyzer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Mono;

import java.util.Map;

@Configuration
public class WebSocketConfig {

    // Register your WebSocket handler at a specific path
    @Bean
    public HandlerMapping webSocketMapping() {
        return new SimpleUrlHandlerMapping(
            Map.of("/ws/endpoint", myWebSocketHandler()),
            10
        );
    }

    // Adapter required for WebFlux WebSocket support
    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    // Your WebSocket handler bean
    @Bean
    public WebSocketHandler myWebSocketHandler() {
        return new WebSocketHandler() {
            @Override
            public Mono<Void> handle(WebSocketSession session) {
                // Echo back each received message with prefix "Echo: "
                return session.send(
                    session.receive()
                        .map(msg -> session.textMessage("Echo: " + msg.getPayloadAsText()))
                );
            }
        };
    }
}
