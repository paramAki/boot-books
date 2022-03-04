package com.param.bootbooks.controller;

import com.param.bootbooks.service.MarkdownParseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoujingyu（976944083@qq.com）
 * @date 2022/3/4 11:11 PM
 */
@Slf4j
@RestController
public class MarkDownParseController {

    @RequestMapping("/markdown/translateToHtml")
    public String getHtml(String str) {
        log.info("Awaiting translate string:{}", str);
        String html = MarkdownParseService.parseHtml(str);
        log.info("get html : {}", html);
        return html;
    }
}
