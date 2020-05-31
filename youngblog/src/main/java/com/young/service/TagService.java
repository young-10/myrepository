package com.young.service;

import com.young.entities.Tag;

import java.util.List;

/**
 * @author young
 * @Description
 * @date 2020-05-04 22:14
 */
public interface TagService {

    int saveTag(Tag tag);

    int deleteTag(Long id);

    int updateTag(Tag tag);

    Tag getById(Long id);

    Tag getByName(String name);

    List<Tag> getAllTag();

    List<Tag> getTagByString(String text);

    List<Tag> getAdminTag();
}
