package com.evan.homemaking.common.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Task
 * @Description Task entity.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 18:29
 */
@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "employerId", columnDefinition = "int(32)")
    private Integer employerId;

    @Column(name = "employeeId", columnDefinition = "int(32)")
    private Integer employeeId;

    @Column(name = "title", columnDefinition = "varchar(50) not null")
    private String title;

    @Column(name = "content", columnDefinition = "varchar(255) not null")
    private String content;

    @Column(name = "isAccepted", columnDefinition = "tinyint not null")
    private Boolean isAccepted;

    @Column(name = "isFinished", columnDefinition = "tinyint not null")
    private Boolean isFinished;

    @Column(name = "createTime", columnDefinition = "varchar(50)")
    private String createTime;

    @Column(name = "updateTime", columnDefinition = "varchar(50)")
    private String updateTime;
}
