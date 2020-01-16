package com.evan.homemaking.controller;

import com.evan.homemaking.common.model.dto.MessageBoardDTO;
import com.evan.homemaking.common.model.param.MessageBoardParam;
import com.evan.homemaking.common.model.vo.ResponseVO;
import com.evan.homemaking.common.utils.ResponseUtil;
import com.evan.homemaking.service.MessageBoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName MessageBoardController
 * @Description MessageBoard controller.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/4 21:23
 */
@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MessageBoardController {
    /**
     * 用户留言管理模块：对员工的留言增删改查
     */
    private final MessageBoardService messageBoardService;

    @PostMapping("/create")
    @ApiOperation("Create a message")
    public ResponseEntity<ResponseVO> create(@RequestBody @Valid MessageBoardParam messageBoardParam) {
        messageBoardService.add(messageBoardParam);
        return ResponseUtil.successResponse();
    }

    @GetMapping("/get/{id}")
    @ApiOperation("Get a message")
    public ResponseEntity<ResponseVO> getOne(@PathVariable Integer id) {
        MessageBoardDTO messageBoardDTO = messageBoardService.getOne(id);
        return ResponseUtil.successResponse(messageBoardDTO);
    }

    @PostMapping("/list")
    @ApiOperation("Get message list")
    public ResponseEntity<ResponseVO> getList(@RequestBody List<Integer> idList) {
        List<MessageBoardDTO> messageBoardDTOList = messageBoardService.getMultiple(idList);
        return ResponseUtil.successResponse(messageBoardDTOList);
    }

    @PostMapping("/list/all")
    @ApiOperation("Get all messages")
    public ResponseEntity<ResponseVO> getAll() {
        List<MessageBoardDTO> messageBoardDTOList = messageBoardService.getAll();
        return ResponseUtil.successResponse(messageBoardDTOList);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete one message")
    public ResponseEntity<ResponseVO> getAll(@PathVariable Integer id) {
        messageBoardService.deleteOne(id);
        return ResponseUtil.successResponse();
    }

    @DeleteMapping("/delete/list")
    @ApiOperation("Delete multiple message")
    public ResponseEntity<ResponseVO> getAll(@RequestBody List<Integer> idList) {
        messageBoardService.deleteMultiple(idList);
        return ResponseUtil.successResponse();
    }

    @DeleteMapping("/delete/all")
    @ApiOperation("Delete all message")
    public ResponseEntity<ResponseVO> deleteAll() {
        messageBoardService.deleteAll();
        return ResponseUtil.successResponse();
    }

    @PutMapping("{id:\\d+}")
    @ApiOperation("Update a message")
    public ResponseEntity<ResponseVO> updateOne(@PathVariable("id") Integer id, @RequestBody MessageBoardParam messageBoardParam) {
        messageBoardService.update(id, messageBoardParam);
        return ResponseUtil.successResponse();
    }

    @PutMapping("/update/list")
    @ApiOperation("Update multiple messages")
    public ResponseEntity<ResponseVO> updateMultiple(@RequestBody List<MessageBoardParam> messageBoardParamList) {
        messageBoardService.updateMultiple(messageBoardParamList);
        return ResponseUtil.successResponse();
    }
}
