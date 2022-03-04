package com.param.bootbooks;

import com.param.bootbooks.mapper.BookMapper;
import com.param.bootbooks.mapper.LibraryMapper;
import com.param.bootbooks.pojo.Library;
import com.param.bootbooks.service.MarkdownParseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@SpringBootTest
class BootBooksApplicationTests {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    BookMapper bookMapper;

    @Autowired
    LibraryMapper libraryMapper;

    @Test
    void contextLoads() {
        Long aLong = jdbcTemplate.queryForObject("select count(*) from books", Long.class);
        log.info("记录条数：{}", aLong);

        log.info("数据源类型：{}", dataSource.getClass());
    }

    @Test
    void testLibraryMapper(){
        Library library = libraryMapper.selectById(1);
        log.info(library.toString());
    }

    @Test
    void testCommonmark(){
        String str = "# This is *Sparta*";
        String html = MarkdownParseService.parseHtml(str);
        log.info("get html : {}",html);
    }


}
