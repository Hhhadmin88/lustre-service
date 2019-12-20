package com.evan.homemaking.handler;

import com.evan.homemaking.common.enums.RoleEnum;
import org.springframework.stereotype.Component;

/**
 * @ClassName EmployerHandler
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/20 0:47
 */
@Component
public class EmployerHandler implements Handler {
    @Override
    public boolean supports(String role) {
        return RoleEnum.EMPLOYER.getRole().equals(role);
    }

    @Override
    public Object handle(Object handler) {
        return null;
    }
}
