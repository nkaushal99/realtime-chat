package com.rtc.message.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import org.springframework.web.socket.WebSocketSession;

@Repository
public class ActiveSessionInMemoryRepository implements
    SessionRepository<String, WebSocketSession> {

  private final Map<String, WebSocketSession> activeSessions = new ConcurrentHashMap<>();

  @Override
  public Map<String, WebSocketSession> findAll() {
    return activeSessions;
  }

  @Override
  public WebSocketSession findById(String sessionId) {
    return activeSessions.get(sessionId);
  }

  @Override
  public void save(String sessionId, WebSocketSession session) {
    activeSessions.put(sessionId, session);
  }

  @Override
  public WebSocketSession delete(String sessionId) {
    return activeSessions.remove(sessionId);
  }

}
