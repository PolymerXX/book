package com.booklending.mapper;

import com.booklending.model.Borrow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BorrowMapper {
    
    // 添加借阅记录
    int insert(Borrow borrow);
    
    // 根据ID删除借阅记录
    int deleteById(Integer id);
    
    // 更新借阅记录
    int update(Borrow borrow);
    
    // 根据ID查询借阅记录
    Borrow selectById(Integer id);
    
    // 查询所有借阅记录
    List<Borrow> selectAll();
    
    // 根据用户ID查询借阅记录
    List<Borrow> selectByUserId(Integer userId);
    
    // 根据书籍ID查询借阅记录
    List<Borrow> selectByBookId(Integer bookId);
    
    // 根据用户ID和书籍ID查询借阅记录
    Borrow selectByUserIdAndBookId(@Param("userId") Integer userId, @Param("bookId") Integer bookId);
    
    // 更新借阅状态
    int updateStatus(@Param("id") Integer id, @Param("status") Integer status);
}