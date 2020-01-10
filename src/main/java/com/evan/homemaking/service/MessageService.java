package com.evan.homemaking.service;

import com.evan.homemaking.common.model.entity.MessageBoard;
import com.evan.homemaking.common.model.entity.Recruitment;
import com.evan.homemaking.common.model.param.MessageBoardParam;
import com.evan.homemaking.service.base.CrudService;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @ClassName MessageService
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/11 3:22
 */
public interface MessageService extends CrudService<MessageBoard, Integer> {
    /**
     * Add a message.
     *
     * @param messageBoardParam messageBoardParam.
     */
    void add(@NonNull MessageBoardParam messageBoardParam);

    /**
     * Update a message.
     *
     * @param messageBoardParam messageBoardParam.
     */
    void update(@NonNull MessageBoardParam messageBoardParam);

    /**
     * Update multiple messages.
     *
     * @param messageBoardParamList messageBoardParam list.
     */
    void updateMultiple(@NonNull List<MessageBoardParam> messageBoardParamList);

    /**
     * Delete a message.
     *
     * @param messageId message id.
     */
    void deleteOne(@NonNull Integer messageId);


    /**
     * Delete multiple messages.
     *
     * @param messageIdList message list.
     */
    void deleteMultiple(@NonNull List<Integer> messageIdList);

    /**
     * Delete all messages.
     */
    void deleteAll();
}
