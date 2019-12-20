package com.evan.homemaking.common.model.param;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @ClassName RegisterParam
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 16:32
 */
@Data
public class RegisterParam {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 8, max = 50, message = "账号的字符长度必须在 {min} - {max} 之间")
    private String accountId;

    @NotBlank(message = "注册密码不能为空")
    @Size(min = 8, max = 50, message = "注册密码的字符长度必须在 {min} - {max} 之间")
    private String password;

    @NotBlank(message = "昵称不能为空")
    @Size(max = 50, message = "昵称长度不能超过 {max}")
    private String nickName;

    @NotBlank(message = "地址不能为空")
    @Size(max = 255, message = "地址内容长度不能超过 {max}")
    private String address;

    @NotBlank(message = "电话号码不能为空")
    @Size(max = 50, message = "电话号码长度不能超过 {max}")
    private String phoneNumber;

    @Email(message = "电子邮件地址的格式不正确")
    @NotBlank(message = "电子邮件地址不能为空")
    @Size(max = 50, message = "电子邮件的字符长度不能超过 {max}")
    private String email;

    @NotBlank(message = "角色标识不能为空")
    @Size(max = 1, message = "角色标识字符长度不能超过 {max}")
    private String role;
}
