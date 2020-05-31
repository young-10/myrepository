package com.young.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.young.entities.Type;
import com.young.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author young
 * @Description
 * @date 2020-05-02 11:54
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TypeService typeService;

    @GetMapping("/types")
    public String list(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pageNum,5);
        List<Type> allAdminType = typeService.getAllAdminType();
        PageInfo pageInfo = new PageInfo(allAdminType);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }


    //去新增页面
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type",new Type()); //将一个空Type放入model，方便前端渲染Type
        return "admin/type-input";
    }

   //处理新增页面的请求
    @PostMapping("/types")
    public String post(Type type,RedirectAttributes attributes,Model model){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            model.addAttribute("message", "不能添加重复的类");
            return "admin/type-input";
        }
        //添加操作
        typeService.saveType(type);
        attributes.addFlashAttribute("message","操作成功");
        return "redirect:/admin/types";
    }


    //去更新界面
    @GetMapping("/types/{id}")
    public String editInput(@PathVariable Long id, Model model) {
        Type type = typeService.getType(id);
        model.addAttribute("type", type);
        return "admin/type-update";
    }

    //处理更新请求
    @PostMapping("/types/update")
    public String editPost(Type type) {
        typeService.updateType(type);
        return "redirect:/admin/types";
    }

    //处理删除请求
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }
}
