package com.evan.lustre.service;

import com.evan.lustre.common.model.dto.MessageBoardDTO;
import com.evan.lustre.common.model.entity.MessageBoard;
import com.evan.lustre.common.model.param.MessageBoardParam;
import com.evan.lustre.service.base.CrudService;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MessageService
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/11 3:22
 */
public interface MessageBoardService extends CrudService<MessageBoard, Integer> {
    /**
     * Add a message.
     *
     * @param messageBoardParam messageBoardParam.
     */
    void add(@NonNull MessageBoardParam messageBoardParam);

    /**
     * Get one message.
     *
     * @param id message id.
     * @return messageBoard dto.
     */
    MessageBoardDTO getOne(@NonNull Integer id);

    /**
     * Get multiple messages.
     *
     * @param idList id list.
     * @return messageBoard dto list.
     */
    List<MessageBoardDTO> getMultiple(@NonNull List<Integer> idList);

    /**
     * Get all messages.
     *
     * @return messageBoard dto list all.
     */
    List<MessageBoardDTO> getAll();


    /**
     * Update a message.
     *
     * @param messageBoardId    message board id.
     * @param messageBoardParam messageBoardParam.
     */
    void update(@NonNull Integer messageBoardId, @NonNull MessageBoardParam messageBoardParam);

    /**
     * Update multiple messages.
     *
     * @param messageBoardParamListMap messageBoardParam list map.
     */
    void updateMultiple(@NonNull List<Map<Integer, MessageBoardParam>> messageBoardParamListMap);

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
