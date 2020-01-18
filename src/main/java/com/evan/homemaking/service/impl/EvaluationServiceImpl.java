package com.evan.homemaking.service.impl;

import com.evan.homemaking.common.model.dto.EvaluationDTO;
import com.evan.homemaking.common.model.vo.EmployerEvaluationVO;
import com.evan.homemaking.common.model.entity.Evaluation;
import com.evan.homemaking.common.model.entity.User;
import com.evan.homemaking.common.model.param.EvaluationParam;
import com.evan.homemaking.repository.EvaluationRepository;
import com.evan.homemaking.repository.UserRepository;
import com.evan.homemaking.service.EvaluationService;
import com.evan.homemaking.service.UserService;
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

    private final UserService userService;


    public EvaluationServiceImpl(EvaluationRepository evaluationRepository,
                                 UserRepository userRepository,
                                 UserService userService) {
        super(evaluationRepository);
        this.evaluationRepository = evaluationRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public void add(@NonNull EvaluationParam evaluationParam) {
        Evaluation evaluation = evaluationParam.convertTo();
        evaluationRepository.save(evaluation);
    }

    @Override
    public EmployerEvaluationVO getOne(@NonNull Integer employerId) {
        EmployerEvaluationVO employerEvaluationVO = new EmployerEvaluationVO();
        List<Evaluation> evaluationList = evaluationRepository.findAllByEmployerId(employerId);
        List<EvaluationDTO> evaluationDTOList = evaluationList.stream()
                .map(evaluation -> buildEvaluationDTO(evaluation.getId())).collect(Collectors.toList());
        OptionalDouble averageScoreOptional = evaluationList.stream().mapToInt(Evaluation::getScore).average();
        double averageScore = 0;
        if (averageScoreOptional.isPresent()) {
            averageScore = averageScoreOptional.getAsDouble();
        }
        employerEvaluationVO.setReceiveEvaluations(evaluationDTOList);
        employerEvaluationVO.setAverageScore(averageScore);
        employerEvaluationVO.setEmployerNickName(userRepository.getOne(employerId).getNickName());
        return employerEvaluationVO;
    }

    public EvaluationDTO buildEvaluationDTO(@NonNull Integer evaluationId) {
        Optional<Evaluation> evaluationOptional = evaluationRepository.findById(evaluationId);
        return evaluationOptional.map(evaluation -> {
            EvaluationDTO evaluationDTO = new EvaluationDTO();
            EvaluationDTO evaluationDtoResult = evaluationDTO.convertFrom(evaluation);
            Optional<User> employee = userRepository.findById(evaluation.getEmployeeId());
            employee.ifPresent(user -> evaluationDtoResult.setEmployeeNickName(user.getNickName()));
            return evaluationDtoResult;
        }).get();
    }


    @Override
    public void update(@NonNull Integer evaluationId, @NonNull EvaluationParam evaluationParam) {

    }

    @Override
    public void deleteOne(@NonNull Integer evaluationId) {

    }

    @Override
    public void deleteMultiple(@NonNull List<Integer> evaluationIdList) {

    }

    @Override
    public void deleteAll() {

    }
}
