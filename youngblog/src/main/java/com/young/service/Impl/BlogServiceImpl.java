package com.young.service.Impl;

import com.young.dao.BlogDao;
import com.young.dto.DetailedBlog;
import com.young.dto.FirstPageBlog;
import com.young.entities.Blog;
import com.young.entities.Tag;
import com.young.exception.NotFoundException;
import com.young.service.BlogService;
import com.young.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author young
 * @Description
 * @date 2020-05-04 13:57
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;
    @Override
    public Blog getBlog(Long id) {
        return blogDao.getBlog(id);
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogDao.getAllBlog();
    }

    @Override
    public int saveBlog(Blog blog) {
        if(blog.getId()==null){ //如果博客id为空则为新增，有id则为修改
            blog.setCreateTime(new Date()); //保存博客创建时间(当前时间)
            blog.setUpdateTime(new Date());
            blog.setViews(0); //博客浏览量初始化
        }else {
            blog.setUpdateTime(new Date());
        }
        return blogDao.saveBlog(blog);
    }

    @Override
    public int updateBlog(Blog blog) {
        return 0;
    }

    @Override
    public int deleteBlog(Long id) {
        return 0;
    }

    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogDao.getDetailedBlog(id);
        if (detailedBlog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return detailedBlog;
    }

    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }

    @Override
    public List<FirstPageBlog> getByTagId(Long tagId) {
        return blogDao.getByTagId(tagId);
    }

    @Override
    public List<FirstPageBlog> getFirstPageBlog() {
        return blogDao.getFirstPageBlog();
    }


}
