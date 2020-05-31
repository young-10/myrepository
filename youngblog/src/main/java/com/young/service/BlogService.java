package com.young.service;

import com.young.dto.DetailedBlog;
import com.young.dto.FirstPageBlog;
import com.young.entities.Blog;

import java.util.List;

/**
 * @author young
 * @Description
 * @date 2020-05-04 13:50
 */
public interface BlogService {

    Blog getBlog(Long id);

    List<Blog> getAllBlog();

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

    DetailedBlog getDetailedBlog(Long id);

    List<FirstPageBlog> getSearchBlog(String query);

    List<FirstPageBlog> getByTypeId(Long typeId);

    List<FirstPageBlog> getByTagId(Long tagId);

    List<FirstPageBlog> getFirstPageBlog();
}

