package com.evan.homemaking.service.impl;

import com.evan.homemaking.common.consts.TaskStatus;
import com.evan.homemaking.common.enums.RoleEnum;
import com.evan.homemaking.common.enums.TaskOperationEnum;
import com.evan.homemaking.common.exception.BadRequestException;
import com.evan.homemaking.common.exception.NotFoundException;
import com.evan.homemaking.common.exception.UnAuthorizedException;
import com.evan.homemaking.common.model.dto.TaskDTO;
import com.evan.homemaking.common.model.entity.Task;
import com.evan.homemaking.common.model.entity.User;
import com.evan.homemaking.common.model.param.TaskParam;
import com.evan.homemaking.repository.TaskRepository;
import com.evan.homemaking.service.TaskService;
import com.evan.homemaking.service.UserService;
import com.evan.homemaking.service.base.AbstractCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.evan.homemaking.common.consts.TaskStatus.*;

/**
 * @ClassName TaskServiceImpl
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 23:07
 */
@Service
@Slf4j
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
    public void deleteMultiple(List<Integer> idList) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void modify(TaskParam taskParam) {

    }

    @Override
    public TaskDTO getOne(Integer taskId) {
        Optional<Task> taskOptional = fetchById(taskId);
        return taskOptional.map(this::setUserNameInTask)
                .orElseThrow(() -> new NotFoundException("当前taskId:" + taskId + "对应的任务不存在"));
    }

    @Override
    public List<TaskDTO> getMultiple(List<Integer> idList) {
        List<Task> taskList = listAllByIds(idList);
        return taskList.stream().map(this::setUserNameInTask).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> getAll() {
        List<Task> taskList = listAll();
        return taskList.stream().map(this::setUserNameInTask).collect(Collectors.toList());
    }

    @Override
    public void createTask(@NonNull TaskParam taskParam) {
        create(buildPersistentTask(taskParam));
    }

    @Override
    public TaskDTO updateTask(@NonNull Integer taskId, @NonNull TaskParam taskParam) {
        return taskUpdateProcessor(taskId, taskParam);
    }

    private TaskDTO taskUpdateProcessor(@NonNull Integer taskId, @NonNull TaskParam taskParam) {
        Task task = taskRepository.findTaskById(taskId);
        User currentUser = userService.getCurrentUser();
        checkOwnerOfTask(task, currentUser);
        if (RoleEnum.ADMIN.getRole().equals(currentUser.getRole())) {
            taskParam.update(task);
            update(task);
        } else if (RoleEnum.EMPLOYER.getRole().equals(currentUser.getRole())) {
            updateTaskByEmployer(task, taskParam);
        } else {
            updateTaskByEmployee(task, taskParam);
        }
        return setUserNameInTask(task);
    }

    private TaskDTO setUserNameInTask(@NonNull Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.convertFrom(task);
        String employeeName = task.getEmployeeId() == null ? null : userService.convertUserIdToName(task.getEmployeeId());
        String employerName = task.getEmployerId() == null ? null : userService.convertUserIdToName(task.getEmployerId());
        taskDTO.setEmployeeName(employeeName);
        taskDTO.setEmployerName(employerName);
        return taskDTO;
    }

    private void updateTaskByEmployer(@NonNull Task task, @NonNull TaskParam taskParam) {
        task.setContent(taskParam.getContent());
        task.setTitle(taskParam.getTitle());
        update(task);
    }

    private void updateTaskByEmployee(Task task, @NonNull TaskParam taskParam) {
        //TODO employee update function and code logic waiting design
    }


    private void checkAndUpdateStatus(@NonNull Task task, @NonNull Integer targetStatus, @NonNull User user) {
        if (task.getStatus() == targetStatus - 1) {
            task.setStatus(targetStatus);
            update(task);
        } else {
            log.error("This operate is illegal,taskParam.status:{}", targetStatus);
            throw new BadRequestException("当前对任务的状态修改不合法或任务已经提交不可修改");
        }
    }

    /**
     * Build a task persistent object.
     *
     * @param taskParam task param.
     * @return persistent task object
     */
    private Task buildPersistentTask(@NonNull TaskParam taskParam) {
        Task task = taskParam.convertTo();
        User currentUser = userService.getCurrentUser();
        if (RoleEnum.EMPLOYEE.getRole().equals(currentUser.getRole())) {
            log.error("Employee is not authorized to publish task");
            throw new UnAuthorizedException("当前用户的角色无权发布任务");
        }
        task.setEmployerId(currentUser.getId());
        task.setStatus(NOT_ACCEPTED_AND_NOT_FINISHED);
        return task;
    }

    private void checkOwnerOfTask(Task task, @NonNull User currentUser) {
        //if the task have not been accepted and current user is employee,the request will be passed.
        if (task.getEmployeeId() == null && currentUser.getRole().equals(RoleEnum.EMPLOYEE.getRole())) {
            return;
        }
        boolean result = task.getEmployerId().equals(currentUser.getId())
                || task.getEmployeeId().equals(currentUser.getId())
                || currentUser.getRole().equals(RoleEnum.ADMIN.getRole());
        if (!result) {
            log.error("The current user is not authorized to modify tasks posted by other user");
            throw new UnAuthorizedException("当前用户无权修改其他用户发布的任务");
        }
    }

    //TODO after update task need to return a taskDto.
    @Override
    public void changeTaskStatus(@NonNull String operation, @NonNull Integer taskId) {
        Task task = taskRepository.findTaskById(taskId);
        User currentUser = userService.getCurrentUser();
        checkOwnerOfTask(task, currentUser);
        TaskOperationEnum taskOperationEnum = TaskOperationEnum.valueOf(operation.toUpperCase());
        if (ACCEPTED_BUT_NOT_FINISHED.equals(taskOperationEnum.getTargetStatusCode())) {
            task.setEmployeeId(currentUser.getId());
        }
        if (HAS_BEEN_CONFIRMED.equals(taskOperationEnum.getTargetStatusCode())) {
            if (RoleEnum.EMPLOYEE.getRole().equals(currentUser.getRole())) {
                log.error("Employee is not have the right that confirm the task.");
                //when the task status is not equals 2,the task can't be confirmed.
                throw new BadRequestException("雇员无权确认任务完成");
            } else if (!task.getStatus().equals(TaskStatus.ACCEPTED_FINISHED_NOT_CONFIRMED)) {
                log.error("The status of the current task cannot be confirmed");
                //when the task status is not equals 2,the task can't be confirmed.
                throw new BadRequestException("当前任务的状态不可确认");
            }
        }

        checkAndUpdateStatus(task, taskOperationEnum.getTargetStatusCode(), currentUser);
    }
}
