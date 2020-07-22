package cn.yao.controller;

import cn.yao.domain.Permission;
import cn.yao.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    PermissionService service;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = service.findAll();
        mv.addObject("permissionList", permissionList);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("save.do")
    public String save(Permission permission) {
        service.savePermission(permission);
        return "redirect:findAll.do";
    }
}
