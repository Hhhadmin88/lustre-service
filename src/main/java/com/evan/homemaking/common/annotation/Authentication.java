package com.evan.homemaking.common.annotation;

import com.evan.homemaking.common.enums.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName authentication
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 11:27
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authentication {
    Role value();
}
