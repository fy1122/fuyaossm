package cn.yao.dao;

import cn.yao.domain.Role;
import cn.yao.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface UserDao {
    @Select("select * from users where id=#{id}")
    @Results({@Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", many = @Many(select = "cn.yao.dao.RoleDao.findRoleByUserId", fetchType = FetchType.EAGER))

    })
    UserInfo findById(String id);

    @Select("select * from users where username=#{username}")
    @Results({@Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", many = @Many(select = "cn.yao.dao.RoleDao.findRoleByUserId", fetchType = FetchType.EAGER))
    })
    UserInfo findByUserName(String username);

    @Update("update users set password=#{password} where id=#{id}")
    void updatePassword(UserInfo user);

    @Select("select * from users")
    List<UserInfo> findAll();

    @Update("insert into users (email,username,password,phoneNum,status) values (#{email},#{username},#{password},#{phoneNum},#{status})")
    void saveUser(UserInfo info);

    @Select("select * from role where id not in (select roleId from users_role where userId=#{id})")
    List<Role> findByIdAndRoles(String id);

    @Insert("insert into users_role (userId,roleId) values (#{userId},#{roleId})")
    void saveRole(@Param("userId") String userId, @Param("roleId") String roleId);

    @Select("select * from users where id in(select userId from users_role where roleId=#{roleId})")
    List<UserInfo> findByRoleId(String RoleId);

}
