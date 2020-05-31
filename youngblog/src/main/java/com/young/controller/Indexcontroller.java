package com.young.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.dao.BlogDao;
import com.young.dto.DetailedBlog;
import com.young.dto.FirstPageBlog;
import com.young.entities.Blog;
import com.young.entities.Tag;
import com.young.entities.Type;
import com.young.service.BlogService;
import com.young.service.TagService;
import com.young.service.TypeService;
import com.young.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Types;
import java.util.List;

/**
 * @author young
 * @Description
 * @date 2020-05-05 20:17
 */
@Controller
public class Indexcontroller {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<FirstPageBlog> blogs = blogService.getFirstPageBlog();
        List<Type> types = typeService.getAllAdminType();
        List<Tag> tags = tagService.getAllTag();
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("tags",tags);
        model.addAttribute("types",types);

        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id,Model model){
        DetailedBlog blog = blogService.getDetailedBlog(id);
        model.addAttribute("blog",blog);
        return "blog";
    }

    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 3);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        PageInfo pageInfo = new PageInfo(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

}
