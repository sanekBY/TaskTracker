package com.sashqua.tracker.security;


import com.sashqua.tracker.entitys.Role;
import com.sashqua.tracker.service.RoleService;
import com.sashqua.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceStub implements UserDetailsService {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.sashqua.tracker.entitys.User user = userService.findByLogin(username);
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new User(
                user.getEmail(),
                user.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(1)
        );
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(roleService.getRole(role));
        return authList;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(Role role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        return authorities;
    }

//    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
//        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
//        return authList;
//    }
//
//    public List<String> getRoles(Integer role) {
//
//        List<String> roles = new ArrayList<String>();
//
//        if (role.intValue() == 1) {
//            roles.add("ROLE_MODERATOR");
//            roles.add("ROLE_ADMIN");
//        } else if (role.intValue() == 2) {
//            roles.add("ROLE_MODERATOR");
//        }
//        return roles;
//    }
//
//    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//
//        for (String role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//        return authorities;
//    }


}