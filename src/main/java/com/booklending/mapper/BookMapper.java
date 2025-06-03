package com.booklending.mapper;

import com.booklending.model.Book;

import java.util.List;

public interface BookMapper {
    
    // 添加书籍
    int insert(Book book);
    
    // 根据ID删除书籍
    int deleteById(Integer id);
    
    // 更新书籍信息
    int update(Book book);
    
    // 根据ID查询书籍
    Book selectById(Integer id);
    
    // 查询所有书籍
    List<Book> selectAll();
    
    // 根据条件查询书籍
    List<Book> selectByCondition(Book book);
    
    // 更新书籍库存
    int updateStock(Integer id, Integer stock);
}