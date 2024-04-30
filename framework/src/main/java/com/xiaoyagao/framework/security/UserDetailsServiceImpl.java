package com.xiaoyagao.framework.security;

import com.xiaoyagao.common.enums.UserStatus;
import com.xiaoyagao.common.utils.StringUtils;
import com.xiaoyagao.common.core.domain.model.LoginUser;
import com.xiaoyagao.common.core.domain.entity.User;
import com.xiaoyagao.system.mapper.UserMapper;
import com.xiaoyagao.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    //实现loadUserByUsername方法
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("进入UserDetailsServiceImpl");
        User user = userService.selectByUsername(username);
        System.out.println("数据库里面查询出来的user："+user);
        if(StringUtils.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        else if(UserStatus.DELETED.getCode().equals(user.getDelFlag())){
            throw new UsernameNotFoundException("用户已被删除");
        }
        else if(UserStatus.DISABLE.getCode().equals(user.getStatus())){
            throw new UsernameNotFoundException("用户已停用");
        }

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(User user){
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(user);
        loginUser.setDeptId(user.getDeptId());
        return  loginUser;
    }

}
