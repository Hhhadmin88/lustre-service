package com.evan.homemaking.handler;

import com.evan.homemaking.common.enums.RoleEnum;
import org.springframework.stereotype.Component;

/**
 * @ClassName EmployHandler
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/20 0:46
 */
@Component
public class EmployeeHandler implements Handler {
    @Override
    public boolean supports(String role) {
        return RoleEnum.EMPLOYEE.getRole().equals(role);
    }

    @Override
    public Object handle(Object handler) {
        return null;
    }
}
