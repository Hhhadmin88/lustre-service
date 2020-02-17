package com.evan.lustre.listener;

import com.evan.lustre.common.model.entity.Evaluation;
import com.evan.lustre.common.model.entity.User;
import com.evan.lustre.event.ScoreEvent;
import com.evan.lustre.repository.EvaluationRepository;
import com.evan.lustre.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.OptionalDouble;

/**
 * @ClassName ScoreEventListener
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/20 20:00
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ScoreEventListener {

    private final EvaluationRepository evaluationRepository;

    private final UserService userService;

    @EventListener
    @Async
    public void calculateAverageScore(ScoreEvent scoreEvent) {
        Integer employeeId = scoreEvent.getEmployeeId();
        List<Evaluation> evaluations = evaluationRepository.findAllByEmployeeId(employeeId);
        OptionalDouble averageScoreOptional = evaluations.stream().mapToInt(Evaluation::getScore).average();
        averageScoreOptional.ifPresent(averageScore -> {
            User employee = userService.getById(employeeId);
            employee.setAverageScore(averageScore);
            userService.update(employee);
        });
    }
}
