package com.evan.homemaking.controller;

import com.evan.homemaking.common.annotation.Authentication;
import com.evan.homemaking.common.enums.Role;
import com.evan.homemaking.common.model.param.LoginParam;
import com.evan.homemaking.common.model.param.RegisterParam;
import com.evan.homemaking.common.model.vo.ResponseVO;
import com.evan.homemaking.common.utils.ResponseUtil;
import com.evan.homemaking.service.AuthenticateService;
import com.evan.homemaking.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ClassName UserController
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 21:22
 */
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
    @ApiOperation("Register an account")
    public ResponseEntity<ResponseVO> register(@RequestBody @Valid RegisterParam registerParam) {
        userService.registerUser(registerParam);
        return ResponseUtil.successResponse();
    }

    @PostMapping("login")
    @ApiOperation("Login and  authenticate")
    public ResponseEntity<ResponseVO> authenticate(@RequestBody @Valid LoginParam loginParam) {
        return ResponseUtil.successResponse(authenticateService.authenticate(loginParam));
    }
}
