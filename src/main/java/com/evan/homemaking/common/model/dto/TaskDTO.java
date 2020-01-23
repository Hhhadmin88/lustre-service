package com.evan.homemaking.common.model.dto;

import com.evan.homemaking.common.model.dto.base.OutputConverter;
import com.evan.homemaking.common.model.entity.Task;
import lombok.Data;

/**
 * @ClassName TaskDTO
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/23 11:26
 */
@Data
public class TaskDTO implements OutputConverter<TaskDTO, Task> {
}
