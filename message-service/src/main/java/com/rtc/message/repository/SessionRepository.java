package com.rtc.message.repository;

public interface SessionRepository<K, V> extends RtcRepository<K, V> {

  V findById(K key);

  @Override
  default V findByKey(K key) {
    return findById(key);
  }

}
