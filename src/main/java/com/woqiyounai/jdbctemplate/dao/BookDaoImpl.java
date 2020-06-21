package com.woqiyounai.jdbctemplate.dao;

import com.woqiyounai.jdbctemplate.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao{

    //注入 jdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void add(Book book) {
        //增加修改删除 update 参数一:sql 参数二:参数
        String sql = "insert into book values(?,?,?)";
        //传递参数
        int update = jdbcTemplate.update(sql, book.getBookId(), book.getBookName(), book.getBookStatus());
        System.out.println(update);
    }

    public void updatBook(Book book) {
        String sql = "update book set book_name=?,book_status=? where book_id=?";
        int update = jdbcTemplate.update(sql, book.getBookName(), book.getBookStatus(), book.getBookId());
        System.out.println(update);
    }

    public void deleteBook(Integer id) {
        String sql = "delete from book where book_id=?";
        int update = jdbcTemplate.update(sql, id);
        System.out.println(update);
    }

    public int count() {
        String sql = "select count(*) from book";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    public Book findById(Integer id) {
        String sql = "select * from book where book_id=?";
        BeanPropertyRowMapper<Book> bookBeanPropertyRowMapper = new BeanPropertyRowMapper<Book>(Book.class);
        Book book = jdbcTemplate.queryForObject(sql, bookBeanPropertyRowMapper, id);
        return book;
    }

    public List<Book> findAll() {
        String sql = "select * from book";
        BeanPropertyRowMapper<Book> bookBeanPropertyRowMapper = new BeanPropertyRowMapper<Book>(Book.class);
        List<Book> query = jdbcTemplate.query(sql, bookBeanPropertyRowMapper);
        return query;
    }

    public void batchInsert(List<Object[]> bookList) {
        String sql = "insert into book values(?,?,?)";
        int[] ints = jdbcTemplate.batchUpdate(sql, bookList);
        //遍历集合
        System.out.println(Arrays.toString(ints));
    }


}
