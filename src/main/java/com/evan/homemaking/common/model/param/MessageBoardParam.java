package com.evan.homemaking.common.model.param;

import com.evan.homemaking.common.model.dto.base.InputConverter;
import com.evan.homemaking.common.model.entity.MessageBoard;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @ClassName MessageBoardParam
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/11 2:35
 */
@Data
public class MessageBoardParam implements InputConverter<MessageBoard> {

    @NotNull(message = "评论发送者Id不能为空")
    private Integer senderId;

    @NotNull(message = "评论接收者Id不能为空")
    private Integer receiverId;

    @NotEmpty(message = "留言内容不能为空")
    private String content;
}
