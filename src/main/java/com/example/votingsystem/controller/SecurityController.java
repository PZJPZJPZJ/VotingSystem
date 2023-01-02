package com.example.votingsystem.controller;

import com.example.votingsystem.bean.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;

@Controller
public class SecurityController {
    @RequestMapping("/security/login")
    public String toLogin() {
        return "/security/login";
    }

    @GetMapping ("/security/register")
    public String toRegister() {
        return "/security/register";
    }
    @Autowired
    DataSource dataSource;
    @PostMapping ("/security/register")
    public String register(Users users) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        if(!manager.userExists(users.getUsername())){
            manager.createUser(User.withUsername(users.getUsername()).password(new BCryptPasswordEncoder().encode(users.getPassword())).roles("user").build());
        }
        return "redirect:/security/login";
    }
}
