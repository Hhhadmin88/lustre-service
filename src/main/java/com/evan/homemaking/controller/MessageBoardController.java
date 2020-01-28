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
import java.util.Map;

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

    @PostMapping("create")
    @ApiOperation("Create a message")
    public ResponseEntity<ResponseVO> create(@RequestBody @Valid MessageBoardParam messageBoardParam) {
        messageBoardService.add(messageBoardParam);
        return ResponseUtil.successResponse();
    }

    @GetMapping("{messageBoardId:\\d+}")
    @ApiOperation("Get a message")
    public ResponseEntity<ResponseVO> getOne(@PathVariable("messageBoardId") Integer messageBoardId) {
        MessageBoardDTO messageBoardDTO = messageBoardService.getOne(messageBoardId);
        return ResponseUtil.successResponse(messageBoardDTO);
    }

    @PostMapping("/list")
    @ApiOperation("Get message list")
    public ResponseEntity<ResponseVO> getList(@RequestBody List<Integer> messageBoardIdList) {
        List<MessageBoardDTO> messageBoardDTOList = messageBoardService.getMultiple(messageBoardIdList);
        return ResponseUtil.successResponse(messageBoardDTOList);
    }

    @PostMapping("/all")
    @ApiOperation("Get all messages")
    public ResponseEntity<ResponseVO> getAll() {
        List<MessageBoardDTO> messageBoardDTOList = messageBoardService.getAll();
        return ResponseUtil.successResponse(messageBoardDTOList);
    }

    @DeleteMapping("{messageBoardId:\\d+}")
    @ApiOperation("Delete one message")
    public ResponseEntity<ResponseVO> deleteOne(@PathVariable("messageBoardId") Integer messageBoardId) {
        messageBoardService.deleteOne(messageBoardId);
        return ResponseUtil.successResponse();
    }

    @DeleteMapping("/list")
    @ApiOperation("Delete multiple message")
    public ResponseEntity<ResponseVO> deleteMultiple(@RequestBody List<Integer> messageBoardIdList) {
        messageBoardService.deleteMultiple(messageBoardIdList);
        return ResponseUtil.successResponse();
    }

    @DeleteMapping("/all")
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

    @PutMapping("/list")
    @ApiOperation("Update multiple messages")
    public ResponseEntity<ResponseVO> updateMultiple(@RequestBody List<Map<Integer, MessageBoardParam>> messageBoardParamListMap) {
        messageBoardService.updateMultiple(messageBoardParamListMap);
        return ResponseUtil.successResponse();
    }
}
