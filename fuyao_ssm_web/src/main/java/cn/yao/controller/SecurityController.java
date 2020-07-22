package cn.yao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {
    @RequestMapping("/error.do")
    public String error(){
        return "error";
    }
}
