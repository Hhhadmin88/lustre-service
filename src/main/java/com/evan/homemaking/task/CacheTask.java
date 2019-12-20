package com.evan.homemaking.task;

import com.evan.homemaking.common.cache.AbstractCache;
import com.evan.homemaking.common.cache.CacheWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import static com.evan.homemaking.common.consts.Security.CACHE_CHECK_EXPIRATION_INTERVAL_MILLI;

/**
 * @ClassName CacheTask
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/18 22:42
 */
@Component
@Slf4j
public class CacheTask {

    private final AbstractCache abstractCache;

    public CacheTask(AbstractCache abstractCache) {
        this.abstractCache = abstractCache;
    }

    @Async
    @Scheduled(fixedRate =CACHE_CHECK_EXPIRATION_INTERVAL_MILLI)
    void checkCacheExpire() {
        ConcurrentHashMap cache = abstractCache.getCache();
        for (Object key : cache.keySet()) {
            CacheWrapper cacheWrapper = (CacheWrapper) cache.get(key);
            Date now = new Date(System.currentTimeMillis());
            if (cacheWrapper.getExpireTime() != null && now.after(cacheWrapper.getExpireTime())) {
                cache.remove(key);
                log.info("User expiration token cache has been cleared,Cache Key:{}", key);
            }
        }
    }
}
