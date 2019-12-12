package com.evan.homemaking.common.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "ageLimitFrom", columnDefinition = "varchar(50) not null")
    private String ageLimitFrom;

    @Column(name = "ageLimitTo", columnDefinition = "varchar(50) not null")
    private String ageLimitTo;

    @Column(name = "genderConstraint", columnDefinition = "int(4) not null")
    private Integer genderConstraint;

    @Column(name = "salary", columnDefinition = "varchar(50) not null")
    private String salary;

    @Column(name = "workingTime", columnDefinition = "varchar(255) not null")
    private String workingTime;

    @Column(name = "numberOfApplicants", columnDefinition = "int default 0")
    private Integer numberOfApplicants;

    @Column(name = "requirement", columnDefinition = "varchar(255) not null")
    private String requirement;

    @Column(name = "createTime", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date createTime;

    @Column(name = "updateTime", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date updateTime;
}
