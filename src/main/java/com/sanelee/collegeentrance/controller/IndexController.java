package com.sanelee.collegeentrance.controller;

import com.sanelee.collegeentrance.mapper.UserMapper;
import com.sanelee.collegeentrance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static com.sanelee.collegeentrance.model.User.*;

@Controller
@RequestMapping("/front/*")
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(){
        return "index";
    }
    //注册页面
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    //登录页面
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    //注册方法
    @RequestMapping("/addregister")
    public String register(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        if (password.equals(password2)){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userMapper.save(user);
            return "login";
        }else {
            return "register";
        }
    }
    //登录方法
    @RequestMapping("/addlogin")
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userMapper.findByUsernameAndPassword(username,password);
        String str = "";
        if (user !=null){
            str = "index";
        }else {
            str = "login";
        }
        return str;
    }

}
