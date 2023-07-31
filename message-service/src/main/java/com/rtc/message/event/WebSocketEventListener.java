package com.rtc.message.event;

import com.rtc.message.repository.SessionRepository;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

  private final SessionRepository<String, String> sessionRepository;

  @EventListener
  public void handleSessionConnect(SessionConnectedEvent event) {
    log.info("Session connected event");
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
    // TODO obtain userId in a systematic way
    GenericMessage<Object> genericMessage = (GenericMessage<Object>) accessor.getMessageHeaders()
        .get("simpConnectMessage");
    String userId = ((List<String>) ((Map<String, Object>) genericMessage.getHeaders().get("nativeHeaders")).get(
        "userId")).get(0);
//    String userId = ((Map<String, Object>)((GenericMessage<String>)accessor.getMessageHeaders().get("simpConnectMessage")).getHeaders().get("nativeHeaders")).get("userId");
    log.info("userId: {} connected", userId);
    sessionRepository.save(userId, accessor.getSessionId());
  }

  @EventListener
  public void handleSessionDisconnect(SessionDisconnectEvent event) {
    log.info("Session disconnect event");
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
    Objects.requireNonNull(accessor.getSessionAttributes())
        .forEach((k, v) -> System.out.println(k + v));
    sessionRepository.delete(accessor.getSessionId());
  }

  @EventListener
  public void handleSessionSubscribe(SessionSubscribeEvent event) {
    log.info("Session subscribe event");
    StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
    Objects.requireNonNull(accessor.getSessionAttributes())
        .forEach((k, v) -> System.out.println(k + v));
  }

}
