package com.sanelee.collegeentrance.controller;

import com.sanelee.collegeentrance.mapper.UserMapper;
import com.sanelee.collegeentrance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map;
import java.util.UUID;

import static com.sanelee.collegeentrance.model.User.*;

@Controller
@RequestMapping("/front/*")
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index( HttpServletRequest request){
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("token")){
//                String token = cookie.getValue();
//                User user = userMapper.findByToken(token);
//                if (user != null){
//                    request.getSession().setAttribute("user",user);
//                }
//                break;
//            }
//        }
        return "index";
    }
    //注册页面
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    //登录页面
    @RequestMapping("/login")
    public String login(HttpServletRequest request){

        return "login";
    }

    //注册方法
    @RequestMapping("/addregister")
    public String register(HttpServletRequest request, Map<String,Object> map){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        User userIdentify = userMapper.findByUsername(username);
        if (password.equals(password2)&&userIdentify==null){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userMapper.save(user);
            map.put("msg","注册成功,请登录！");
            return "login";
        }else {
            map.put("msg","密码不一致或用户名已存在！");
            return "register";
        }
    }
    //登录方法
    @RequestMapping("/addlogin")
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        Map<String,Object> map,
                        ModelAndView mv,
                        Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        User user = userMapper.findByUsernameAndPassword(username,password);
        if (user !=null){
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            userMapper.update(user);
            response.addCookie(new Cookie("token",token));
            session.setAttribute("loginUser",user);
            map.put("msg","登陆成功");
            model.addAttribute("user",username);
            return "redirect:/";
        }else {
            map.put("msg","密码或账号错误！");
            return "login";
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginUser");
        return "redirect:/";
    }

}
