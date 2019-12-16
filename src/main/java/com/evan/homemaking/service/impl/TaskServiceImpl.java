package com.evan.homemaking.service.impl;

import cn.hutool.core.date.DateUtil;
import com.evan.homemaking.common.model.entity.Task;
import com.evan.homemaking.common.model.entity.User;
import com.evan.homemaking.common.model.param.TaskParam;
import com.evan.homemaking.common.utils.ParamTransformUtil;
import com.evan.homemaking.repository.TaskRepository;
import com.evan.homemaking.repository.base.BaseRepository;
import com.evan.homemaking.security.context.SecurityContextHolder;
import com.evan.homemaking.service.TaskService;
import com.evan.homemaking.service.UserService;
import com.evan.homemaking.service.base.AbstractCrudService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName TaskServiceImpl
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 23:07
 */
@Service
public class TaskServiceImpl extends AbstractCrudService<Task, Integer> implements TaskService {

    private final TaskRepository taskRepository;

    private final UserService userService;

    public TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
        super(taskRepository);
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    public void publish(TaskParam taskParam) {

    }

    @Override
    public void delete(Integer taskId) {

    }

    @Override
    public void deleteMultiple(List<Integer> ids) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void modify(TaskParam taskParam) {

    }

    @Override
    public void retrieveOne(Integer id) {

    }

    @Override
    public void retrieveMultiple(List<Integer> ids) {

    }

    @Override
    public void retrieveAll() {

    }

    @Override
    public void createTask(@NonNull TaskParam taskParam) {
        create(buildPersistentTask(taskParam));
    }

    /**
     * Build a task persistent object.
     *
     * @param taskParam task param.
     * @return persistent task object
     */
    public Task buildPersistentTask(TaskParam taskParam) {
        Task task = ParamTransformUtil.copyProperties(taskParam, Task.class);
        User user = userService.getCurrentUser();
        task.setEmployerId(user.getId());
        task.setCreateTime(DateUtil.now());
        task.setIsAccepted(false);
        task.setIsFinished(false);
        return task;
    }
}
