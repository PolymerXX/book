-- 创建用户表
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `role` int(1) NOT NULL DEFAULT '0' COMMENT '角色：0-普通用户，1-管理员',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 创建书籍表
CREATE TABLE IF NOT EXISTS `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL COMMENT '书名',
  `author` varchar(50) NOT NULL COMMENT '作者',
  `description` text COMMENT '简介',
  `isbn` varchar(20) NOT NULL COMMENT 'ISBN',
  `publish_year` int(4) COMMENT '出版年份',
  `publisher` varchar(50) COMMENT '出版社',
  `cover_image` varchar(255) COMMENT '封面图片URL',
  `stock` int(11) NOT NULL DEFAULT '0' COMMENT '库存',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态：0-可借阅，1-不可借阅',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='书籍表';

-- 创建借阅表
CREATE TABLE IF NOT EXISTS `borrow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `book_id` int(11) NOT NULL COMMENT '书籍ID',
  `borrow_date` datetime NOT NULL COMMENT '借阅日期',
  `return_date` datetime NOT NULL COMMENT '应还日期',
  `actual_return_date` datetime COMMENT '实际归还日期',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '状态：0-借阅中，1-已归还',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_book_id` (`book_id`),
  CONSTRAINT `fk_borrow_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_borrow_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='借阅表';

-- 插入默认管理员账号
INSERT INTO `user` (`username`, `password`, `email`, `phone`, `role`, `create_time`, `update_time`)
SELECT 'admin', 'admin123', 'admin@example.com', '13800138000', 1, NOW(), NOW()
FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM `user` WHERE `username` = 'admin');

-- 插入测试用户
INSERT INTO `user` (`username`, `password`, `email`, `phone`, `role`, `create_time`, `update_time`)
SELECT 'user', 'user123', 'user@example.com', '13900139000', 0, NOW(), NOW()
FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM `user` WHERE `username` = 'user');

-- 插入测试书籍数据
INSERT INTO `book` (`title`, `author`, `description`, `isbn`, `publish_year`, `publisher`, `cover_image`, `stock`, `status`, `create_time`, `update_time`)
SELECT '活着', '余华', '《活着》是作家余华的代表作之一，讲述了农村人福贵悲惨的人生遭遇。福贵本是个阔少爷，因为嗜赌成性，卖掉了家里的田地，一贫如洗，穷困潦倒之际，又被抓去当壮丁，后来参加国民革命军，被解放军俘虏，回到家乡。此后更加悲惨的命运一次又一次降临到福贵身上，他的妻子、儿女和女婿相继死去，最后只剩下年老的福贵和一头老牛相依为命。', '9787506365437', 2012, '作家出版社', 'https://img3.doubanio.com/view/subject/l/public/s29053580.jpg', 10, 0, NOW(), NOW()
FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM `book` WHERE `title` = '活着' AND `author` = '余华');

INSERT INTO `book` (`title`, `author`, `description`, `isbn`, `publish_year`, `publisher`, `cover_image`, `stock`, `status`, `create_time`, `update_time`)
SELECT '百年孤独', '加西亚·马尔克斯', '《百年孤独》是魔幻现实主义文学的代表作，描写了布恩迪亚家族七代人的传奇故事，以及加勒比海沿岸小镇马孔多的百年兴衰，反映了拉丁美洲一个世纪以来风云变幻的历史。作品融入神话传说、民间故事、宗教典故等神秘因素，巧妙地糅合了现实与虚幻，展现出一个瑰丽的想象世界，成为20世纪最重要的经典文学巨著之一。', '9787544253994', 2011, '南海出版公司', 'https://img2.doubanio.com/view/subject/l/public/s6384944.jpg', 8, 0, NOW(), NOW()
FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM `book` WHERE `title` = '百年孤独' AND `author` = '加西亚·马尔克斯');

INSERT INTO `book` (`title`, `author`, `description`, `isbn`, `publish_year`, `publisher`, `cover_image`, `stock`, `status`, `create_time`, `update_time`)
SELECT '三体', '刘慈欣', '《三体》是刘慈欣创作的系列长篇科幻小说，由《三体》、《三体Ⅱ·黑暗森林》、《三体Ⅲ·死神永生》组成，第一部《三体》讲述了地球人类文明和三体文明的信息交流，以及三体人入侵地球的故事。', '9787536692930', 2008, '重庆出版社', 'https://img1.doubanio.com/view/subject/l/public/s2768378.jpg', 5, 0, NOW(), NOW()
FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM `book` WHERE `title` = '三体' AND `author` = '刘慈欣');

INSERT INTO `book` (`title`, `author`, `description`, `isbn`, `publish_year`, `publisher`, `cover_image`, `stock`, `status`, `create_time`, `update_time`)
SELECT '围城', '钱钟书', '《围城》是钱钟书所著的长篇小说，是中国现代文学史上一部风格独特的讽刺小说。被誉为"新儒家"的钱钟书先生对中国社会的独特观察和冷峻解析，以及对人性的深刻洞悉，使《围城》成为中国现代文学史上的经典之作。', '9787020090006', 2006, '人民文学出版社', 'https://img1.doubanio.com/view/subject/l/public/s1070222.jpg', 12, 0, NOW(), NOW()
FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM `book` WHERE `title` = '围城' AND `author` = '钱钟书');

INSERT INTO `book` (`title`, `author`, `description`, `isbn`, `publish_year`, `publisher`, `cover_image`, `stock`, `status`, `create_time`, `update_time`)
SELECT '平凡的世界', '路遥', '《平凡的世界》是中国作家路遥创作的一部全景式地表现中国当代城乡社会生活的百万字长篇小说。全书共三部。该书以中国70年代中期到80年代中期十年间为背景，通过复杂的矛盾纠葛，以孙少安和孙少平两兄弟为中心，刻画了当时社会各阶层众多普通人的形象。', '9787530216781', 2017, '北京十月文艺出版社', 'https://img3.doubanio.com/view/subject/l/public/s29634528.jpg', 7, 0, NOW(), NOW()
FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM `book` WHERE `title` = '平凡的世界' AND `author` = '路遥');

-- 插入测试借阅数据
INSERT INTO `borrow` (`user_id`, `book_id`, `borrow_date`, `return_date`, `status`, `create_time`, `update_time`)
SELECT 
    (SELECT id FROM `user` WHERE username = 'user' LIMIT 1),
    (SELECT id FROM `book` WHERE title = '活着' LIMIT 1),
    DATE_SUB(NOW(), INTERVAL 10 DAY),
    DATE_ADD(DATE_SUB(NOW(), INTERVAL 10 DAY), INTERVAL 30 DAY),
    0,
    NOW(),
    NOW()
FROM DUAL
WHERE EXISTS (SELECT 1 FROM `user` WHERE username = 'user')
AND EXISTS (SELECT 1 FROM `book` WHERE title = '活着')
AND NOT EXISTS (
    SELECT 1 FROM `borrow` 
    WHERE user_id = (SELECT id FROM `user` WHERE username = 'user' LIMIT 1)
    AND book_id = (SELECT id FROM `book` WHERE title = '活着' LIMIT 1)
    AND status = 0
);