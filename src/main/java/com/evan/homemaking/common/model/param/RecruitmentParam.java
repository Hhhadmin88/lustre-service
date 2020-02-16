package com.evan.homemaking.common.model.param;

import com.evan.homemaking.common.model.dto.base.InputConverter;
import com.evan.homemaking.common.model.entity.Recruitment;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * @ClassName RecruitmentParam
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/26 20:43
 */
@Data
public class RecruitmentParam implements InputConverter<Recruitment> {

    @Min(value = 18, message = "年龄不能小于 {value}")
    private Integer ageLimitFrom;


    @Max(value = 65, message = "年龄不能大于 {value}")
    private Integer ageLimitTo;

    /**
     * 0 no constraint,1 male,2 female
     */
    @NotNull(message = "性别不能为空")
    @Max(value = 2, message = "性别标识字符不能大于 {value}")
    @Min(value = 0, message = "性别标识字符不能小于 {value}")
    private Integer genderConstraint;

    @DecimalMin(value = "0", message = "薪资不能小于 {value}")
    @DecimalMax(value = "1000000", message = "薪资不能大于 {value}")
    private BigDecimal salary;

    @Max(value = 365, message = "工作最大时间量不能大于 {value}")
    @Min(value = 1, message = "工作最小时间量能小于 {value}")
    private Integer workingTimeNumber;

    @NotBlank(message = "工作时间单位不能为空")
    @Size(min = 1, max = 1, message = "工作时间单位长度应为 {min}")
    private String timeUnit;

    @NotBlank(message = "需求内容不能为空")
    @Size(max = 255, message = "需求内容长度不能大于 {max}")
    private String requirement;
}
