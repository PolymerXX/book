package com.booklending.service;

import com.booklending.model.User;

import java.util.List;

public interface UserService {
    
    // 用户注册
    boolean register(User user);
    
    // 用户登录
    User login(String username, String password);
    
    // 添加用户
    boolean addUser(User user);
    
    // 删除用户
    boolean deleteUser(Integer id);
    
    // 更新用户信息
    boolean updateUser(User user);
    
    // 根据ID查询用户
    User getUserById(Integer id);
    
    // 根据用户名查询用户
    User getUserByUsername(String username);
    
    // 查询所有用户
    List<User> getAllUsers();
    
    // 根据条件查询用户
    List<User> getUsersByCondition(User user);
}