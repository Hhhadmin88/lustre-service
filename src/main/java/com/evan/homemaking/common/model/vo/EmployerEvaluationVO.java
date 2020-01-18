package com.evan.homemaking.common.model.vo;

import com.evan.homemaking.common.model.dto.EvaluationDTO;
import com.evan.homemaking.common.model.dto.base.OutputConverter;
import com.evan.homemaking.common.model.entity.Evaluation;
import lombok.Data;

import java.util.List;

/**
 * @ClassName EvaluationDTO
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/17 16:36
 */
@Data
public class EmployerEvaluationVO {

    private String employerNickName;

    private List<EvaluationDTO> receiveEvaluations;

    private Double averageScore;
}
