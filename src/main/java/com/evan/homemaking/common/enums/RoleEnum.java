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
public enum RoleEnum {
    /**
     * Role of user.
     */
    EMPLOYEE(1, "employee"),
    EMPLOYER(2, "employer"),
    ADMIN(3, "admin");
    private Integer role;
    private String description;
}
