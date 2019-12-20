package com.evan.homemaking.handler;

import com.evan.homemaking.common.enums.RoleEnum;
import org.springframework.stereotype.Component;

/**
 * @ClassName AdminHandler
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/20 0:46
 */
@Component
public class AdminHandler implements Handler {
    @Override
    public boolean supports(String role) {
        return RoleEnum.ADMIN.getRole().equals(role);
    }

    @Override
    public Object handle(Object handler) {
        return null;
    }
}
