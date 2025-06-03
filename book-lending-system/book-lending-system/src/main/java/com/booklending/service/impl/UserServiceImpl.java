package com.booklending.service.impl;

import com.booklending.mapper.UserMapper;
import com.booklending.model.User;
import com.booklending.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public boolean register(User user) {
        // 检查用户名是否已存在
        User existUser = userMapper.selectByUsername(user.getUsername());
        if (existUser != null) {
            return false;
        }
        
        // 设置默认角色为普通用户
        if (user.getRole() == null) {
            user.setRole(0);
        }
        
        return userMapper.insert(user) > 0;
    }
    
    @Override
    public User login(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }
    
    @Override
    public boolean addUser(User user) {
        // 检查用户名是否已存在
        User existUser = userMapper.selectByUsername(user.getUsername());
        if (existUser != null) {
            return false;
        }
        
        return userMapper.insert(user) > 0;
    }
    
    @Override
    public boolean deleteUser(Integer id) {
        return userMapper.deleteById(id) > 0;
    }
    
    @Override
    public boolean updateUser(User user) {
        return userMapper.update(user) > 0;
    }
    
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }
    
    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
    
    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }
    
    @Override
    public List<User> getUsersByCondition(User user) {
        return userMapper.selectByCondition(user);
    }
}