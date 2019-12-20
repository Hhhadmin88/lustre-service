package com.evan.homemaking.common.consts;

/**
 * @ClassName TaskStatus
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/20 23:44
 */
public class TaskStatus {
    /**
     * Task has not accepted and not finished.
     */
    public static final Integer NOT_ACCEPTED_AND_NOT_FINISHED = 0;

    /**
     * Task has been accepted but not finished.
     */
    public static final Integer ACCEPTED_BUT_NOT_FINISHED = 1;

    /**
     * Task has been accepted and finished,but not be confirmed.
     */
    public static final Integer ACCEPTED_FINISHED_NOT_CONFIRMED = 2;

    /**
     * Task has been confirmed.
     */
    public static final Integer HAS_BEEN_CONFIRMED = 3;
}
