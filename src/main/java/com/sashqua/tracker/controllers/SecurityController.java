package com.sashqua.tracker.controllers;

import com.sashqua.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
public class SecurityController {

    @RequestMapping(value = "/auth/login", method = RequestMethod.GET, produces = "application/json")
    public User getUser(Principal principial) {
        if (principial != null) {
            if (principial instanceof AbstractAuthenticationToken){
                return (User) ((AbstractAuthenticationToken) principial).getPrincipal();
            }
        }
        return null;
    }

    @RequestMapping(value = "/auth/logout", method = RequestMethod.POST)
    public void logout(HttpServletRequest rq, HttpServletResponse rs) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(rq, rs, null);
    }
}
