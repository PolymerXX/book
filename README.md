# 书籍借阅管理系统

基于SSM框架（Spring+SpringMVC+MyBatis）开发的书籍借阅管理系统，实现了用户注册、登录、书籍管理、借阅管理等功能。

## 项目概述

本项目旨在通过开发一个书籍借阅管理系统，掌握Web应用开发中的前后端交互、数据库操作及SSM框架的应用。系统实现了用户管理、书籍管理、借阅管理等核心功能。

## 技术栈

- 后端：Spring Boot 2.7.18
- 持久层：MyBatis 2.3.1
- 数据库：MySQL/MariaDB
- 前端：Thymeleaf + Bootstrap + jQuery

## 功能模块

### 用户管理模块
- 用户注册：支持用户名、密码、邮箱等信息注册
- 用户登录：支持用户登录验证
- 角色管理：区分普通用户和管理员权限

### 书籍管理模块
- 书籍列表：展示所有书籍信息
- 书籍详情：查看书籍详细信息
- 书籍管理：管理员可进行书籍的增加、修改、删除操作

### 借阅管理模块
- 借阅书籍：用户可借阅书籍
- 借阅记录：查看个人借阅记录
- 归还书籍：用户可归还已借阅书籍

## 数据库设计

系统包含以下主要数据表：

1. **用户表(user)**：存储用户信息
   - id：用户ID
   - username：用户名
   - password：密码
   - email：邮箱
   - role：角色（0-普通用户，1-管理员）

2. **书籍表(book)**：存储书籍信息
   - id：书籍ID
   - title：书名
   - author：作者
   - publisher：出版社
   - publish_date：出版日期
   - isbn：ISBN号
   - description：描述
   - cover：封面图片URL
   - stock：库存数量

3. **借阅表(borrow)**：存储借阅记录
   - id：借阅记录ID
   - user_id：用户ID
   - book_id：书籍ID
   - borrow_date：借阅日期
   - return_date：归还日期
   - status：状态（0-借阅中，1-已归还）

## 安装与运行

### 环境要求
- JDK 17+
- Maven 3.6+
- MySQL/MariaDB 5.7+

### 步骤

1. 克隆项目
   ```bash
   git clone https://github.com/PolymerXX/book.git
   cd book/book-lending-system/book-lending-system
   ```

2. 配置数据库
   - 创建数据库：`book_lending_system`
   - 修改 `src/main/resources/application.properties` 中的数据库连接信息

3. 编译运行
   ```bash
   mvn clean package
   java -jar target/book-lending-system-1.0-SNAPSHOT.jar
   ```
   或使用Maven直接运行
   ```bash
   mvn spring-boot:run
   ```

4. 访问应用
   - 访问地址：http://localhost:12000/book-lending/
   - 默认管理员账号：admin/admin
   - 默认用户账号：user/user
   - 测试用户账号：testuser/testuser

## 项目结构

```
book-lending-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── booklending/
│   │   │           ├── config/         # 配置类
│   │   │           ├── controller/     # 控制器
│   │   │           ├── interceptor/    # 拦截器
│   │   │           ├── mapper/         # MyBatis映射接口
│   │   │           ├── model/          # 实体类
│   │   │           ├── service/        # 服务接口及实现
│   │   │           └── BookLendingSystemApplication.java  # 启动类
│   │   └── resources/
│   │       ├── mapper/                 # MyBatis XML映射文件
│   │       ├── templates/              # Thymeleaf模板
│   │       ├── application.properties  # 应用配置
│   │       └── schema.sql              # 数据库初始化脚本
│   └── test/                           # 测试代码
└── pom.xml                             # Maven配置
```

## 截图展示

### 首页
![首页](https://example.com/screenshots/home.png)

### 书籍列表
![书籍列表](https://example.com/screenshots/book-list.png)

### 书籍详情
![书籍详情](https://example.com/screenshots/book-detail.png)

### 借阅记录
![借阅记录](https://example.com/screenshots/borrow-list.png)

## 开发者

- PolymerXX

## 许可证

MIT License