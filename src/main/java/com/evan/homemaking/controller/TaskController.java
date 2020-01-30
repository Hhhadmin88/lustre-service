package com.evan.homemaking.controller;

import com.evan.homemaking.common.annotation.Authentication;
import com.evan.homemaking.common.enums.RoleEnum;
import com.evan.homemaking.common.model.param.TaskParam;
import com.evan.homemaking.common.model.vo.ResponseVO;
import com.evan.homemaking.common.utils.ResponseUtil;
import com.evan.homemaking.service.TaskService;
import com.evan.homemaking.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Api(tags = "Task Module Interface")
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

    @GetMapping("{taskId:\\d+}")
    @ApiOperation("Get a task")
    public ResponseEntity<ResponseVO> getOne(@PathVariable Integer taskId) {
        return ResponseUtil.successResponse(taskService.getOne(taskId));
    }

    @GetMapping("multiple")
    @ApiOperation("Get multiple tasks")
    public ResponseEntity<ResponseVO> getMultiple(@RequestBody List<Integer> taskIdList) {
        return ResponseUtil.successResponse(taskService.getMultiple(taskIdList));
    }

    @GetMapping("all")
    @ApiOperation("Get all tasks")
    public ResponseEntity<ResponseVO> getAll() {
        return ResponseUtil.successResponse(taskService.getAll());
    }

    @DeleteMapping("{taskId:\\d+}")
    @ApiOperation("delete a task")
    public ResponseEntity<ResponseVO> deleteOne(@PathVariable Integer taskId) {
        taskService.removeById(taskId);
        log.info("验证通过，任务删除成功,taskId:{}", taskId);
        return ResponseUtil.successResponse();
    }

    @DeleteMapping("list")
    @ApiOperation("delete multiple tasks")
    @Authentication(RoleEnum.ADMIN)
    public ResponseEntity<ResponseVO> deleteMultiple(@RequestBody @Valid List<Integer> taskIds) {
        taskService.removeInBatch(taskIds);
        log.info("验证通过，任务删除成功,taskIds:{}", taskIds.toString());
        return ResponseUtil.successResponse();
    }

    @DeleteMapping("all")
    @ApiOperation("delete all tasks")
    @Authentication(RoleEnum.ADMIN)
    public ResponseEntity<ResponseVO> deleteAll() {
        taskService.removeAll();
        log.info("验证通过，任务全部删除成功");
        return ResponseUtil.successResponse();
    }

    @PutMapping("{taskId:\\d+}")
    @ApiOperation("modify task information")
    public ResponseEntity<ResponseVO> modify(@PathVariable Integer taskId, @RequestBody @Valid TaskParam taskParam) {
        return ResponseUtil.successResponse(taskService.updateTask(taskId, taskParam));
    }

    @PutMapping("status/{operation}/{taskId}")
    @ApiOperation("modify task information")
    public ResponseEntity<ResponseVO> changeStatus(@PathVariable String operation, @PathVariable Integer taskId) {
        taskService.changeTaskStatus(operation, taskId);
        return ResponseUtil.successResponse();
    }
}
