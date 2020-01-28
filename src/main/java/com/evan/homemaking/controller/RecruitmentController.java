package com.evan.homemaking.controller;

import com.evan.homemaking.common.annotation.Authentication;
import com.evan.homemaking.common.enums.RoleEnum;
import com.evan.homemaking.common.model.entity.Recruitment;
import com.evan.homemaking.common.model.param.RecruitmentParam;
import com.evan.homemaking.common.model.vo.ResponseVO;
import com.evan.homemaking.common.utils.ResponseUtil;
import com.evan.homemaking.service.RecruitmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @ApiOperation("Publish a recruitment")
    public ResponseEntity<ResponseVO> publish(@RequestBody @Valid RecruitmentParam recruitmentParam) {
        recruitmentService.createRecruitment(recruitmentParam);
        return ResponseUtil.successResponse();
    }

    @PutMapping("modify")
    @ApiOperation("Modify a recruitment")
    public ResponseEntity<ResponseVO> modify(@RequestBody @Valid RecruitmentParam recruitmentParam) {
        recruitmentService.updateRecruitment(recruitmentParam);
        return ResponseUtil.successResponse();
    }

    @DeleteMapping("{recruitmentId:\\d+}")
    @ApiOperation("Delete a recruitment")
    public ResponseEntity<ResponseVO> delete(@PathVariable Integer recruitmentId) {
        recruitmentService.deleteRecruitment(recruitmentId);
        return ResponseUtil.successResponse();
    }

    @GetMapping("all")
    @ApiOperation("Get all recruitment")
    public ResponseEntity<ResponseVO> getAll() {
        List<Recruitment> recruitmentList = recruitmentService.findAllRecruitment();
        return ResponseUtil.successResponse(recruitmentList);
    }

    @GetMapping("{recruitmentId::\\d+}")
    @ApiOperation("Get one recruitment")
    public ResponseEntity<ResponseVO> getOne(@PathVariable Integer recruitmentId) {
        Recruitment recruitment = recruitmentService.findOneRecruitment(recruitmentId);
        return ResponseUtil.successResponse(recruitment);
    }
}
