package cn.yao.service;

import cn.yao.domain.Permission;
import cn.yao.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRole();

    void saveRole(Role role);

    Role findById(String id);

    List<Permission> findRoleByIdAndAllPermission(String id);

    void addPermissionToRole(String roleId, String[] ids);
}
