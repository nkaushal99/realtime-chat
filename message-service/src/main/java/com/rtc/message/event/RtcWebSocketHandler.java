package com.rtc.message.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class RtcWebSocketHandler extends TextWebSocketHandler {

  private final ApplicationEventPublisher eventPublisher;

  public RtcWebSocketHandler(ApplicationEventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    // This method is called when a new WebSocket connection is established.
    // You can perform actions when a client connects here.
    System.out.println("WebSocket connection established: " + session.getId());
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message)
      throws Exception {
    // This method is called when the server receives a text message from the client.
    // Handle the incoming message here.
    String payload = message.getPayload();
    System.out.println("Received message from " + session.getId() + ": " + payload);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
      throws Exception {
    // This method is called after a WebSocket connection is closed.
    // Perform any necessary cleanup or notification here.
    System.out.println("WebSocket connection closed: " + session.getId());
  }
}
