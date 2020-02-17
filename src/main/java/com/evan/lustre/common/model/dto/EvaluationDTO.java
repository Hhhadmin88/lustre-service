package com.evan.lustre.common.model.dto;

import com.evan.lustre.common.model.dto.base.OutputConverter;
import com.evan.lustre.common.model.entity.Evaluation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @ClassName EvaluationDTO
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/17 16:36
 */
@Data
public class EvaluationDTO implements OutputConverter<EvaluationDTO, Evaluation> {

    private Integer id;

    @JsonProperty("employer_name")
    private String employerNickName;

    private String content;

    private Integer score;
}
