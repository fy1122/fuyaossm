package cn.yao.dao;

import cn.yao.domain.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findByRoleId(String id);
    @Select("select * from permission")
    List<Permission> findAll();
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void savePermission(Permission permission);
}
