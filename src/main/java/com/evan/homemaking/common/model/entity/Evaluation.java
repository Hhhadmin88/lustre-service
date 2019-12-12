package com.evan.homemaking.common.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @ClassName Evaluation
 * @Description Evaluation entity.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 18:20
 */
@Data
@Entity
@Table(name = "evaluation")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "employerId", columnDefinition = "int(32) not null")
    private Integer employerId;

    @Column(name = "employeeId", columnDefinition = "int(32) not null")
    private Integer employeeId;

    @Column(name = "content", columnDefinition = "varchar(255) not null")
    private String content;

    @Column(name = "score", columnDefinition = "int(32) not null")
    private Integer score;

}
