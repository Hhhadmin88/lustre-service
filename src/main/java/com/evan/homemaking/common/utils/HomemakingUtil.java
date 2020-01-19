package com.evan.homemaking.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;

import java.util.UUID;

/**
 * @ClassName HomemakingUtil
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/19 23:33
 */
public class HomemakingUtil {
    /**
     * Gets random uuid without dash.
     *
     * @return random uuid without dash
     */
    @NonNull
    public static String randomUUIDWithoutDash() {
        return StringUtils.remove(UUID.randomUUID().toString(), '-');
    }
}
