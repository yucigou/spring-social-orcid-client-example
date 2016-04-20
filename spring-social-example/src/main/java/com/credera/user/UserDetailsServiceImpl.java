package com.credera.user;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    UserDao userDao;
    
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserById(username);
        Set<GrantedAuthority> grantedAuthorities = user.getGrantedAuthorities();
        String password = user.getPassword();
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, password, true, true, true, true, grantedAuthorities);
        return userDetails;
    }

}
