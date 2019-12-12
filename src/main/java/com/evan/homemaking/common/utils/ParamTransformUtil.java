package com.evan.homemaking.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName PropertyCopyUtils
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/6 0:23
 */
@Slf4j
public class ParamTransformUtil {

    public static <T> T copyProperties(Object requestParam, Class<T> entityClass) {
        T entity = null;

        try {
            entity = entityClass.newInstance();
        } catch (Exception e) {
            log.error("Param transform is failed", e.getMessage());
        }
        BeanUtils.copyProperties(requestParam, entity);
        return entity;
    }
}
