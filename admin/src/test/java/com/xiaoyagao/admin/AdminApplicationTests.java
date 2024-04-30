package com.xiaoyagao.admin;

import com.xiaoyagao.framework.web.service.LoginService;
import com.xiaoyagao.system.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AdminApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginService loginService;


    @Test
    void contextLoads() {
        System.out.println(loginService.login("xiaoyagao", "123456"));
    }


}
