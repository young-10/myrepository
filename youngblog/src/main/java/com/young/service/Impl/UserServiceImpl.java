package com.young.service.Impl;

import com.young.service.UserService;
import com.young.dao.UserDao;
import com.young.entities.User;
import com.young.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author young
 * @Description
 * @date 2020-05-01 19:56
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.queryByUsernameAndPassword(username, MD5Utils.code(password));
    }
}
