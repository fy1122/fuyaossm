package cn.yao.service.impl;

import cn.yao.dao.UserDao;
import cn.yao.domain.Role;
import cn.yao.domain.UserInfo;
import cn.yao.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Logger logger=Logger.getLogger(UserServiceImpl.class);
    @Autowired
   private UserDao dao;

//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("123") );
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = dao.findByUserName(username);

//        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        System.out.println("----------------");
        System.out.println(userInfo.getPassword());
        List<Role> roles = userInfo.getRoles();
        List<SimpleGrantedAuthority> authoritys = getAuthority(roles);
        User user =new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0? false: true,true,true,true,authoritys);
        System.out.println("--------------------");
        logger.info(user);
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for (Role role:
             roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            
        }
        return authorities;
    }

    @Override
    public List<UserInfo> findAll() {
        return dao.findAll();
    }

    @Override
    public UserInfo findById(String id) {
        return dao.findById(id);
    }

    @Override
    public void svaeUser(UserInfo info) {
        info.setPassword(bCryptPasswordEncoder.encode(info.getPassword()));
        dao.saveUser(info);
    }

    @Override
    public List<Role> findUserByIdAndRoles(String id) {
        return dao.findByIdAndRoles(id);
    }

    @Override
    public void addRoleToUser(String userId, String... ids) {
        for (String roleId:
             ids) {
            dao.saveRole(userId,roleId);
        }
    }
}
