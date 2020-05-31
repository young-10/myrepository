package com.young.service;

import com.young.entities.Type;

import java.util.List;

/**
 * @author young
 * @Description
 * @date 2020-05-02 9:56
 */
public interface TypeService {

    int saveType(Type type);

    Type getType(Long id);

    List<Type> getAllType();

    List<Type> getAllAdminType();

    int updateType(Type type);

    int deleteType(Long id);

    Type getTypeByName(String name);
}
