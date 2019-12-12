package com.evan.homemaking.common.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @ClassName LoginParam
 * @Description Login param.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 10:36
 */
@Data
public class LoginParam {
    @NotBlank(message = "用户名或邮箱不能为空")
    @Size(max = 50, message = "用户名或邮箱的字符长度不能超过 {max}")
    private String username;

    @NotBlank(message = "登录密码不能为空")
    @Size(max = 50, message = "用户密码字符长度不能超过 {max}")
    private String password;
}
