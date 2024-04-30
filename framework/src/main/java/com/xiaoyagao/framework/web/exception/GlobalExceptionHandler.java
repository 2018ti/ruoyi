package com.xiaoyagao.framework.web.exception;


import com.alibaba.fastjson2.schema.ValidateResult;
import com.xiaoyagao.common.constant.HttpStatus;
import com.xiaoyagao.common.core.domain.AjaxResult;
import org.aspectj.weaver.ast.Var;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AuthenticationException.class)
    public AjaxResult  AuthenticationException(AuthenticationException e, HttpServletRequest request){

            String requestURI = request.getRequestURI();
            log.error("请求地址'{}',认证失败{}",requestURI,e.getMessage());
            return AjaxResult.error(HttpStatus.UNAUTHORIZED,"没有权限访问该资源");

    }

    @ExceptionHandler(IOException.class)
    public AjaxResult handleRuntimeException(IOException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return AjaxResult.error(e.getMessage());
    }



}
