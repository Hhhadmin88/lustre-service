package com.evan.homemaking.service;

import com.evan.homemaking.common.model.dto.TaskDTO;
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
     * @param taskParam taskParam.
     */
    void publish(TaskParam taskParam);

    /**
     * Delete a task.
     *
     * @param taskId task id.
     */
    void delete(Integer taskId);

    /**
     * Delete Multiple tasks.
     *
     * @param idList id list.
     */
    void deleteMultiple(List<Integer> idList);

    /**
     * Delete all tasks.
     */
    void deleteAll();

    /**
     * Modify information about task.
     *
     * @param taskParam taskParam.
     */
    void modify(TaskParam taskParam);

    /**
     * Get a task.
     *
     * @param taskId task id.
     * @return task dto.
     */
    Object getOne(Integer taskId);

    /**
     * Gets Multiple tasks.
     *
     * @param idList id list.
     * @return task dto list.
     */
    List<TaskDTO> getMultiple(List<Integer> idList);

    /**
     * Gets all tasks.
     *
     * @return task dto list.
     */
    List<TaskDTO> getAll();

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
     * @return task dto.
     */
    TaskDTO updateTask(@NonNull Integer taskId, @NonNull TaskParam taskParam);

    /**
     * Update status of task.
     *
     * @param operation operation of the task status change.
     * @param taskId    task id.
     */
    void changeTaskStatus(@NonNull String operation, @NonNull Integer taskId);
}
