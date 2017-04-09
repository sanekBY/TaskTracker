package com.sashqua.tracker.controllers;

import com.sashqua.tracker.security.SecurityUtils;
import com.sashqua.tracker.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@RestController
public class TrackerController {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    UserService userService;

    @RequestMapping("/resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @RequestMapping("/api/user/info")
    public User user(User user) {
//        User userr = new User();
//        userr.setId(1);
//        userr.setFirstName("admin");
//        userr.setEmail("admin");
//        userr.setPassword("admin");
//        if (user.getEmail().equals(userr.getEmail()) && user.getPassword().equals(userr.getPassword())) return userr;
        return null;
    }

//

    @RequestMapping(value = "/auth/login", method = RequestMethod.GET, produces = "application/json")
    public User getUser(Principal principial) {
        if (principial != null) {
            if (principial instanceof AbstractAuthenticationToken){
                return (User) ((AbstractAuthenticationToken) principial).getPrincipal();
            }
        }
        return null;
    }

//    @RequestMapping(value = "/auth/login", method = RequestMethod.GET, produces = "application/json")
//    public @ResponseBody
//    User getUser() {
//        User user = userService.findByLogin(SecurityUtils.getCurrentLogin());
//        user.setPassword(null);
//        return user;
//    }


    @RequestMapping(value = "/auth/logout", method = RequestMethod.POST)
    public void logout(HttpServletRequest rq, HttpServletResponse rs) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(rq, rs, null);
    }



}
