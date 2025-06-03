package com.booklending.mapper;

import com.booklending.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    
    // 添加用户
    int insert(User user);
    
    // 根据ID删除用户
    int deleteById(Integer id);
    
    // 更新用户信息
    int update(User user);
    
    // 根据ID查询用户
    User selectById(Integer id);
    
    // 根据用户名查询用户
    User selectByUsername(String username);
    
    // 根据用户名和密码查询用户（登录）
    User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    
    // 查询所有用户
    List<User> selectAll();
    
    // 根据条件查询用户
    List<User> selectByCondition(User user);
}