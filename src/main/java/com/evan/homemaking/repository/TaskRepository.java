package com.evan.homemaking.repository;

import com.evan.homemaking.common.model.entity.Task;
import com.evan.homemaking.repository.base.BaseRepository;
import org.springframework.lang.NonNull;

/**
 * @ClassName TaskRepository
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 23:09
 */
public interface TaskRepository extends BaseRepository<Task, Integer> {

    /**
     * Gets a task by id.
     *
     * @param id username must not be blank.
     * @return an task.
     */
    @NonNull
    Task findTaskById(Integer id);
}
