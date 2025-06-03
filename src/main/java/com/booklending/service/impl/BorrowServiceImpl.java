package com.booklending.service.impl;

import com.booklending.mapper.BookMapper;
import com.booklending.mapper.BorrowMapper;
import com.booklending.model.Book;
import com.booklending.model.Borrow;
import com.booklending.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {
    
    @Autowired
    private BorrowMapper borrowMapper;
    
    @Autowired
    private BookMapper bookMapper;
    
    @Override
    @Transactional
    public boolean borrowBook(Integer userId, Integer bookId) {
        // 检查该书是否已被借阅
        Borrow existBorrow = borrowMapper.selectByUserIdAndBookId(userId, bookId);
        if (existBorrow != null) {
            return false;
        }
        
        // 检查书籍库存
        Book book = bookMapper.selectById(bookId);
        if (book == null || book.getStock() <= 0 || book.getStatus() != 0) {
            return false;
        }
        
        // 减少库存
        bookMapper.updateStock(bookId, book.getStock() - 1);
        
        // 创建借阅记录
        Borrow borrow = new Borrow();
        borrow.setUserId(userId);
        borrow.setBookId(bookId);
        borrow.setBorrowDate(new Date());
        
        // 设置归还日期为30天后
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        borrow.setReturnDate(calendar.getTime());
        
        // 设置状态为借阅中
        borrow.setStatus(0);
        
        return borrowMapper.insert(borrow) > 0;
    }
    
    @Override
    @Transactional
    public boolean returnBook(Integer id) {
        Borrow borrow = borrowMapper.selectById(id);
        if (borrow == null || borrow.getStatus() != 0) {
            return false;
        }
        
        // 增加库存
        Book book = bookMapper.selectById(borrow.getBookId());
        bookMapper.updateStock(borrow.getBookId(), book.getStock() + 1);
        
        // 更新借阅状态为已归还
        return borrowMapper.updateStatus(id, 1) > 0;
    }
    
    @Override
    public boolean deleteBorrow(Integer id) {
        return borrowMapper.deleteById(id) > 0;
    }
    
    @Override
    public Borrow getBorrowById(Integer id) {
        return borrowMapper.selectById(id);
    }
    
    @Override
    public List<Borrow> getAllBorrows() {
        return borrowMapper.selectAll();
    }
    
    @Override
    public List<Borrow> getBorrowsByUserId(Integer userId) {
        return borrowMapper.selectByUserId(userId);
    }
    
    @Override
    public List<Borrow> getBorrowsByBookId(Integer bookId) {
        return borrowMapper.selectByBookId(bookId);
    }
}