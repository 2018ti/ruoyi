package com.xiaoyagao.framework.web.service;


import com.xiaoyagao.common.utils.TokenUtils;
import com.xiaoyagao.common.core.domain.model.LoginUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LoginService {

    @Resource
    AuthenticationManager authenticationManager;

    public String login(String username,String password){

       Authentication authentication =null;
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("用户名或密码错误");
        }
        LoginUser loginUser =(LoginUser) authentication.getPrincipal();
        String token = TokenUtils.generateToken(loginUser.getUser().getUserId().toString(), loginUser.getUser().getUserName(),loginUser.getUser().getPassword());
        return  token;
    }


}
