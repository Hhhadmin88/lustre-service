package com.evan.homemaking.service.impl;

import com.evan.homemaking.common.model.dto.EvaluationDTO;
import com.evan.homemaking.common.model.entity.Evaluation;
import com.evan.homemaking.common.model.param.EvaluationParam;
import com.evan.homemaking.common.model.vo.EvaluationVO;
import com.evan.homemaking.repository.EvaluationRepository;
import com.evan.homemaking.repository.UserRepository;
import com.evan.homemaking.service.EvaluationService;
import com.evan.homemaking.service.base.AbstractCrudService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * @ClassName EvaluationServiceImpl
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/17 17:09
 */
@Service
public class EvaluationServiceImpl extends AbstractCrudService<Evaluation, Integer> implements EvaluationService {

    private final EvaluationRepository evaluationRepository;

    private final UserRepository userRepository;

    public EvaluationServiceImpl(EvaluationRepository evaluationRepository,
                                 UserRepository userRepository) {
        super(evaluationRepository);
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void add(@NonNull EvaluationParam evaluationParam) {
        Evaluation evaluation = evaluationParam.convertTo();
        evaluationRepository.save(evaluation);
    }

    @Override
    public EvaluationVO getEvaluationsForOne(@NonNull Integer employeeId) {
        List<Evaluation> evaluationList = evaluationRepository.findAllByEmployeeId(employeeId);
        return buildEvaluationVO(employeeId, evaluationList);
    }

    @NonNull
    private EvaluationVO buildEvaluationVO(@NonNull Integer employeeId, List<Evaluation> evaluationList) {
        EvaluationVO evaluationVO = new EvaluationVO();
        List<EvaluationDTO> evaluationDTOList = evaluationList.stream()
                .map(evaluation -> convertUserIdToNickName(evaluation.getId())).collect(Collectors.toList());
        OptionalDouble averageScoreOptional = evaluationList.stream().mapToInt(Evaluation::getScore).average();
        averageScoreOptional.ifPresent(averageScore -> {
                    evaluationVO.setAverageScore(averageScoreOptional.getAsDouble());
                    evaluationVO.setReceiveEvaluations(evaluationDTOList);
                    evaluationVO.setEmployerNickName(userRepository.getOne(employeeId).getNickName());
                }
        );
        return evaluationVO;
    }

    public EvaluationDTO convertUserIdToNickName(@NonNull Integer evaluationId) {
        Optional<Evaluation> evaluationOptional = evaluationRepository.findById(evaluationId);
        EvaluationDTO evaluationDTO = new EvaluationDTO();
        evaluationOptional.ifPresent(evaluation -> {
            evaluationDTO.convertFrom(evaluation);
            userRepository.findById(evaluation.getEmployerId()).ifPresent(
                    user -> evaluationDTO.setEmployerNickName(user.getNickName())
            );
        });
        return evaluationDTO;
    }

    @Override
    public EvaluationDTO update(@NonNull Integer evaluationId, @NonNull EvaluationParam evaluationParam) {
        Optional<Evaluation> evaluationOptional = evaluationRepository.findById(evaluationId);
        EvaluationDTO evaluationDTO = new EvaluationDTO();
        evaluationOptional.ifPresent(evaluation -> {
            evaluationParam.update(evaluation);
            evaluationDTO.convertFrom(evaluation);
        });
        return evaluationDTO;
    }

    @Override
    public void deleteOne(@NonNull Integer evaluationId) {
        evaluationRepository.deleteById(evaluationId);
    }

    @Override
    public void deleteMultiple(@NonNull List<Integer> evaluationIdList) {
        evaluationRepository.deleteByIdIn(evaluationIdList);
    }

    @Override
    public void deleteAll() {
        evaluationRepository.deleteAll();
    }
}
