package com.evan.homemaking.handler;

/**
 * @ClassName Handler
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/20 1:01
 */
public interface Handler {
    boolean supports(String role);

    Object handle(Object handler);
}
