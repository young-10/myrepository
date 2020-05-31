package com.young.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.entities.Blog;
import com.young.entities.User;
import com.young.service.BlogService;
import com.young.service.TagService;
import com.young.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author young
 * @Description
 * @date 2020-05-01 21:21
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;

    public void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getAllAdminType());
        model.addAttribute("tags", tagService.getAdminTag());
    }
    @GetMapping("/blog")
    public String blogs(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Blog> blogs = blogService.getAllBlog();
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs";
    }

    @GetMapping("/blog/input")
    public String input(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }

    //新增
    @PostMapping("/blogs")
    public String add(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setUserId(blog.getUser().getId());
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        blogService.saveBlog(blog);
        attributes.addFlashAttribute("message","新增成功");
        return "redirect:/admin/blog";
    }

}
