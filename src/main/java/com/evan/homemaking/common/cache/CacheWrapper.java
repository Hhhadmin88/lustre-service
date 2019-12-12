package com.evan.homemaking.common.cache;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName CacheWrapper
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/6 13:44
 */
@Data
public class CacheWrapper<V> {

    /**
     * Cache data
     */
    private V data;

    /**
     * Expired time.
     */
    private Date expireTime;

    /**
     * Create time.
     */
    private Date createTime;
}
