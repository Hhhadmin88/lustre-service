package com.evan.homemaking.common.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName LiveMessage
 * @Description Message board entity.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 18:05
 */
@Data
@Entity
@Table(name = "messageboard")
public class MessageBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sender", columnDefinition = "varchar(50) not null")
    private String sender;

    @Column(name = "receiver", columnDefinition = "varchar(50) not null")
    private String receiver;

    @Column(name = "content", columnDefinition = "varchar(50) not null")
    private String content;

    @Column(name = "createTime", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date createTime;

    @Column(name = "updateTime", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Date updateTime;
}
