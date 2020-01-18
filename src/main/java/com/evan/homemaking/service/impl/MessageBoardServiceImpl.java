package com.evan.homemaking.service.impl;

import com.evan.homemaking.common.model.dto.MessageBoardDTO;
import com.evan.homemaking.common.model.dto.base.InputConverter;
import com.evan.homemaking.common.model.entity.MessageBoard;
import com.evan.homemaking.common.model.entity.User;
import com.evan.homemaking.common.model.param.MessageBoardParam;
import com.evan.homemaking.repository.MessageBoardRepository;
import com.evan.homemaking.repository.UserRepository;
import com.evan.homemaking.service.MessageBoardService;
import com.evan.homemaking.service.base.AbstractCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName MessageServiceImpl
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/11 3:32
 */
@Service
@Slf4j
public class MessageBoardServiceImpl extends AbstractCrudService<MessageBoard, Integer> implements MessageBoardService {

    private final MessageBoardRepository messageBoardRepository;

    private final UserRepository userRepository;

    public MessageBoardServiceImpl(MessageBoardRepository messageBoardRepository, UserRepository userRepository) {
        super(messageBoardRepository);
        this.messageBoardRepository = messageBoardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void add(@NonNull MessageBoardParam messageBoardParam) {
        MessageBoard messageBoard = messageBoardParam.convertTo();
        messageBoardRepository.save(messageBoard);
    }

    @Override
    public MessageBoardDTO getOne(@NonNull Integer messageBoardId) {
        return buildMessageBoardDTO(messageBoardId);
    }

    @Override
    public List<MessageBoardDTO> getMultiple(@NonNull List<Integer> messageBoardIdList) {
        return messageBoardIdList.stream().map(this::buildMessageBoardDTO).collect(Collectors.toList());
    }

    @Override
    public List<MessageBoardDTO> getAll() {
        List<MessageBoard> messageBoardList = messageBoardRepository.findAll();
        return messageBoardList.stream().
                map(messageBoard -> buildMessageBoardDTO(messageBoard.getId())).collect(Collectors.toList());
    }

    @NonNull
    private MessageBoardDTO buildMessageBoardDTO(Integer messageBoardId) {
        Assert.notNull(messageBoardId, "MessageBoardId must not be null");

        Optional<MessageBoard> messageBoardOptional = messageBoardRepository.findById(messageBoardId);
        return messageBoardOptional.map(messageBoard -> {
            MessageBoardDTO messageBoardDTO = new MessageBoardDTO();
            messageBoardDTO.convertFrom(messageBoard);
            String senderName = userRepository.getOne(messageBoard.getSenderId()).getNickName();
            String receiverName = userRepository.getOne(messageBoard.getReceiverId()).getNickName();
            messageBoardDTO.setSenderName(senderName);
            messageBoardDTO.setReceiverName(receiverName);
            return messageBoardDTO;
        }).get();
    }

    @Override
    public void update(@NonNull Integer messageBoardId, @NonNull MessageBoardParam messageBoardParam) {
        Optional<MessageBoard> messageBoardOptional = messageBoardRepository.findById(messageBoardId);
        messageBoardOptional.map(messageBoard -> {
            messageBoardParam.update(messageBoard);
            messageBoardRepository.saveAndFlush(messageBoard);
            return messageBoard;
        });
    }

    @Override
    public void updateMultiple(@NonNull List<Map<Integer, MessageBoardParam>> messageBoardParamListMap) {
        List<MessageBoard> messageBoardList = new ArrayList<>();
        messageBoardParamListMap.stream().peek(messageBoardParamMap ->
                messageBoardParamMap.forEach((id, messageBoardParam) -> {
                    Optional<MessageBoard> messageBoard = messageBoardRepository.findById(id);
                    messageBoard.ifPresent(messageBoardInternal -> {
                        messageBoardParam.update(messageBoardInternal);
                        messageBoardList.add(messageBoardInternal);
                    });
                }));
        updateInBatch(messageBoardList);
    }

    @Override
    public void deleteOne(@NonNull Integer messageBoardId) {
        removeById(messageBoardId);
    }

    @Override
    public void deleteMultiple(@NonNull List<Integer> messageBoardIdList) {
        removeInBatch(messageBoardIdList);
    }

    @Override
    public void deleteAll() {
        removeAll();
    }
}
