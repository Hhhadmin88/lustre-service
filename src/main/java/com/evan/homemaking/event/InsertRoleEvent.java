package com.evan.homemaking.event;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import com.evan.homemaking.common.model.entity.Role;
import com.evan.homemaking.common.utils.JsonUtil;
import com.evan.homemaking.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

        //ResourceUtils.getFile()
        ClassPathResource classPathResource = new ClassPathResource("init/role.json");
        List<Role> roleList = null;
        try {
            File roleJsonFile = classPathResource.getFile();
            String roleJson = FileUtil.readString(roleJsonFile, CharsetUtil.UTF_8);
            roleList = JsonUtil.jsonArrayToList(roleJson, Role.class);
        } catch (IOException e) {
            log.error("Read init role.json is failed");
            e.printStackTrace();
        }
        if (roleList != null) {
            roleRepository.deleteAll();
            roleRepository.saveAll(roleList);
            log.info("Role data insert success");
        }
    }
}
