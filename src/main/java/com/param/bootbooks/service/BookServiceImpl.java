package com.param.bootbooks.service;


import com.param.bootbooks.mapper.BookMapper;
import com.param.bootbooks.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhoujingyu（976944083@qq.com）
 */
@Component("BookServiceImpl")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    public int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    @Override
    public int deleteBookById(int id) {
        return bookMapper.deleteBookById(id);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public Book queryBookById(int id) {
        return bookMapper.queryBookById(id);
    }

    @Override
    public List<Book> queryBookByName(String name) {
        return bookMapper.queryBookByName(name);
    }

    @Override
    public List<Book> queryAllBook() {
        return bookMapper.queryAllBook();
    }
}
