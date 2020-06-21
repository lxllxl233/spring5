package com.woqiyounai.jdbctemplate.service;

import com.woqiyounai.jdbctemplate.dao.BookDao;
import com.woqiyounai.jdbctemplate.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    //注入 dao
    @Autowired
    private BookDao bookDao;

    //添加的方法
    public void addBook(Book book){
        bookDao.add(book);
    }

    public void updateBook(Book book){
        bookDao.updatBook(book);
    }

    public void deleteBook(Integer id){
        bookDao.deleteBook(id);
    }

    //查询表里有多少条记录
    public int findCount(){
        int count = bookDao.count();
        return count;
    }

    public Book findById(Integer id){
        Book book = bookDao.findById(id);
        return book;
    }

    public List<Book> findAll(){
        List<Book> bookList = bookDao.findAll();
        return bookList;
    }

    public void batchInsert(List<Object[]> bookList){
        bookDao.batchInsert(bookList);
    }
}
