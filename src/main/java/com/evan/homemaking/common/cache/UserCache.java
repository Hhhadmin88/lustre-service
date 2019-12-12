package com.evan.homemaking.common.cache;

import com.evan.homemaking.common.exception.InternalException;
import com.evan.homemaking.common.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Optional;

/**
 * @ClassName UserCache
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/6 14:11
 */
@Slf4j
@Component
public class UserCache extends AbstractCache<String, String> {

    public <T> Optional<T> getMultiple(String key, Class<T> type) {
        Assert.notNull(type, "Type must not be null");

        return get(key).map(value -> {
            try {
                return JsonUtil.jsonToObject(value, type);
            } catch (IOException e) {
                log.error("Failed to convert json to type: " + type.getName(), e);
                return null;
            }
        });
    }

    public <T> void putMultiple(@NonNull String key, @NonNull T value, long expiryTime) {
        try {
            put(key, JsonUtil.objectToJson(value), expiryTime);
        } catch (JsonProcessingException e) {
            throw new InternalException("Failed to convert " + value + " to json", e);
        }
    }

    @Override
    protected void putInternal(String key, CacheWrapper<String> cacheWrapper) {
        Assert.hasText(key, "Cache key must not be blank");
        Assert.notNull(cacheWrapper, "Cache wrapper must not be null");

        // Put the cache wrapper
        CacheWrapper<String> putCacheWrapper = CACHE_CONTAINER.put(key, cacheWrapper);

        log.debug("Put [{}] cache result: [{}], original cache wrapper: [{}]", key, putCacheWrapper, cacheWrapper);
    }

    @Override
    Optional<CacheWrapper<String>> getInternal(String key) {
        Assert.hasText(key, "Cache key must not be blank");
        return Optional.ofNullable(CACHE_CONTAINER.get(key));
    }

    @Override
    public void delete(String key) {
        Assert.hasText(key, "Cache key must not be blank");

        CACHE_CONTAINER.remove(key);
        log.debug("Removed key: [{}]", key);
    }
}
