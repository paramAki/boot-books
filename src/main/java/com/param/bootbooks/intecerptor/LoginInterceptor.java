package com.param.bootbooks.intecerptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("all")
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if (session.getAttribute("loginUser") != null) {
            return true;
        }

        if (request.getRequestURI().contains("ogin")) {
            return true;
        }

        if (request.getRequestURI().contains("egister")) {
            return true;
        }

        // 重定向，浏览器地址栏会变
        response.sendRedirect("/user/toLogin");
        return false;
    }
}
