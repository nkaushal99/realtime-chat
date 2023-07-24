package com.rtc.message.repository;

import java.util.Map;

public interface RtcRepository<K, V> {

  Map<K, V> findAll();

  V findByKey(K key);

  void save(K key, V value);

  V delete(K key);

}
