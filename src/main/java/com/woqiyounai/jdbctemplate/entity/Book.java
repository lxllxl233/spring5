package com.woqiyounai.jdbctemplate.entity;

public class Book {
    private Integer bookId;
    private String bookName;
    private String bookStatus;

    public Book( ) {
    }

    public Book(Integer bookId, String bookName, String bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookStatus = bookStatus;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookStatus='" + bookStatus + '\'' +
                '}';
    }
}
