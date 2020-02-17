package com.evan.lustre.common.cache;

import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * @ClassName Cache
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/6 13:43
 */
public interface Cache<K, V> {


    /**
     * Gets by cache key.
     *
     * @param key must not be null
     * @return cache value
     */
    @NonNull
    Optional<V> get(@NonNull K key);

    /**
     * Puts a cache.
     *
     * @param key        cache key must not be null
     * @param value      cache value must not be null
     * @param expiryTime expire time
     */
    void put(@NonNull K key, @NonNull V value, long expiryTime);

    /**
     * Delete a key.
     *
     * @param key cache key must not be null
     */
    void delete(@NonNull K key);
}
