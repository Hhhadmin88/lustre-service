package com.evan.homemaking.service.impl;

import com.evan.homemaking.common.model.entity.MessageBoard;
import com.evan.homemaking.common.model.param.MessageBoardParam;
import com.evan.homemaking.repository.base.BaseRepository;
import com.evan.homemaking.service.MessageService;
import com.evan.homemaking.service.base.AbstractCrudService;
import com.evan.homemaking.service.base.CrudService;
import com.google.common.util.concurrent.AbstractService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MessageServiceImpl
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/1/11 3:32
 */
@Service
public class MessageServiceImpl extends AbstractCrudService<MessageBoard, Integer> implements MessageService {

    public MessageServiceImpl(BaseRepository<MessageBoard, Integer> repository) {
        super(repository);
    }

    @Override
    public void add(@NonNull MessageBoardParam messageBoardParam) {

    }

    @Override
    public void update(@NonNull MessageBoardParam messageBoardParam) {

    }

    @Override
    public void updateMultiple(@NonNull List<MessageBoardParam> messageBoardParamList) {

    }

    @Override
    public void deleteOne(@NonNull Integer messageId) {

    }

    @Override
    public void deleteMultiple(@NonNull List<Integer> messageIdList) {

    }

    @Override
    public void deleteAll() {

    }
}
