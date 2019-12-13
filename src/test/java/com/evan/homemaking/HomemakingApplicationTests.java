package com.evan.homemaking;

import com.evan.homemaking.common.model.dto.RoleDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomemakingApplicationTests {

    @Autowired
    private RoleDTO roleDto;

    @Test
    public void contextLoads() {
    }

    @Test
    public void roleListTest() {
        System.out.println(roleDto.getRoleList());
    }
}
