package com.evan.lustre.service.impl;

import com.evan.lustre.common.model.dto.EvaluationDTO;
import com.evan.lustre.common.model.entity.Evaluation;
import com.evan.lustre.common.model.entity.User;
import com.evan.lustre.common.model.param.EvaluationParam;
import com.evan.lustre.common.model.vo.EvaluationVO;
import com.evan.lustre.event.ScoreEvent;
import com.evan.lustre.repository.EvaluationRepository;
import com.evan.lustre.repository.UserRepository;
import com.evan.lustre.service.EvaluationService;
import com.evan.lustre.service.base.AbstractCrudService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    private final ApplicationEventPublisher eventPublisher;

    public EvaluationServiceImpl(EvaluationRepository evaluationRepository,
                                 UserRepository userRepository,
                                 ApplicationEventPublisher eventPublisher) {
        super(evaluationRepository);
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void add(@NonNull EvaluationParam evaluationParam) {
        Evaluation evaluation = evaluationRepository.save(evaluationParam.convertTo());
        eventPublisher.publishEvent(new ScoreEvent(this, evaluation.getEmployeeId()));
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

        User employee = userRepository.getOne(employeeId);
        evaluationVO.setAverageScore(employee.getAverageScore());
        evaluationVO.setEmployerNickName(employee.getNickName());
        evaluationVO.setReceiveEvaluations(evaluationDTOList);

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

    @NonNull
    @Override
    public List<Evaluation> getAll() {
        return listAll();
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
