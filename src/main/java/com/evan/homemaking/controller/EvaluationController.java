package com.evan.homemaking.controller;

import com.evan.homemaking.common.model.dto.MessageBoardDTO;
import com.evan.homemaking.common.model.param.EvaluationParam;
import com.evan.homemaking.common.model.param.MessageBoardParam;
import com.evan.homemaking.common.model.vo.EmployerEvaluationVO;
import com.evan.homemaking.common.model.vo.ResponseVO;
import com.evan.homemaking.common.utils.ResponseUtil;
import com.evan.homemaking.service.EvaluationService;
import com.evan.homemaking.service.MessageBoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName EvaluationController
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 21:25
 */
@RestController
@RequestMapping("/api/evaluation")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EvaluationController {
    /**
     * 用户留言管理模块：对员工的留言增删改查
     */
    private final EvaluationService evaluationService;

    @PostMapping("/create")
    @ApiOperation("Create a evaluation")
    public ResponseEntity<ResponseVO> create(@RequestBody @Valid EvaluationParam evaluationParam) {
        evaluationService.add(evaluationParam);
        return ResponseUtil.successResponse();
    }

    @GetMapping("/get/{id:\\d+}")
    @ApiOperation("Get a evaluation")
    public ResponseEntity<ResponseVO> getOne(@PathVariable Integer id) {
        return ResponseUtil.successResponse(evaluationService.getOne(id));
    }
}

