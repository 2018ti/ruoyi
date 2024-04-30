package com.xiaoyagao.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoyagao.common.core.domain.entity.User;


/**
 * 用户信息表(User)表服务接口
 *
 * @author makejava
 * @since 2024-04-26 11:11:57
 */
public interface UserService extends IService<User> {

    public User selectByUsername(String username);
}
