package com.booklending.service.impl;

import com.booklending.mapper.BookMapper;
import com.booklending.model.Book;
import com.booklending.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    
    @Autowired
    private BookMapper bookMapper;
    
    @Override
    public boolean addBook(Book book) {
        // 设置默认状态为可借阅
        if (book.getStatus() == null) {
            book.setStatus(0);
        }
        
        return bookMapper.insert(book) > 0;
    }
    
    @Override
    public boolean deleteBook(Integer id) {
        return bookMapper.deleteById(id) > 0;
    }
    
    @Override
    public boolean updateBook(Book book) {
        return bookMapper.update(book) > 0;
    }
    
    @Override
    public Book getBookById(Integer id) {
        return bookMapper.selectById(id);
    }
    
    @Override
    public List<Book> getAllBooks() {
        return bookMapper.selectAll();
    }
    
    @Override
    public List<Book> getBooksByCondition(Book book) {
        return bookMapper.selectByCondition(book);
    }
    
    @Override
    public boolean updateBookStock(Integer id, Integer stock) {
        return bookMapper.updateStock(id, stock) > 0;
    }
}