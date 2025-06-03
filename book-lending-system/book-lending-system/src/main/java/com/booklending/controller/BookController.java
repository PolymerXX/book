package com.booklending.controller;

import com.booklending.model.Book;
import com.booklending.model.Result;
import com.booklending.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    // 跳转到书籍列表页面
    @GetMapping("/list")
    public String toBookList(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }
    
    // 跳转到书籍详情页面
    @GetMapping("/detail/{id}")
    public String toBookDetail(@PathVariable Integer id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book/detail";
    }
    
    // 跳转到添加书籍页面（管理员）
    @GetMapping("/add")
    public String toAddBook(Model model) {
        model.addAttribute("book", new Book());
        return "book/add";
    }
    
    // 处理添加书籍请求（管理员）
    @PostMapping("/add")
    @ResponseBody
    public Result<Void> addBook(@Valid @RequestBody Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(bindingResult.getFieldError().getDefaultMessage());
        }
        
        boolean success = bookService.addBook(book);
        if (success) {
            return Result.success();
        } else {
            return Result.error("添加失败");
        }
    }
    
    // 跳转到编辑书籍页面（管理员）
    @GetMapping("/edit/{id}")
    public String toEditBook(@PathVariable Integer id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book/edit";
    }
    
    // 处理编辑书籍请求（管理员）
    @PostMapping("/edit")
    @ResponseBody
    public Result<Void> editBook(@Valid @RequestBody Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(bindingResult.getFieldError().getDefaultMessage());
        }
        
        boolean success = bookService.updateBook(book);
        if (success) {
            return Result.success();
        } else {
            return Result.error("更新失败");
        }
    }
    
    // 处理删除书籍请求（管理员）
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Result<Void> deleteBook(@PathVariable Integer id) {
        boolean success = bookService.deleteBook(id);
        if (success) {
            return Result.success();
        } else {
            return Result.error("删除失败");
        }
    }
    
    // 处理上传书籍封面图片请求
    @PostMapping("/upload")
    @ResponseBody
    public Result<String> uploadCoverImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("请选择文件");
        }
        
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成新文件名
        String newFileName = UUID.randomUUID().toString() + suffixName;
        // 指定上传目录
        String filePath = "src/main/resources/static/images/";
        
        // 创建目录
        File dest = new File(filePath + newFileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        
        try {
            // 保存文件
            file.transferTo(dest);
            return Result.success("/images/" + newFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("上传失败");
        }
    }
    
    // 搜索书籍
    @GetMapping("/search")
    public String searchBooks(@RequestParam(required = false) String keyword, Model model) {
        Book condition = new Book();
        condition.setTitle(keyword);
        List<Book> books = bookService.getBooksByCondition(condition);
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        return "book/list";
    }
}