package com.rtc.message.event;

import com.rtc.message.repository.SessionRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

  private final SessionRepository<String, WebSocketSession> sessionRepository;

  @EventListener
  public void handleSessionConnect(SessionConnectedEvent event) {
    log.info("Session connected event");
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
  }

  @EventListener
  public void handleSessionDisconnect(SessionDisconnectEvent event) {
    log.info("Session disconnect event");
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
    Objects.requireNonNull(accessor.getSessionAttributes())
        .forEach((k, v) -> System.out.println(k + v));
  }

  @EventListener
  public void handleSessionSubscribe(SessionSubscribeEvent event) {
    log.info("Session subscribe event");
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
    Objects.requireNonNull(accessor.getSessionAttributes())
        .forEach((k, v) -> System.out.println(k + v));
  }

}
