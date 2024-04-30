package com.xiaoyagao.admin;


import com.xiaoyagao.common.core.domain.AjaxResult;
import com.xiaoyagao.common.core.domain.model.LoginBody;
import com.xiaoyagao.framework.web.service.LoginService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody){
        System.out.println("进入登录controller");
        String access_token =   loginService.login(loginBody.getUsername(),loginBody.getPassword());
        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("access_token",access_token);
      return   AjaxResult.success("登陆成功",dataMap);
    }

    @GetMapping("/getinfo")
    public AjaxResult getUserInfo(){
        

        return null;
    }



}
