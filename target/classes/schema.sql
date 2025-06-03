-- 创建用户表
CREATE TABLE IF NOT EXISTS "user" (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  role INT DEFAULT 0,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 创建书籍表
CREATE TABLE IF NOT EXISTS book (
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  author VARCHAR(50) NOT NULL,
  description VARCHAR(1000),
  isbn VARCHAR(20) NOT NULL,
  publish_year INT,
  publisher VARCHAR(50),
  cover_image VARCHAR(255),
  stock INT DEFAULT 0,
  status INT DEFAULT 0,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 创建借阅表
CREATE TABLE IF NOT EXISTS borrow (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT,
  book_id INT,
  borrow_date TIMESTAMP,
  return_date TIMESTAMP,
  actual_return_date TIMESTAMP,
  status INT DEFAULT 0,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 插入默认管理员账号
INSERT INTO "user" (username, password, email, phone, role) 
VALUES ('admin', 'admin123', 'admin@example.com', '13800138000', 1);

-- 插入测试用户
INSERT INTO "user" (username, password, email, phone, role) 
VALUES ('user', 'user123', 'user@example.com', '13900139000', 0);

-- 插入测试书籍数据（添加封面图片）
INSERT INTO book (title, author, description, isbn, publish_year, publisher, cover_image, stock, status) 
VALUES ('活着', '余华', '一部关于生存与命运的经典小说。', '9787506365437', 2012, '作家出版社', 'https://img1.doubanio.com/view/subject/l/public/s1074291.jpg', 10, 0);

INSERT INTO book (title, author, description, isbn, publish_year, publisher, cover_image, stock, status) 
VALUES ('百年孤独', '加西亚马尔克斯', '魔幻现实主义文学的代表作品。', '9787544253994', 2011, '南海出版公司', 'https://img9.doubanio.com/view/subject/l/public/s6384944.jpg', 8, 0);

INSERT INTO book (title, author, description, isbn, publish_year, publisher, cover_image, stock, status) 
VALUES ('三体', '刘慈欣', '中国科幻文学的里程碑作品。', '9787536692930', 2008, '重庆出版社', 'https://img1.doubanio.com/view/subject/l/public/s2768378.jpg', 5, 0);

INSERT INTO book (title, author, description, isbn, publish_year, publisher, cover_image, stock, status) 
VALUES ('围城', '钱钟书', '一部幽默而深刻的讽刺小说。', '9787020090006', 2006, '人民文学出版社', 'https://img1.doubanio.com/view/subject/l/public/s1070959.jpg', 12, 0);

INSERT INTO book (title, author, description, isbn, publish_year, publisher, cover_image, stock, status) 
VALUES ('平凡的世界', '路遥', '描写中国农村社会生活的长篇小说。', '9787530216781', 2017, '北京十月文艺出版社', 'https://img9.doubanio.com/view/subject/l/public/s1085042.jpg', 7, 0);