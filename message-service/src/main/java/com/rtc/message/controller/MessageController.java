package com.rtc.message.controller;

import com.rtc.message.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MessageController {

  @MessageMapping("/chat/{roomId}")
  @SendTo("/topic/{roomId}")
  public MessageDTO sendMessage(MessageDTO message, @DestinationVariable String roomId) {
    log.info("Message received for roomId: {}", roomId);
    return message;
  }

  @SubscribeMapping("/topic/{roomId}")
  public String subscribeRoom(@DestinationVariable String roomId) {
    return "Subscribed to room: " + roomId;
  }
}
