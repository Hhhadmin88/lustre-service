package com.evan.homemaking.common.model.param;

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
public class MessageBoardParam {

    @NotNull(message = "评论发送者不能为空")
    private Integer sender;

    @NotNull(message = "评论接收者不能为空")
    private Integer receiver;

    @NotEmpty(message = "留言内容不能为空")
    private String content;
}
