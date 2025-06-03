package com.booklending.model;

import lombok.Data;

import java.util.Date;

@Data
public class Borrow {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Date borrowDate;
    private Date returnDate;
    private Date actualReturnDate;
    
    // 0: 借阅中, 1: 已归还, 2: 逾期未还
    private Integer status;
    
    private Date createTime;
    private Date updateTime;
    
    // 非数据库字段，用于展示
    private User user;
    private Book book;
}