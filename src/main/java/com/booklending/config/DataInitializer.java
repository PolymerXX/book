package com.booklending.config;

import com.booklending.mapper.BookMapper;
import com.booklending.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private BookMapper bookMapper;
    
    @Override
    public void run(String... args) throws Exception {
        // 更新书籍封面图片
        updateBookCovers();
    }
    
    private void updateBookCovers() {
        // 定义书籍ID到封面图片URL的映射 - 使用可访问的图片
        Map<Integer, String> coverMap = new HashMap<>();
        coverMap.put(1, "https://picsum.photos/200/300?random=1"); // 活着
        coverMap.put(2, "https://picsum.photos/200/300?random=2"); // 百年孤独
        coverMap.put(3, "https://picsum.photos/200/300?random=3"); // 三体
        coverMap.put(4, "https://picsum.photos/200/300?random=4"); // 围城
        coverMap.put(5, "https://picsum.photos/200/300?random=5"); // 平凡的世界
        
        // 更新每本书的封面图片
        for (Map.Entry<Integer, String> entry : coverMap.entrySet()) {
            try {
                Book book = bookMapper.selectById(entry.getKey());
                if (book != null) {
                    book.setCoverImage(entry.getValue());
                    bookMapper.update(book);
                    System.out.println("Updated cover for book: " + book.getTitle() + " -> " + entry.getValue());
                }
            } catch (Exception e) {
                System.err.println("Error updating cover for book ID " + entry.getKey() + ": " + e.getMessage());
            }
        }
    }
} 