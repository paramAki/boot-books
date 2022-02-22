package com.param.bootbooks.service;

import com.param.bootbooks.mapper.BookMapper;
import com.param.bootbooks.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface BookService {

    // 增加
    int addBook(Book book);

    // 删除
    int deleteBookById(int id);

    // 更新
    int updateBook(Book book);

    // 查找
    Book queryBookById(int id);

    // 查找
    List<Book> queryBookByName(String name);

    // 查找所有书
    List<Book> queryAllBook();
}
