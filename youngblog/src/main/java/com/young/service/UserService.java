package com.young.service;

import com.young.entities.User;

/**
 * @author young
 * @Description
 * @date 2020-05-01 19:55
 */

public interface UserService {

    User checkUser(String username,String password);
}
