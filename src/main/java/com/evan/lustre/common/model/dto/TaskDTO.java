package com.evan.lustre.common.model.dto;

import com.evan.lustre.common.model.dto.base.OutputConverter;
import com.evan.lustre.common.model.entity.Task;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName TaskDTO
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/23 11:26
 */
@Data
public class TaskDTO implements OutputConverter<TaskDTO, Task> {

    private Integer id;

    private Integer employerId;

    private String employerName;

    private Integer employeeId;

    private String employeeName;

    private String title;

    private String content;

    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
