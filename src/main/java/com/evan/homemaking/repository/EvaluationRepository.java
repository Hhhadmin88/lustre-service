package com.evan.homemaking.repository;

import com.evan.homemaking.common.model.entity.Evaluation;
import com.evan.homemaking.repository.base.BaseRepository;
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
     * Find all evaluations by employer id.
     *
     * @param employerId employer id.
     * @return evaluation list.
     */
    List<Evaluation> findAllByEmployerId(@NonNull Integer employerId);
}
