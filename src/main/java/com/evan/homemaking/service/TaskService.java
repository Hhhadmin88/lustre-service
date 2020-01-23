package com.evan.homemaking.service;

import com.evan.homemaking.common.model.entity.Task;
import com.evan.homemaking.common.model.param.TaskParam;
import com.evan.homemaking.service.base.CrudService;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @ClassName TaskService
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 22:53
 */
public interface TaskService extends CrudService<Task, Integer> {

    /**
     * Publish a task.
     *
     * @param taskParam
     */
    void publish(TaskParam taskParam);

    /**
     * Delete a task.
     *
     * @param taskId
     */
    void delete(Integer taskId);

    /**
     * Delete Multiple tasks.
     *
     * @param ids
     */
    void deleteMultiple(List<Integer> ids);

    /**
     * Delete all tasks.
     */
    void deleteAll();

    /**
     * Modify information about task.
     *
     * @param taskParam
     */
    void modify(TaskParam taskParam);

    /**
     * Gets a task.
     *
     * @param id
     */
    void retrieveOne(Integer id);

    /**
     * Gets Multiple tasks.
     *
     * @param ids
     */
    void retrieveMultiple(List<Integer> ids);

    /**
     * Gets all tasks.
     */
    void retrieveAll();

    /**
     * Create a task.
     *
     * @param taskParam taskParam.
     */
    void createTask(@NonNull TaskParam taskParam);

    /**
     * Update a task.
     *
     * @param taskId    task id.
     * @param taskParam taskParam.
     */
    void updateTask(@NonNull Integer taskId, @NonNull TaskParam taskParam);

    /**
     * Update status of task.
     *
     * @param operation operation of the task status change.
     * @param taskId    task id.
     */
    void changeTaskStatus(@NonNull String operation, @NonNull Integer taskId);
}
