package com.xiaoyagao.framework.security.filter;


import com.xiaoyagao.common.utils.StringUtils;
import com.xiaoyagao.common.utils.TokenUtils;
import com.xiaoyagao.framework.security.handle.AuthenticationEntryPointImpl;
import com.xiaoyagao.framework.web.service.TokenService;
import com.xiaoyagao.common.core.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    AuthenticationEntryPointImpl authenticationEntryPoint;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        User user= null;
        System.out.println("header 里面的Authorization:"+request.getHeader("Authorization"));
        if(StringUtils.isNotNull(request.getHeader("Authorization"))) {
             user = tokenService.getuser(request.getHeader("Authorization"));
        }
        if(StringUtils.isNotNull(user) && StringUtils.isNull(SecurityContextHolder.getContext().getAuthentication())){
            int tokenVerify = TokenUtils.parseToken(request.getHeader("Authorization"));
            if(tokenVerify==-1){
              throw new BadCredentialsException("token已过期");
            }else if(tokenVerify==-2){
                throw new BadCredentialsException("token非法");
            }
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword(),null);
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request,response);
    }

    //验证token是否合法


}
