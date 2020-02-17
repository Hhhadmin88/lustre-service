package com.evan.lustre.common.model.dto;

import com.evan.lustre.common.model.dto.base.OutputConverter;
import com.evan.lustre.common.model.entity.MessageBoard;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName MessageBoardDTO
 * @Description MessageBoard output dto.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/11 19:53
 */
@Data
public class MessageBoardDTO implements OutputConverter<MessageBoardDTO, MessageBoard> {

    private Integer id;

    private Integer senderId;

    private String senderName;

    private Integer receiverId;

    private String receiverName;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
