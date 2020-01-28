package com.evan.homemaking.common.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName User
 * @Description User information entity.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 16:40
 */
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "accountId", columnDefinition = "varchar(50) not null")
    private String accountId;

    @Column(name = "password", columnDefinition = "varchar(1024) not null")
    private String password;

    @Column(name = "nickName", columnDefinition = "varchar(50)")
    private String nickName;

    @Column(name = "address", columnDefinition = "varchar(255) not null")
    private String address;

    @Column(name = "phoneNumber", columnDefinition = "varchar(50) not null")
    private String phoneNumber;

    @Column(name = "email", columnDefinition = "varchar(50)")
    private String email;
    /**
     * This Role is the foreign key of the RoleId in the Role entity.
     */
    @Column(name = "role", columnDefinition = "varchar(50) not null")
    private String role;

    @Column(name = "averageScore", columnDefinition = "double(2,1) default null")
    private Double averageScore;
}
