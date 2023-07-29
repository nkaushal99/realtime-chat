package com.rtc.message.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private String id;

  @DateTimeFormat(iso = ISO.DATE_TIME)
  private ZonedDateTime timestamp;

  private String text;

  private String parentId;

  private String senderParticipantId;

  private String receiverRoomId;
}
