package com.evan.homemaking.event;

import com.evan.homemaking.common.model.dto.RoleDTO;
import com.evan.homemaking.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * @ClassName InsertRoleDataEvent
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/13 21:56
 */
@Component
@Slf4j
public class InsertRoleEvent extends ContextClosedEvent {

    public InsertRoleEvent(ApplicationContext source) {
        super(source);
        RoleRepository roleRepository = source.getBean(RoleRepository.class);
        RoleDTO roleDto = source.getBean(RoleDTO.class);
        roleRepository.deleteAll();
        roleRepository.saveAll(roleDto.getRoleList());
        log.info("Role data insert success");
    }
}
