package com.evan.lustre.common.model.param;

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
    @Size(min = 8, max = 50, message = "请输入正确格式的用户名，长度{min}~{max}字符之间")
    private String username;

    @NotBlank(message = "登录密码不能为空")
    @Size(min = 8, max = 50, message = "请输入正确格式的密码，长度{min}~{max}字符之间")
    private String password;
}
