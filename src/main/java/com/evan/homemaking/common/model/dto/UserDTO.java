package com.evan.homemaking.common.model.dto;

import com.evan.homemaking.common.model.dto.base.OutputConverter;
import com.evan.homemaking.common.model.entity.User;
import lombok.Data;

/**
 * @ClassName UserDTO
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/21 2:53
 */
@Data
public class UserDTO implements OutputConverter<UserDTO, User> {

    private Integer id;

    private String accountId;

    private String nickName;

    private Integer gender;

    private String address;

    private String phoneNumber;

    private String email;

    private Integer role;

    private Double averageScore;
}
