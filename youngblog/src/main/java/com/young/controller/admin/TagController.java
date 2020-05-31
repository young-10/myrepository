package com.young.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.entities.Tag;
import com.young.entities.Type;
import com.young.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author young
 * @Description
 * @date 2020-05-04 22:25
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    TagService tagService;
    @GetMapping("/tags")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Tag> adminTag = tagService.getAdminTag();
        PageInfo pageInfo = new PageInfo(adminTag);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(){
        return "admin/tag-input";
    }

    @PostMapping("/tags/input")
    public String inputTag(Tag tag, RedirectAttributes attributes,Model model){
        Tag tag1 = tagService.getByName(tag.getName());
        if(tag1!=null){
            model.addAttribute("message","存在相同的标签");
            return "admin/tag-input";
        }else{
            attributes.addFlashAttribute("message","操作成功");
            tagService.saveTag(tag);
            return "redirect:/admin/tags" ;
        }

    }

    @GetMapping("/tag/{id}")
    public String get(@PathVariable("id") Long id,
                      Model model){
        Tag tag = tagService.getById(id);
        model.addAttribute("tag",tag);
        return "admin/tag-update";

    }

    @PostMapping("/tag/update")
    public String update(Tag tag){
        tagService.updateTag(tag);
        return "redirect:/admin/tags" ;
    }

}
