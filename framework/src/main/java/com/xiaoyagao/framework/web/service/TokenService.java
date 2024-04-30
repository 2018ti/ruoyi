package com.xiaoyagao.framework.web.service;


import com.xiaoyagao.common.utils.TokenUtils;
import com.xiaoyagao.common.core.domain.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

@Component
public class TokenService {

    public User getuser(String token){
        Claims claims = TokenUtils.getClaimByToken(token);
        String username = claims.get("username", String.class);
        String password = claims.get("password", String.class);
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        return  user;
    }
}
