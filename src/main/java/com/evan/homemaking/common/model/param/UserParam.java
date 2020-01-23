package com.evan.homemaking.common.model.param;

import com.evan.homemaking.common.model.dto.base.InputConverter;
import com.evan.homemaking.common.model.entity.User;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @ClassName UserParam
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/21 2:59
 */
@Data
public class UserParam implements InputConverter<User> {

    @Size(min = 8, max = 50, message = "注册密码的字符长度必须在 {min} - {max} 之间")
    private String password;

    @Size(max = 50, message = "昵称长度不能超过 {max}")
    private String nickName;

    @Size(max = 255, message = "地址内容长度不能超过 {max}")
    private String address;

    @Pattern(regexp = "^1[3456789]\\d{9}$", message = "输入手机号码格式不正确，请重新输入")
    private String phoneNumber;

    @Email(message = "电子邮件地址的格式不正确")
    @Size(max = 50, message = "电子邮件的字符长度不能超过 {max}")
    private String email;
}
