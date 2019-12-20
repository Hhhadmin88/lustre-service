package com.evan.homemaking.controller;

import com.evan.homemaking.common.annotation.Authentication;
import com.evan.homemaking.common.enums.RoleEnum;
import com.evan.homemaking.common.exception.BadRequestException;
import com.evan.homemaking.common.model.param.LoginParam;
import com.evan.homemaking.common.model.param.RegisterParam;
import com.evan.homemaking.common.model.vo.ResponseVO;
import com.evan.homemaking.common.utils.ResponseUtil;
import com.evan.homemaking.service.AuthenticateService;
import com.evan.homemaking.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName UserController
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 21:22
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
@Api(tags = "User Module Interface")
public class UserController {
    /**
     * 注册模块：员工或雇主注册
     * 信息查询模块：员工信息查询
     * 用户基本信息管理模块：个人信息的增删改查 以及权限修改
     */
    private final AuthenticateService authenticateService;

    private final UserService userService;

    @PostMapping("register")
    @ApiOperation("Register an user")
    public ResponseEntity<ResponseVO> register(@RequestBody @Valid RegisterParam registerParam) {
        userService.registerUser(registerParam);
        return ResponseUtil.successResponse();
    }

    @PostMapping("login")
    @ApiOperation("Login and  authenticate")
    public ResponseEntity<ResponseVO> authenticate(@RequestBody @Valid LoginParam loginParam) {
        return ResponseUtil.successResponse(authenticateService.authenticate(loginParam));
    }

    @DeleteMapping("delete/{userId}")
    @ApiOperation("Delete a user")
    @Authentication(RoleEnum.ADMIN)
    public ResponseEntity<ResponseVO> deleteOne(@PathVariable Integer userId) {
        userService.removeById(userId);
        log.info("权限验证通过，用户删除成功,userId:{}", userId.toString());
        return ResponseUtil.successResponse();
    }

    @DeleteMapping("delete/multiple")
    @ApiOperation("Delete multiple users")
    @Authentication(RoleEnum.ADMIN)
    public ResponseEntity<ResponseVO> deleteMultiple(@RequestBody @Valid List<Integer> userIds) {
        userService.removeInBatch(userIds);
        log.info("权限验证通过，用户删除成功,userIds:{}", userIds.toString());
        return ResponseUtil.successResponse();
    }

    @DeleteMapping("delete/all")
    @ApiOperation("Delete all user")
    @Authentication(RoleEnum.ADMIN)
    public ResponseEntity<ResponseVO> deleteAll() {
        userService.removeAll();
        log.info("权限验证通过，用户全部删除成功");
        return ResponseUtil.successResponse();
    }

    @PostMapping("add")
    @ApiOperation("Add a user")
    @Authentication(RoleEnum.ADMIN)
    public ResponseEntity<ResponseVO> addUser(@RequestBody @Valid RegisterParam registerParam) {
        userService.registerUser(registerParam);
        return ResponseUtil.successResponse();
    }
}
