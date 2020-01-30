package com.evan.homemaking.common.model.dto;

import com.evan.homemaking.common.model.dto.base.OutputConverter;
import com.evan.homemaking.common.model.entity.Recruitment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName RecruitmentDTO
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/28 23:09
 */
@Data
public class RecruitmentDTO implements OutputConverter<RecruitmentDTO, Recruitment> {

    private Integer id;

    private Integer ageLimitFrom;

    private Integer ageLimitTo;

    private Integer genderConstraint;

    private BigDecimal salary;

    private Integer workingTimeNumber;

    private String timeUnit;

    private Integer applicantsNumber;

    private String requirement;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
