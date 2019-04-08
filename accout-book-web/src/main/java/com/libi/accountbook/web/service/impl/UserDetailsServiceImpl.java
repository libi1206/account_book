package com.libi.accountbook.web.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.entity.AccUser;
import com.libi.accountbook.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author libi
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String PHONE_REGEX = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    @Reference
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AccUser sysUser = null;
        if (userName.matches(PHONE_REGEX)) {
            sysUser = userService.selectUserByPhone(userName);
        } else {
            sysUser = userService.selectUserByUserName(userName);
        }
        return new User(sysUser.getUserName(), sysUser.getPassword(), handleAuthority(sysUser.getAuthority()));
    }

    /**
     * 负责处理数据库中的角色权限信息，把他变成SimpleGrantedAuthority类的集合
     * 现在它的逻辑是：
     * USER ==> ROLE_USER
     * ADMIN ==> ROLE_USER,ADMIN
     *
     * @param auth 数据库中的权限字符串
     * @return 需要的权限类的集合
     */
    private List<SimpleGrantedAuthority> handleAuthority(String auth) {
        List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
        SimpleGrantedAuthority authUser = new SimpleGrantedAuthority("USER");
        SimpleGrantedAuthority authAdmin = new SimpleGrantedAuthority("ADMIN");
        if ("user".equals(auth) || auth == null) {
            authList.add(authUser);
        } else if ("admin".equals(auth)) {
            authList.add(authAdmin);
            authList.add(authUser);
        }
        return authList;
    }
}
