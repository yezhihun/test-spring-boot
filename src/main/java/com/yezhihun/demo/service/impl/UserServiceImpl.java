package com.yezhihun.demo.service.impl;

import com.yezhihun.demo.dao.UserDao;
import com.yezhihun.demo.entity.User;
import com.yezhihun.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tianye on 2018/5/3.
 */
@Service
public class UserServiceImpl extends AbstractBaseServiceImpl<User> implements UserService{

    @Autowired
    private UserDao userRepository;

    @Override
    public void init() {
        this.baseDao = userRepository;
    }
}
