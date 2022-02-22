package com.param.bootbooks.controller;

import com.param.bootbooks.pojo.City;
import com.param.bootbooks.service.CityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CityServiceImpl cityService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @ResponseBody
    @RequestMapping("/sql")
    public String sql() {
        Long aLong = jdbcTemplate.queryForObject("select count(*) from books", Long.class);
        log.info("记录条数：{}", aLong);

        return "got";
    }

    @ResponseBody
    @RequestMapping("/city")
    public City queryCityById(@RequestParam("id") Long id){

        return cityService.queryCityById(id);
    }
}
