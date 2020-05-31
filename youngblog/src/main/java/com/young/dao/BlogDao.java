package com.young.dao;

import com.young.dto.DetailedBlog;
import com.young.dto.FirstPageBlog;
import com.young.entities.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author young
 * @Description
 * @date 2020-05-01 18:31
 */
@Repository
@Mapper
public interface BlogDao {

    Blog getBlog(Long id);

    List<Blog> getAllBlog();

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    DetailedBlog getDetailedBlog(Long id);

    List<FirstPageBlog> getSearchBlog(String query);

    List<FirstPageBlog> getByTypeId(Long typeId);

    List<FirstPageBlog> getFirstPageBlog();
    List<FirstPageBlog> getByTagId(Long tagId);
}
