package com.rtc.message.repository;

public interface SessionRepository<K, V> extends RtcRepository<K, V> {

  V findById(K userId);

  @Override
  default V findByKey(K userId) {
    return findById(userId);
  }

}
