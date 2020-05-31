package com.young.dao;

import com.young.entities.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author young
 * @Description
 * @date 2020-05-04 22:08
 */
@Repository
@Mapper
public interface TagDao {

    int saveTag(Tag tag);

    int deleteTag(Long id);

    int updateTag(Tag tag);

    Tag getById(Long id);

    Tag getByName(String name);

    List<Tag> getAllTag();

    List<Tag> getAdminTag();
}
