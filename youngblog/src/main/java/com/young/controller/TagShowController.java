package com.young.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.dto.FirstPageBlog;
import com.young.entities.Tag;
import com.young.service.BlogService;
import com.young.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author young
 * @Description
 * @date 2020-05-07 16:43
 */
@Controller
public class TagShowController {

    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tag(@PathVariable("id") Long id,
                      @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                      Model model) {
        List<Tag> tags = tagService.getAllTag();
        if(id==-1){
            id = tags.get(0).getId();
        }model.addAttribute("tags", tags);
        List<FirstPageBlog> blogs = blogService.getByTagId(id);
        PageHelper.startPage(pageNum, 100);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
