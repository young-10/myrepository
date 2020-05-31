package com.young.dao;

import com.young.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author young
 * @Description
 * @date 2020-05-01 19:48
 */
@Mapper
@Repository
public interface UserDao {

    User queryByUsernameAndPassword(String username, String password);
}
