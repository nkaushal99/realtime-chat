package com.rtc.message.entity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.PrePersist;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
import java.io.Serial;
import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

//  @Id
  private UUID id;

//  @Temporal(TemporalType.TIMESTAMP)
  private ZonedDateTime timestamp;

  private String text;

  private UUID parentId;

  private UUID senderParticipantId;

  private UUID receiverRoomId;

//  @PrePersist
  private void prePersist() {
    this.id = UUID.randomUUID();
    this.timestamp = ZonedDateTime.now(ZoneId.of("UTC"));
  }

}
