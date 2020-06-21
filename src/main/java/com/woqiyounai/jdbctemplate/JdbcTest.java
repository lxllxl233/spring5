package com.woqiyounai.jdbctemplate;

import com.woqiyounai.jdbctemplate.entity.Book;
import com.woqiyounai.jdbctemplate.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class JdbcTest {

    @Test
    public void testJdbc(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:jdbcTemplate/data.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        Book book = new Book(0,"水浒传","入库");
        bookService.addBook(book);
        book.setBookId(5);
        book.setBookName("白夜行");
        book.setBookStatus("出库");
        bookService.updateBook(book);
        bookService.deleteBook(3);
    }

    @Test
    public void findCount(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:jdbcTemplate/data.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        int count = bookService.findCount();
        System.out.println(count);
    }

    @Test
    public void testSelect(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:jdbcTemplate/data.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        Book byId = bookService.findById(5);
        System.out.println(byId);
    }

    @Test
    public void testQuery(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:jdbcTemplate/data.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        List<Book> all = bookService.findAll();
        for (Book book : all) {
            System.out.println(book);
        }
    }

    //测试批量
    @Test
    public void testBatch(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:jdbcTemplate/data.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        List<Object[]> bookList = new ArrayList<Object[]>();
        Object[] o1 = {"0","java","入库"};
        Object[] o2 = {"0","c++","入库"};
        Object[] o3 = {"0","mysql","入库"};
        bookList.add(o1);
        bookList.add(o2);
        bookList.add(o3);
        bookService.batchInsert(bookList);
    }
}
