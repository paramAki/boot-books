package com.param.bootbooks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhoujingyu（976944083@qq.com）
 * @date 2022/2/23 9:47 AM
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    @RequestMapping("/toMain")
    public String toMain() {
        return "blog/main";
    }

    @RequestMapping("/toContact")
    public String toContact(){
        return "blog/contact";
    }

    @RequestMapping("/toAbout")
    public String toAbout(){
        return "blog/about";
    }

    @RequestMapping("/toPost")
    public String toPost(){
        return "blog/post";
    }
}
