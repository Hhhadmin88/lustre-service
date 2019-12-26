package com.evan.homemaking.common.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @ClassName Recruitment
 * @Description Recruitment entity.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 18:44
 */
@Data
@Entity
@Table(name = "recruitment")
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ageLimitFrom", columnDefinition = "int(32)")
    private Integer ageLimitFrom;

    @Column(name = "ageLimitTo", columnDefinition = "int4(32)")
    private Integer ageLimitTo;

    @Column(name = "genderConstraint", columnDefinition = "int(32)")
    private Integer genderConstraint;

    @Column(name = "salary", columnDefinition = "decimal(50) not null")
    private BigDecimal salary;

    @Column(name = "workingTimeNumber", columnDefinition = "varchar(255) not null")
    private Integer workingTimeNumber;

    @Column(name = "timeUnit", columnDefinition = "varchar(20) not null")
    private String timeUnit;

    @Column(name = "applicantsNumber", columnDefinition = "int(32) default 0")
    private Integer applicantsNumber;

    @Column(name = "requirement", columnDefinition = "varchar(255) not null")
    private String requirement;

    @Column(name = "createTime", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private String createTime;

    @Column(name = "updateTime", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private String updateTime;
}
