package cn.yao.controller;

import cn.yao.domain.Role;
import cn.yao.domain.UserInfo;
import cn.yao.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
   private UserService service;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
       List<UserInfo>userList= service.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
       UserInfo userInfo= service.findById(id);
//        roles.forEach(role -> System.out.println(role.getPermissions()));
//        System.out.println(userInfo.toString());
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }
    @RequestMapping("/save.do")
    public String findById(UserInfo info){
        service.svaeUser(info);
//        System.out.println(userInfo.toString());
        ModelAndView mv=new ModelAndView();
        return  "forward:findAll.do";
    }
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id){
//        service.svaeUser(info);
        UserInfo user = service.findById(id);
        List<Role>roles=service.findUserByIdAndRoles(id);
//        System.out.println(userInfo.toString());
        ModelAndView mv=new ModelAndView();
        mv.addObject("roleList",roles);
        mv.addObject("user",user);
        mv.setViewName("user-role-add");
        return  mv;
    }
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(String userId,String[]ids){
//        service.svaeUser(info);
//        UserInfo user = service.findById(id);
//        List<Role>roles=service.findUserByIdAndRoles(id);
//        System.out.println(userInfo.toString());
//        ModelAndView mv=new ModelAndView();
//        mv.addObject("roleList",roles);
//        mv.addObject("user",user);
//        mv.setViewName("user-role-add");
        service.addRoleToUser(userId,ids);
        return  "redirect:findAll.do";
    }

}
