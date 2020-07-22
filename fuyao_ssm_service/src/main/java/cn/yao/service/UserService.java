package cn.yao.service;

import cn.yao.domain.Role;
import cn.yao.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    List<UserInfo> findAll();

    UserInfo findById(String id);

    void svaeUser(UserInfo info);

    List<Role> findUserByIdAndRoles(String id);

    void addRoleToUser(String userId, String... ids);
}
