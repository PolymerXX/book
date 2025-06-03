package com.booklending.service;

import com.booklending.model.Book;

import java.util.List;

public interface BookService {
    
    // 添加书籍
    boolean addBook(Book book);
    
    // 删除书籍
    boolean deleteBook(Integer id);
    
    // 更新书籍信息
    boolean updateBook(Book book);
    
    // 根据ID查询书籍
    Book getBookById(Integer id);
    
    // 查询所有书籍
    List<Book> getAllBooks();
    
    // 根据条件查询书籍
    List<Book> getBooksByCondition(Book book);
    
    // 更新书籍库存
    boolean updateBookStock(Integer id, Integer stock);
}