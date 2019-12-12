package com.evan.homemaking.service.impl;

import com.evan.homemaking.common.model.entity.Task;
import com.evan.homemaking.common.model.param.TaskParam;
import com.evan.homemaking.repository.TaskRepository;
import com.evan.homemaking.service.TaskService;
import com.evan.homemaking.service.base.AbstractCrudService;
import org.springframework.stereotype.Service;

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

    public TaskServiceImpl(TaskRepository taskRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
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
}
