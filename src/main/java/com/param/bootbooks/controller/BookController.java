package com.param.bootbooks.controller;

import com.param.bootbooks.pojo.Book;
import com.param.bootbooks.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author zhoujingyu（976944083@qq.com）
 */
@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 查询所有数据，并返回到一个展示页面
     * @param model
     * @return
     */
    @RequestMapping("/allBook")
    public String getAllBook(Model model) {
        List<Book> list = bookService.queryAllBook();
        log.info(list.toString());
        model.addAttribute("list", list);

        return "allBook";
    }

    @RequestMapping("/toAddBook")
    public String toAddBookPage() {
        return "addBook";
    }

    @RequestMapping("/addBook")
    public String addBook(Book book) {
        bookService.addBook(book);
        log.info("add Book => " + book);

        // 重定向回allBook
        return "redirect:/book/allBook";
    }

    @RequestMapping("/toUpdate")
    public String toUpdatePage(int bookID, Model model) {
        Book book = bookService.queryBookById(bookID);
        model.addAttribute("QBook", book);

        return "updateBook";
    }

    @RequestMapping("/updateBook")
    public String updateBook(Book book) {
        log.info("update Book => " + book);
        bookService.updateBook(book);

        // 重定向回allBook
        return "redirect:/book/allBook";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(int bookID){
        log.info("delete book => " + bookService.queryBookById(bookID));
        bookService.deleteBookById(bookID);

        return "redirect:/book/allBook";
    }
    
    @RequestMapping("/queryBook")
    public String queryBook(String queryBookName, Model model){
        List<Book> list = bookService.queryBookByName(queryBookName);

        log.info(list.toString());
        model.addAttribute("list", list);

        if(list.isEmpty()){
            model.addAttribute("err","抱歉，没有查找到");
        }

        return "allBook";
    }
}
