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
  public V findById(K userId) {
    return activeSessions.get(userId);
  }

  @Override
  public void save(K userId, V sessionId) {
    activeSessions.put(userId, sessionId);
  }

  @Override
  public void delete(K userId) {
    activeSessions.remove(userId);
  }

}
