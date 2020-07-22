package cn.yao.controller;

import cn.yao.domain.Permission;
import cn.yao.domain.Role;
import cn.yao.domain.UserInfo;
import cn.yao.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService service;
    @RequestMapping("/findAll.do")
    public ModelAndView findAllRole(){
        ModelAndView mv=new ModelAndView();
        List<Role> roles = service.findAllRole();
        mv.addObject("roleList",roles);
        mv.setViewName("role-list");
        return mv;
    }
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(String id){
//        service.svaeUser(info);
        Role role  = service.findById(id);
        List<Permission> permissions=service.findRoleByIdAndAllPermission(id);
//        System.out.println(userInfo.toString());
        ModelAndView mv=new ModelAndView();
        mv.addObject("permissionList",permissions);
        mv.addObject("role",role);
        mv.setViewName("role-permission-add");
        return  mv;
    }
    @RequestMapping("/save.do")
    public String saveRole(Role role){
        ModelAndView mv=new ModelAndView();
        service.saveRole(role);
        return "redirect:findAll.do";
    }
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(String roleId,String[]ids){
//        service.svaeUser(info);
//        UserInfo user = service.findById(id);
//        List<Role>roles=service.findUserByIdAndRoles(id);
//        System.out.println(userInfo.toString());
//        ModelAndView mv=new ModelAndView();
//        mv.addObject("roleList",roles);
//        mv.addObject("user",user);
//        mv.setViewName("user-role-add");
        service.addPermissionToRole(roleId,ids);
        return  "redirect:findAll.do";
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        Role role= service.findById(id);
//        roles.forEach(role -> System.out.println(role.getPermissions()));
//        System.out.println(userInfo.toString());
        ModelAndView mv=new ModelAndView();
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }
}
