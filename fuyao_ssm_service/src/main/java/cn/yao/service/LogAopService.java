package cn.yao.service;

import cn.yao.domain.SysLog;

import java.util.List;

public interface LogAopService {
     void save (SysLog sysLog);


    List<SysLog> findAll();
}
