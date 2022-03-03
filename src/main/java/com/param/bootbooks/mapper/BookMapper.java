package com.param.bootbooks.mapper;


import com.param.bootbooks.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Mapper
public interface BookMapper {

    // 增加
    int addBook(Book book);

    // 删除
    int deleteBookById(@Param("bookId") int id);

    // 更新
    int updateBook(Book book);

    // 查找
    Book queryBookById(@Param("bookId") int id);

    // 查找
    List<Book> queryBookByName(@Param("bookName") String name);

    // 查找所有书
    List<Book> queryAllBook();

//这是一堆我看不懂的代码！！！
    //这是一条新的注释
    //OK 编译器安装好了！！！
}
