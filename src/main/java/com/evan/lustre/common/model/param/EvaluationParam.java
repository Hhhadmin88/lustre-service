package com.evan.lustre.common.model.param;

import com.evan.lustre.common.model.dto.base.InputConverter;
import com.evan.lustre.common.model.entity.Evaluation;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * @ClassName EvaluationParam
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/17 16:29
 */
@Data
public class EvaluationParam implements InputConverter<Evaluation> {

    @NotNull(message = "任务id不能为空")
    private Integer taskId;

    @NotNull(message = "雇员id不能为空")
    private Integer employeeId;

    @NotNull(message = "雇主id不能为空")
    private Integer employerId;

    @NotBlank(message = "评价内容不能为空")
    @Size(max = 255, message = "评价内容长度不能超过 {max}")
    private String content;

    @Max(value = 10, message = "评分不能大于 {value}")
    @Min(value = 1, message = "评分不能小于 {value}")
    private Integer score;
}
