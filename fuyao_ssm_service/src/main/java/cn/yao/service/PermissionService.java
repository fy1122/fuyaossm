package cn.yao.service;

import cn.yao.domain.Permission;

import java.util.List;

public interface PermissionService {
    public List<Permission> findAll();

    public void savePermission(Permission permission);
}
