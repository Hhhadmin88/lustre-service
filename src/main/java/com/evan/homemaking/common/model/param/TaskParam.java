package com.evan.homemaking.common.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @ClassName TaskParam
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 19:56
 */
@Data
public class TaskParam {

    private String userName;

    @NotBlank(message = "任务内容不能为空")
    @Size(max = 255, message = "任务内容长度不能超过 {max}")
    private String content;

    @NotBlank(message = "任务标题不能为空")
    @Size(max = 50, message = "任务内容长度不能超过 {max}")
    private String title;

    private Boolean isAccepted;

    private Boolean isFinished;

    private Date createTime;

    private Date updateTime;
}
