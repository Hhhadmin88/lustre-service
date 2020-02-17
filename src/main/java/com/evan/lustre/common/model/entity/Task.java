package com.evan.lustre.common.model.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
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

    @Column(name = "status", columnDefinition = "int(4) not null")
    private Integer status;

    @CreatedDate
    @Column(name = "createTime", columnDefinition = "timestamp not null default current_timestamp")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "updateTime", columnDefinition = "timestamp not null default current_timestamp")
    private Date updateTime;
}
