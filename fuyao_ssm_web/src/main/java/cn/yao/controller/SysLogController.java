package cn.yao.controller;

import cn.yao.domain.SysLog;
import cn.yao.service.LogAopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private LogAopService service;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        List<SysLog>sysLogs=service.findAll();
        for (SysLog s:
             sysLogs) {
            System.out.println(s.toString());
        }
        ModelAndView mv=new ModelAndView();
        mv.addObject("sysLogs",sysLogs);
        mv.setViewName("syslog-list");
        return mv;

    }
}

