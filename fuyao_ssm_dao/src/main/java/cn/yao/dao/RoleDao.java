package cn.yao.dao;

import cn.yao.domain.Permission;
import cn.yao.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoleDao {
    @Select("select * from role where id in(select roleId from users_role where userId=#{id})")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",many = @Many(select = "cn.yao.dao.PermissionDao.findByRoleId",fetchType = FetchType.EAGER)),
    }
    )
    List<Role> findRoleByUserId(String id);

    @Select("select * from role")
    List<Role> findAllRole();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void saveRole(Role role);

    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",many = @Many(select = "cn.yao.dao.PermissionDao.findByRoleId",fetchType = FetchType.EAGER)),
            @Result(column = "id",property = "users",many = @Many(select = "cn.yao.dao.UserDao.findByRoleId",fetchType = FetchType.LAZY))
    })
    Role findById(String id);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{id}) ")
    List<Permission> findRoleByIdAndAllPermission(String id);


    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{id})")
    void savePermission(@Param("roleId") String roleId, @Param("id") String id);
}
