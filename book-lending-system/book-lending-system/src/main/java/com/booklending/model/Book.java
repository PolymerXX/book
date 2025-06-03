package com.booklending.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Book {
    private Integer id;
    
    @NotBlank(message = "书名不能为空")
    private String title;
    
    @NotBlank(message = "作者不能为空")
    private String author;
    
    private String description;
    
    @NotBlank(message = "ISBN不能为空")
    private String isbn;
    
    @NotNull(message = "出版年份不能为空")
    @Min(value = 1000, message = "出版年份不合法")
    private Integer publishYear;
    
    private String publisher;
    
    private String coverImage;
    
    @NotNull(message = "库存数量不能为空")
    @Min(value = 0, message = "库存数量不能小于0")
    private Integer stock;
    
    // 0: 可借阅, 1: 不可借阅
    private Integer status;
    
    private Date createTime;
    private Date updateTime;
}