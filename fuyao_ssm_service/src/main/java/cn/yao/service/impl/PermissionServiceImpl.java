package cn.yao.service.impl;

import cn.yao.dao.PermissionDao;
import cn.yao.domain.Permission;
import cn.yao.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionDao dao;
    @Override
    public List<Permission> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public void savePermission(Permission permission) {
        dao.savePermission(permission);
    }
}
