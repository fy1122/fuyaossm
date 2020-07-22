package cn.yao.controller;


import cn.yao.domain.SysLog;
import cn.yao.service.LogAopService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    private Date visitTime;//访问的时间
    private Class clazz;//访问的类
    private Method executionMethod;//访问的方法
    @Autowired
    private LogAopService service;
    @Autowired
    private HttpServletRequest request;
    @Before("execution(* cn.yao.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime=new Date();
        clazz=jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if (args==null||args.length==0){
            executionMethod=clazz.getMethod(methodName);
        }else {
            Class[]classArgs=new Class[args.length];
            for (int i = 0; i <args.length ; i++) {
                classArgs[i]=args[i].getClass();
            }
            executionMethod= clazz.getMethod(methodName,classArgs);
        }

    }
    @After("execution(* cn.yao.controller.*.*(..))")
    public void daAfter(JoinPoint jp){
        Long executionTime=new Date().getTime()-visitTime.getTime();
        if (clazz!=LogAop.class&&clazz!=null&&executionMethod!=null&&clazz!=SysLogController.class){
            RequestMapping clazzAnnotation = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation!=null){
                RequestMapping methodAnnotion= executionMethod.getAnnotation(RequestMapping.class);
                if (methodAnnotion!=null){
                    SysLog sysLog=new SysLog();
                    String url="";
                    url=clazzAnnotation.value()[0]+methodAnnotion.value()[0];
                    String ip=request.getRemoteAddr();
                    SecurityContext context= SecurityContextHolder.getContext();
                    String username=((User)(context.getAuthentication().getPrincipal())).getUsername();
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setIp(ip);
                    sysLog.setMethod(clazz.getName()+executionMethod.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    service.save(sysLog);
                }
            }

        }

    }
}
