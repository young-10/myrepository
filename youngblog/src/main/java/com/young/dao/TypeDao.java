package com.young.dao;

import com.young.entities.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author young
 * @Description
 * @date 2020-05-02 10:02
 */
@Repository
@Mapper
public interface TypeDao {

    int saveType(Type type);

    Type getTypeById(Long id);

    Type getTypeByName(String name);

    List<Type> getAllType();

    List<Type> getAdminType();

    int deleteType(Long id);

    int updateType(Type type);

}
