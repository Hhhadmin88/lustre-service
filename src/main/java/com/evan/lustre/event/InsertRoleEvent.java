package com.evan.lustre.event;

import cn.hutool.core.io.resource.InputStreamResource;
import com.evan.lustre.common.model.entity.Role;
import com.evan.lustre.common.utils.JsonUtil;
import com.evan.lustre.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
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
            InputStream inputStream = classPathResource.getInputStream();
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
            String roleJson = inputStreamResource.readUtf8Str();
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
