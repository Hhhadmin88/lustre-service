package com.evan.homemaking.event;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

/**
 * @ClassName ScoreEvent
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/20 19:55
 */
@Slf4j
@Getter
public class ScoreEvent extends ApplicationEvent {

    private final Integer employeeId;

    public ScoreEvent(Object source, Integer employeeId) {
        super(source);
        this.employeeId = employeeId;
    }
}
