package com.booklending.controller;

import com.booklending.model.Result;
import com.booklending.model.User;
import com.booklending.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    // 跳转到登录页面
    @GetMapping("/login")
    public String toLogin() {
        return "user/login";
    }
    
    // 处理登录请求
    @PostMapping("/login")
    @ResponseBody
    public Result<User> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userService.login(username, password);
        if (user != null) {
            // 将用户信息存入session
            session.setAttribute("user", user);
            return Result.success(user);
        } else {
            return Result.error("用户名或密码错误");
        }
    }
    
    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
    // 跳转到注册页面
    @GetMapping("/register")
    public String toRegister(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }
    
    // 处理注册请求
    @PostMapping("/register")
    @ResponseBody
    public Result<Void> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(bindingResult.getFieldError().getDefaultMessage());
        }
        
        boolean success = userService.register(user);
        if (success) {
            return Result.success();
        } else {
            return Result.error("用户名已存在");
        }
    }
    
    // 跳转到用户列表页面（管理员）
    @GetMapping("/list")
    public String toUserList(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }
    
    // 跳转到添加用户页面（管理员）
    @GetMapping("/add")
    public String toAddUser(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }
    
    // 处理添加用户请求（管理员）
    @PostMapping("/add")
    @ResponseBody
    public Result<Void> addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(bindingResult.getFieldError().getDefaultMessage());
        }
        
        boolean success = userService.addUser(user);
        if (success) {
            return Result.success();
        } else {
            return Result.error("用户名已存在");
        }
    }
    
    // 跳转到编辑用户页面（管理员）
    @GetMapping("/edit/{id}")
    public String toEditUser(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user/edit";
    }
    
    // 处理编辑用户请求（管理员）
    @PostMapping("/edit")
    @ResponseBody
    public Result<Void> editUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(bindingResult.getFieldError().getDefaultMessage());
        }
        
        boolean success = userService.updateUser(user);
        if (success) {
            return Result.success();
        } else {
            return Result.error("更新失败");
        }
    }
    
    // 处理删除用户请求（管理员）
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Result<Void> deleteUser(@PathVariable Integer id) {
        boolean success = userService.deleteUser(id);
        if (success) {
            return Result.success();
        } else {
            return Result.error("删除失败");
        }
    }
    
    // 获取当前登录用户信息
    @GetMapping("/info")
    @ResponseBody
    public Result<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.error(401, "未登录");
        }
    }
}