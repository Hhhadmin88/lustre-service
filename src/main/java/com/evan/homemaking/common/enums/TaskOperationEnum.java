package com.evan.homemaking.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.evan.homemaking.common.consts.TaskStatus.*;

/**
 * @ClassName TaskOperationEnum
 * @Description Task status change operation enum.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/23 13:18
 */
@AllArgsConstructor
@Getter
public enum TaskOperationEnum {
    /**
     * Accept a task.
     */
    ACCEPT(ACCEPTED_BUT_NOT_FINISHED),

    /**
     * finish a task.
     */
    FINISH(ACCEPTED_FINISHED_NOT_CONFIRMED),

    /**
     * confirm a task.
     */
    CONFIRM(HAS_BEEN_CONFIRMED);

    /**
     * Status code that should be modified
     */
    private Integer targetStatusCode;
}
