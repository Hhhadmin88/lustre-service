package com.evan.homemaking.common.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AbstractCache
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/6 13:56
 */
@Slf4j
public abstract class AbstractCache<K, V> implements Cache<K, V> {

    protected final ConcurrentHashMap<K, CacheWrapper<V>> CACHE_CONTAINER = new ConcurrentHashMap<>();


    @Override
    public Optional<V> get(K key) {
        Assert.notNull(key, "Cache key must not be blank");
        return getInternal(key).map(cacheWrapper -> {
            // Check expiration
            if (cacheWrapper.getExpireTime() != null && cacheWrapper.getExpireTime().before(new Date())) {
                // Expired then delete it
                log.warn("Cache key: [{}] has been expired", key);
                // Delete the key
                delete(key);
                // Return null
                return null;
            }
            return cacheWrapper.getData();
        });
    }

    @Override
    public void put(@NonNull K key, @NonNull V value, long expiryTime) {
        putInternal(key, buildCacheWrapper(value, expiryTime));
    }

    /**
     * Get cache wrapper by key.
     *
     * @param key key must not be null
     * @return an optional cache wrapper
     */
    @NonNull
    abstract Optional<CacheWrapper<V>> getInternal(@NonNull K key);

    /**
     * Puts the cache wrapper.
     *
     * @param key          key must not be null
     * @param cacheWrapper cache wrapper must not be null
     */
    protected abstract void putInternal(@NonNull K key, @NonNull CacheWrapper<V> cacheWrapper);

    @NonNull
    private CacheWrapper<V> buildCacheWrapper(@NonNull V value, long timeout) {
        Assert.notNull(value, "Cache value must not be null");
        Assert.isTrue(timeout >= 0, "Cache expiration timeout must not be less than 1");

        // Build cache wrapper
        CacheWrapper<V> cacheWrapper = new CacheWrapper<>();
        cacheWrapper.setCreateTime(new Date());
        long timeoutMillis = TimeUnit.SECONDS.toMillis(timeout);
        cacheWrapper.setExpireTime(new Date(System.currentTimeMillis() + timeoutMillis));
        cacheWrapper.setData(value);

        return cacheWrapper;
    }

    public ConcurrentHashMap<K, CacheWrapper<V>> getCache() {
        return CACHE_CONTAINER;
    }
}
