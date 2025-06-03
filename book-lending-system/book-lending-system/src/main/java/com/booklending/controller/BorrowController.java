package com.booklending.controller;

import com.booklending.model.Borrow;
import com.booklending.model.Result;
import com.booklending.model.User;
import com.booklending.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/borrow")
public class BorrowController {
    
    @Autowired
    private BorrowService borrowService;
    
    // 跳转到借阅记录列表页面
    @GetMapping("/list")
    public String toBorrowList(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Borrow> borrows;
        
        // 管理员可以查看所有借阅记录，普通用户只能查看自己的借阅记录
        if (user.getRole() == 1) {
            borrows = borrowService.getAllBorrows();
        } else {
            borrows = borrowService.getBorrowsByUserId(user.getId());
        }
        
        model.addAttribute("borrows", borrows);
        return "borrow/list";
    }
    
    // 处理借阅书籍请求
    @PostMapping("/borrow/{bookId}")
    @ResponseBody
    public Result<Void> borrowBook(@PathVariable Integer bookId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Result.error(401, "请先登录");
        }
        
        boolean success = borrowService.borrowBook(user.getId(), bookId);
        if (success) {
            return Result.success();
        } else {
            return Result.error("借阅失败，可能是库存不足或已借阅该书");
        }
    }
    
    // 处理归还书籍请求
    @PostMapping("/return/{id}")
    @ResponseBody
    public Result<Void> returnBook(@PathVariable Integer id) {
        boolean success = borrowService.returnBook(id);
        if (success) {
            return Result.success();
        } else {
            return Result.error("归还失败");
        }
    }
    
    // 处理删除借阅记录请求（管理员）
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Result<Void> deleteBorrow(@PathVariable Integer id) {
        boolean success = borrowService.deleteBorrow(id);
        if (success) {
            return Result.success();
        } else {
            return Result.error("删除失败");
        }
    }
    
    // 查看借阅详情
    @GetMapping("/detail/{id}")
    public String toBorrowDetail(@PathVariable Integer id, Model model) {
        Borrow borrow = borrowService.getBorrowById(id);
        model.addAttribute("borrow", borrow);
        return "borrow/detail";
    }
}