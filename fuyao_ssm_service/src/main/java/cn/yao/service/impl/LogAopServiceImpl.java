package cn.yao.service.impl;

import cn.yao.dao.LogAopDao;
import cn.yao.domain.SysLog;
import cn.yao.service.LogAopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("logAopService")
@Transactional
public class LogAopServiceImpl implements LogAopService {
    @Autowired
    private LogAopDao dao;
    @Override
    public void save(SysLog sysLog) {
        dao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() {
        return dao.findAll();
    }
}
