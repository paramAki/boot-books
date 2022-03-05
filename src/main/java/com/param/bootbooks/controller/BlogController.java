package com.param.bootbooks.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.param.bootbooks.mapper.BlogMapper;
import com.param.bootbooks.pojo.Blog;
import com.param.bootbooks.pojo.User;
import com.param.bootbooks.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhoujingyu（976944083@qq.com）
 * @date 2022/2/23 9:47 AM
 */
@Slf4j
@Controller
@RequestMapping("/blog")
public class BlogController {

//    @Autowired
//    BlogService blogService;

    @Autowired
    BlogMapper blogMapper;

    @RequestMapping("/toMain")
    public String toMain() {
        return "blog/main";
    }

    @RequestMapping("/toContact")
    public String toContact() {
        return "blog/contact";
    }

    @RequestMapping("/toAbout")
    public String toAbout() {
        return "blog/about";
    }

    @RequestMapping("/toPost")
    public String toPost() {
        return "blog/post";
    }

    @RequestMapping("/toElse")
    public String toElse() {
        return "blog/else";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, HttpServletRequest request) {
        int templateId = 0;
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        log.info("get log in user session:{}",loginUser);
        Blog blog = blogMapper.selectById(templateId);
        model.addAttribute("blog", blog);
        model.addAttribute("username",loginUser.getUsername());
        log.info("get blog data from database");
        return "blog/edit";
    }

    /**
     * 存入数据库
     *
     * @param blog
     * @return 保存结果
     */
    @RequestMapping("/save")
    public String publishBlog(Blog blog) {
        QueryWrapper<Blog> queryWrapper=new QueryWrapper();
        Long count = blogMapper.selectCount(queryWrapper);
        blog.setId(count);
        int a = blogMapper.insert(blog);
        if (a == 1) {
            log.info("新建成功，访问地址为http://175.178.96.30:9090/blog/show/"+blog.getId());
            return "redirect:show/"+blog.getId();
        }
        log.error("新建blog失败");
        return "/error";
    }

    /**
     * RequestParam
     *
     * @param image
     * @return
     */
    @RequestMapping("/image/upload")
    @ResponseBody
    public JSONObject imageUpload(@RequestParam("editormd-image-file") MultipartFile image) {
        JSONObject jsonObject = new JSONObject();
        if (image != null) {
            String path = FileUtils.uploadFile(image);
            System.out.println(path);
            jsonObject.put("url", path);
            jsonObject.put("success", 1);
            jsonObject.put("message", "upload success!");
            return jsonObject;
        }
        jsonObject.put("success", 0);
        jsonObject.put("message", "upload error!");
        return jsonObject;
    }


    @RequestMapping("/get/{id}")
    public ModelAndView getBlogById(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Blog blog = blogMapper.selectById(id);
        modelAndView.setViewName("blog");
        if (blog == null) {
            modelAndView.addObject("blog", new Blog());
        }
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    /**
     * 通过id展示内容
     *
     * @param id
     * @return
     */
    @RequestMapping("/show/{id}")
    public String showBlog(@PathVariable(name = "id") int id, Model model) {
        Blog blog = blogMapper.selectById(id);
        model.addAttribute("blog", blog);
        return "blog/blog";
    }
}
