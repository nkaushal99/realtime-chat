package com.rtc.message.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class ActiveSessionInMemoryRepository<K, V> implements SessionRepository<K, V> {

  private final Map<K, V> activeSessions = new ConcurrentHashMap<>();

  @Override
  public Map<K, V> findAll() {
    return activeSessions;
  }

  @Override
  public V findById(K sessionId) {
    return activeSessions.get(sessionId);
  }

  @Override
  public void save(K sessionId, V session) {
    activeSessions.put(sessionId, session);
  }

  @Override
  public V delete(K sessionId) {
    return activeSessions.remove(sessionId);
  }

}
