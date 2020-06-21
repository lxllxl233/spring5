package com.woqiyounai.jdbctemplate.dao;

import com.woqiyounai.jdbctemplate.entity.Book;

import java.util.List;

public interface BookDao {

    void add(Book book);

    void updatBook(Book book);

    void deleteBook(Integer id);

    int count();

    Book findById(Integer id);

    List<Book> findAll();

    void batchInsert(List<Object[]> bookList);
}
