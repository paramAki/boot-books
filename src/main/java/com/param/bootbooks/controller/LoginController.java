package com.param.bootbooks.controller;

import com.alibaba.druid.util.StringUtils;
import com.param.bootbooks.pojo.User;
import com.param.bootbooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {

    // controller层调service层
    @Autowired
//    @Qualifier("UserServiceImpl")
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(User user, HttpSession session, Model model) {
        System.out.println(user);

        User getUser = userService.queryUserByName(user.getUsername());

        // 通过验证存入session
        if (user.equals(getUser)) {
            model.addAttribute("username", user.getUsername());
            session.setAttribute("loginUser", user);
            return "redirect:/book/allBook";
        }
        model.addAttribute("errorMsg", "账户或者密码错误！");
        return "login";
//        if (!StringUtils.isEmpty(user.getUsername()) && !user.getPassword().isEmpty()) {
//            session.setAttribute("loginUser", user);
//
//            return "redirect:/book/allBook";
//        } else {
//            model.addAttribute("msg", "账号或者密码错误");
//            return "login";
//        }
    }

    @RequestMapping("/main")
    public String main() {
        return "allBook";
    }

    @RequestMapping("/logout")
    public String goOut(HttpSession session, Model model) {
        session.removeAttribute("userLoginInfo");
        model.addAttribute("errorMsg", "请重新登录");
        return "login";
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/register")
    public String register(User user) {
        userService.addUser(user);

        return "redirect:login";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
