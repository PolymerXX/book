package com.booklending.service;

import com.booklending.model.Borrow;

import java.util.List;

public interface BorrowService {
    
    // 借阅书籍
    boolean borrowBook(Integer userId, Integer bookId);
    
    // 归还书籍
    boolean returnBook(Integer id);
    
    // 删除借阅记录
    boolean deleteBorrow(Integer id);
    
    // 根据ID查询借阅记录
    Borrow getBorrowById(Integer id);
    
    // 查询所有借阅记录
    List<Borrow> getAllBorrows();
    
    // 根据用户ID查询借阅记录
    List<Borrow> getBorrowsByUserId(Integer userId);
    
    // 根据书籍ID查询借阅记录
    List<Borrow> getBorrowsByBookId(Integer bookId);
}