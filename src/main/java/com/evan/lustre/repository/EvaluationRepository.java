package com.evan.lustre.repository;

import com.evan.lustre.common.model.entity.Evaluation;
import com.evan.lustre.repository.base.BaseRepository;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @ClassName EvaluationRepository
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/17 17:08
 */
public interface EvaluationRepository extends BaseRepository<Evaluation, Integer> {

    /**
     * Find all evaluations for this employee id.
     *
     * @param employeeId employee id.
     * @return evaluation list.
     */
    List<Evaluation> findAllByEmployeeId(@NonNull Integer employeeId);
}
