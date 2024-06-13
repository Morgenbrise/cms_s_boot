package com.server.cms.config.websocket;

import com.server.cms.config.jwt.JWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtChannelInterceptor implements ChannelInterceptor {

    private final JWT jwt;

    public JwtChannelInterceptor(JWT jwt) {
        this.jwt = jwt;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompCommand command = StompCommand.valueOf(message.getHeaders().get("simpCommand").toString());
        if (command == StompCommand.SEND) {
            // WebSocket 메시지가 전송되기 전에 JWT 확인
            String jwtToken = extractJwtToken(message);
            JWT.Claims claims = jwt.verify(jwtToken);
            // JWT를 기반으로 사용자를 인증하고, 메시지에 사용자 정보를 추가할 수 있습니다.
            String userId = claims.getUserId();
            message = enrichMessageWithUserInfo(message, userId);
        }
        return message;

    }

    // WebSocket 메시지에서 JWT 추출
    private String extractJwtToken(Message<?> message) {
        // 실제로는 여기에서 헤더나 페이로드에서 JWT를 추출합니다.
        // 예시로 페이로드에서 추출하는 것으로 가정합니다.
        return message.getPayload().toString();
    }

    // 메시지에 사용자 정보 추가
    private Message<?> enrichMessageWithUserInfo(Message<?> message, String userId) {
        // 메시지에 사용자 정보를 추가하여 반환합니다.
        // 예를 들어, 헤더나 페이로드에 사용자 ID를 추가할 수 있습니다.
        return message;
    }

}
