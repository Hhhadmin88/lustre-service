package com.evan.homemaking.common.model.dto;

import com.evan.homemaking.common.model.entity.Role;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName RoleDto
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/14 0:21
 */
@Component
@ConfigurationProperties(prefix = "insert-data")
@Data
public class RoleDTO {
    public List<Role> roleList;
}
