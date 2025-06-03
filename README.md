# 📚 图书借阅管理系统

基于Spring Boot的现代化图书借阅管理系统，提供完整的图书管理和借阅服务功能。

## ✨ 项目特性

- 🚀 **现代化架构**：基于Spring Boot 2.7.18构建，代码结构清晰
- 💾 **轻量级数据库**：使用H2文件数据库，无需安装MySQL即可运行
- 🎨 **友好界面**：使用Thymeleaf模板引擎，界面简洁美观
- 🔒 **用户认证**：完整的用户登录注册系统
- 📖 **图书管理**：支持图书的增删改查操作
- 📋 **借阅管理**：完整的图书借阅和归还流程
- 🛡️ **权限控制**：基于拦截器的登录验证

## 🛠️ 技术栈

### 后端技术
- **Spring Boot** 2.7.18 - 主框架
- **Spring MVC** - Web层框架
- **MyBatis** - 持久层框架
- **H2 Database** - 轻量级数据库
- **Maven** - 项目构建工具
- **Lombok** - 代码简化工具

### 前端技术
- **Thymeleaf** - 模板引擎
- **HTML5** - 页面结构
- **CSS3** - 样式设计
- **JavaScript** - 交互逻辑

## 📋 功能模块

### 👤 用户管理
- 用户注册和登录
- 用户信息管理
- 用户权限控制

### 📚 图书管理
- 图书信息录入
- 图书信息查询
- 图书信息修改
- 图书删除

### 📖 借阅管理  
- 图书借阅申请
- 借阅记录查询
- 图书归还处理
- 借阅历史统计

## 🚀 快速开始

### 环境要求
- **Java**: JDK 17 或更高版本
- **Maven**: 3.6+ 
- **操作系统**: Windows/Linux/macOS

### 安装步骤

1. **克隆项目**
   ```bash
   git clone https://github.com/PolymerXX/book.git
   cd book
   ```

2. **编译项目**
   ```bash
   mvn clean compile
   ```

3. **运行项目**
   ```bash
   mvn spring-boot:run
   ```

4. **访问应用**
   
   打开浏览器访问：http://localhost:12001/book-lending/

### 📊 数据库说明

项目使用H2文件数据库，数据存储在 `./data/booklending.mv.db` 文件中。

**H2控制台访问**：
- URL: http://localhost:12001/book-lending/h2-console
- JDBC URL: `jdbc:h2:file:./data/booklending`  
- 用户名: `sa`
- 密码: (空)

## 📁 项目结构

```
src/
├── main/
│   ├── java/com/booklending/
│   │   ├── config/          # 配置类
│   │   ├── controller/      # 控制器层
│   │   ├── service/         # 业务逻辑层
│   │   ├── mapper/          # 数据访问层
│   │   ├── model/           # 实体类
│   │   └── interceptor/     # 拦截器
│   └── resources/
│       ├── mapper/          # MyBatis映射文件
│       ├── templates/       # Thymeleaf模板
│       ├── schema.sql       # 数据库初始化脚本
│       └── application.properties # 配置文件
└── test/                    # 测试代码
```

## ⚙️ 配置说明

### 主要配置项（application.properties）

```properties
# 服务器配置
server.port=12001
server.servlet.context-path=/book-lending

# 数据库配置
spring.datasource.url=jdbc:h2:file:./data/booklending
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# H2控制台
spring.h2.console.enabled=true

# MyBatis配置
mybatis.mapper-locations=classpath:mapper/*.xml
mybatis.type-aliases-package=com.booklending.model
```

## 🔧 开发指南

### 添加新功能

1. 在 `model` 包中创建实体类
2. 在 `mapper` 包中创建Mapper接口  
3. 在 `resources/mapper` 中创建XML映射文件
4. 在 `service` 包中实现业务逻辑
5. 在 `controller` 包中创建控制器
6. 在 `templates` 中创建页面模板

### 数据库操作

项目启动时会自动执行 `schema.sql` 初始化数据库表结构和基础数据。

## 🐛 常见问题

### 1. 编译错误："找不到符号"
**解决方案**：确保使用JDK 17，Lombok插件已正确安装。

### 2. 数据库连接失败
**解决方案**：检查data目录权限，确保应用有读写权限。

### 3. 端口被占用
**解决方案**：修改application.properties中的server.port配置。

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 🤝 贡献

欢迎提交Issue和Pull Request！

## 📞 联系方式

如有问题请提交Issue或联系项目维护者。

---

⭐ 如果这个项目对您有帮助，请给个Star支持一下！ 
