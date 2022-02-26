package com.param.bootbooks.controller;

import com.param.bootbooks.pojo.User;
import com.param.bootbooks.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author zhoujingyu（976944083@qq.com）
 */
@Slf4j
@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "book/login";
    }

    @RequestMapping("/login")
    public String login(User user, HttpSession session, Model model) {
        System.out.println(user);

        User getUser = userService.queryUserById(user.getUserID());

        // 判断账号状态
        if(getUser.getStatus()==0){
            model.addAttribute("errorMsg", "账号已被锁定，请联系作者！");
            return "book/login";
        }

        // 通过验证存入session
        if (user.equals(getUser)) {
            model.addAttribute("username", user.getUsername());
            session.setAttribute("loginUser", user);
            return "redirect:/book/allBook";
        }
        model.addAttribute("errorMsg", "账户或者密码错误！");
        return "book/login";
    }

    @RequestMapping("/main")
    public String main() {
        return "book/allBook";
    }

    @RequestMapping("/logout")
    public String logOut(HttpSession session, Model model) {
        System.out.println("====================================");
        session.removeAttribute("loginUser");
        model.addAttribute("errorMsg", "请重新登录");
        return "book/login";
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "book/register";
    }

    @RequestMapping("/register")
    public String register(User user,Model model) {

        // 先判断ID有没有重复
        if(userService.queryUserById(user.getUserID())!=null){
            model.addAttribute("errorMsg", "该ID已被注册！");
            return "book/register";
        }

        userService.addUser(user);
        return "redirect:login";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
