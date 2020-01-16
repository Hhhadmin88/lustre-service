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

import java.util.List;
import java.util.Optional;
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
    public MessageBoardDTO getOne(@NonNull Integer id) {
        return buildMessageBoardDTO(id);
    }

    @Override
    public List<MessageBoardDTO> getMultiple(@NonNull List<Integer> idList) {
        return idList.stream().map(this::buildMessageBoardDTO).collect(Collectors.toList());
    }

    @Override
    public List<MessageBoardDTO> getAll() {
        List<MessageBoard> messageBoardList = messageBoardRepository.findAll();
        return messageBoardList.stream().
                map(messageBoard -> buildMessageBoardDTO(messageBoard.getId())).collect(Collectors.toList());
    }

    @NonNull
    private MessageBoardDTO buildMessageBoardDTO(Integer id) {
        Assert.notNull(id, "Message id must not be null");

        MessageBoard messageBoard = messageBoardRepository.getOne(id);
        Integer senderId = messageBoard.getSenderId();
        Integer receiverId = messageBoard.getReceiverId();
        User sender = userRepository.getOne(senderId);
        User receiver = userRepository.getOne(receiverId);

        MessageBoardDTO messageBoardDTO = new MessageBoardDTO();
        messageBoardDTO.convertFrom(messageBoard);
        messageBoardDTO.setSenderName(sender.getNickName());
        messageBoardDTO.setReceiverName(receiver.getNickName());
        return messageBoardDTO;
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
    public void updateMultiple(@NonNull List<MessageBoardParam> messageBoardParamList) {
        List<MessageBoard> messageBoardList = messageBoardParamList.stream()
                .map(InputConverter::convertTo).collect(Collectors.toList());
        updateInBatch(messageBoardList);
    }

    @Override
    public void deleteOne(@NonNull Integer messageId) {
        removeById(messageId);
    }

    @Override
    public void deleteMultiple(@NonNull List<Integer> messageIdList) {
        removeInBatch(messageIdList);
    }

    @Override
    public void deleteAll() {
        removeAll();
    }
}
