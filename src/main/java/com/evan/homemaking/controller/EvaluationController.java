package com.evan.homemaking.controller;

import com.evan.homemaking.common.model.param.EvaluationParam;
import com.evan.homemaking.common.model.vo.ResponseVO;
import com.evan.homemaking.common.utils.ResponseUtil;
import com.evan.homemaking.service.EvaluationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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


    @GetMapping("{id:\\d+}")
    @ApiOperation("Get a evaluation")
    public ResponseEntity<ResponseVO> getOne(@PathVariable Integer id) {
        return ResponseUtil.successResponse(evaluationService.getEvaluationsForOne(id));
    }

    @PutMapping("{id:\\d+}")
    @ApiOperation("Update a evaluation")
    public ResponseEntity<ResponseVO> updateOne(@PathVariable Integer id, @NonNull EvaluationParam evaluationParam) {
        return ResponseUtil.successResponse(evaluationService.update(id, evaluationParam));
    }

    @DeleteMapping("{id:\\d+}")
    @ApiOperation("Delete a evaluation")
    public ResponseEntity<ResponseVO> deleteOne(@PathVariable Integer id) {
        evaluationService.deleteOne(id);
        return ResponseUtil.successResponse();
    }

    @DeleteMapping("delete/list")
    @ApiOperation("Delete multiple evaluations")
    public ResponseEntity<ResponseVO> deleteMultiple(@NonNull List<Integer> idList) {
        evaluationService.deleteMultiple(idList);
        return ResponseUtil.successResponse();
    }

    @DeleteMapping("delete/all")
    @ApiOperation("Delete all evaluations")
    public ResponseEntity<ResponseVO> deleteAll() {
        evaluationService.deleteAll();
        return ResponseUtil.successResponse();
    }
}

