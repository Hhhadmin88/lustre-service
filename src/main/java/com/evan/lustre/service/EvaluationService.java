package com.evan.lustre.service;

import com.evan.lustre.common.model.dto.EvaluationDTO;
import com.evan.lustre.common.model.entity.Evaluation;
import com.evan.lustre.common.model.param.EvaluationParam;
import com.evan.lustre.common.model.vo.EvaluationVO;
import com.evan.lustre.service.base.CrudService;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @ClassName EvaluationService
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/17 16:55
 */
public interface EvaluationService extends CrudService<Evaluation, Integer> {
    /**
     * Add a evaluation param.
     *
     * @param evaluationParam evaluation param.
     */
    void add(@NonNull EvaluationParam evaluationParam);

    /**
     * Get all evaluations for one employee.
     *
     * @param employeeId employeeId.
     * @return evaluationVO.
     */
    EvaluationVO getEvaluationsForOne(@NonNull Integer employeeId);

    /**
     * Get all evaluations.
     *
     * @return all evaluations.
     */
    @NonNull
    List<Evaluation> getAll();

    /**
     * Update a evaluation.
     *
     * @param evaluationId    evaluation id.
     * @param evaluationParam evaluation param.
     * @return evaluation dto.
     */
    EvaluationDTO update(@NonNull Integer evaluationId, @NonNull EvaluationParam evaluationParam);

    /**
     * Delete a evaluation.
     *
     * @param evaluationId evaluation id.
     */
    void deleteOne(@NonNull Integer evaluationId);


    /**
     * Delete multiple evaluations.
     *
     * @param evaluationIdList evaluation id list.
     */
    void deleteMultiple(@NonNull List<Integer> evaluationIdList);

    /**
     * Delete all evaluations.
     */
    void deleteAll();
}
