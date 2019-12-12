package com.evan.homemaking.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName Role
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/6 11:48
 */
@AllArgsConstructor
@Getter
public enum Role {
    /**
     * Role of user.
     */
    EMPLOYEE("employee"),
    EMPLOYER("employer"),
    ADMIN("admin");
    private String role;
}
