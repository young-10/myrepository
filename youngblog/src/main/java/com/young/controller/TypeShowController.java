package com.young.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.dto.FirstPageBlog;
import com.young.entities.Type;
import com.young.service.BlogService;
import com.young.service.TypeService;
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
 * @date 2020-05-06 17:10
 */
@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                        @PathVariable Long id, Model model) {
        List<Type> types = typeService.getAllType();
        List<Type> serviceAllAdminType = typeService.getAllAdminType();
        if (id == -1) {
            id = types.get(0).getId();
        }
        model.addAttribute("types", serviceAllAdminType);
        List<FirstPageBlog> blogs = blogService.getByTypeId(id);
        PageHelper.startPage(pageNum, 100);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
