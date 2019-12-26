package com.evan.homemaking.controller;

import com.evan.homemaking.common.annotation.Authentication;
import com.evan.homemaking.common.enums.RoleEnum;
import com.evan.homemaking.common.model.param.RecruitmentParam;
import com.evan.homemaking.common.model.vo.ResponseVO;
import com.evan.homemaking.common.utils.ResponseUtil;
import com.evan.homemaking.service.RecruitmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @ClassName RecruitmentController
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 21:24
 */
@RestController
@RequestMapping("/api/recruitment")
@Authentication(RoleEnum.ADMIN)
public class RecruitmentController {
    /**
     * 信息查询模块：招聘信息的增删改查
     */
    private final RecruitmentService recruitmentService;

    public RecruitmentController(RecruitmentService recruitmentService) {
        this.recruitmentService = recruitmentService;
    }

    @PostMapping("publish")
    @ApiOperation("Publish a task")
    public ResponseEntity<ResponseVO> publish(@RequestBody @Valid RecruitmentParam recruitmentParam) {
        recruitmentService.createRecruitment(recruitmentParam);
        return ResponseUtil.successResponse();
    }
}
