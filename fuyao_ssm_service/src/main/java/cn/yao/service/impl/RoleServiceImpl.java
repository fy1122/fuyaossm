package cn.yao.service.impl;

import cn.yao.dao.RoleDao;
import cn.yao.domain.Permission;
import cn.yao.domain.Role;
import cn.yao.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao dao;

    @Override
    public List<Role> findAllRole() {
        return dao.findAllRole();
    }

    @Override
    public void saveRole(Role role) {
        dao.saveRole(role);
    }

    @Override
    public Role findById(String id) {
       return     dao.findById(id);
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(String id) {
        return dao.findRoleByIdAndAllPermission(id);
    }

    @Override
    @Transactional
    public void addPermissionToRole(String roleId, String[] ids) {
        for (String id : ids) {
            dao.savePermission(roleId, id);
        }
    }
}
