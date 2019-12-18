package com.evan.homemaking.controller;

import cn.hutool.core.date.DateUtil;
import com.evan.homemaking.common.annotation.Authentication;
import com.evan.homemaking.common.enums.RoleEnum;
import com.evan.homemaking.common.model.entity.Task;
import com.evan.homemaking.common.model.entity.User;
import com.evan.homemaking.common.model.param.TaskParam;
import com.evan.homemaking.common.model.vo.ResponseVO;
import com.evan.homemaking.common.utils.ParamTransformUtil;
import com.evan.homemaking.common.utils.ResponseUtil;
import com.evan.homemaking.service.TaskService;
import com.evan.homemaking.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName TaskController
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 21:26
 */
@AllArgsConstructor
@RequestMapping("/api/task")
@RestController
@Slf4j
public class TaskController {
    /**
     * 任务信息管理模块：任务信息的发布，查询，修改
     */
    private final TaskService taskService;

    private final UserService userService;

    @PostMapping("publish")
    @ApiOperation("publish a task")
    public ResponseEntity<ResponseVO> publish(@RequestBody @Valid TaskParam taskParam) {
        taskService.createTask(taskParam);
        return ResponseUtil.successResponse();
    }

    @DeleteMapping("delete/{taskId}")
    @ApiOperation("delete a task")
    public ResponseEntity<ResponseVO> deleteOne(@PathVariable Integer taskId) {
        taskService.removeById(taskId);
        log.info("验证通过，任务删除成功,taskId:{}", taskId);
        return ResponseUtil.successResponse();
    }

    @DeleteMapping("delete/multiple")
    @ApiOperation("delete multiple tasks")
    @Authentication(RoleEnum.ADMIN)
    public ResponseEntity<ResponseVO> deleteMultiple(@RequestBody @Valid List<Integer> taskIds) {
        taskService.removeInBatch(taskIds);
        log.info("验证通过，任务删除成功,taskIds:{}", taskIds.toString());
        return ResponseUtil.successResponse();
    }

    @DeleteMapping("delete/all")
    @ApiOperation("delete all tasks")
    @Authentication(RoleEnum.ADMIN)
    public ResponseEntity<ResponseVO> deleteAll() {
        taskService.removeAll();
        log.info("验证通过，任务全部删除成功");
        return ResponseUtil.successResponse();
    }

    @PutMapping("modify")
    @ApiOperation("modify task information")
    @Authentication(RoleEnum.ADMIN)
    public ResponseEntity<ResponseVO> modify(@RequestBody @Valid TaskParam taskParam) {
        User currentUser = userService.getCurrentUser();
        long updateTimestamp = System.currentTimeMillis();
        Task task = ParamTransformUtil.copyProperties(taskParam, Task.class);
        task.setUpdateTime(DateUtil.now());
        taskService.update(task);
        return ResponseUtil.successResponse();
    }
}
