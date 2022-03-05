/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : ssmbuild

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 05/03/2022 19:21:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of blog
-- ----------------------------
BEGIN;
INSERT INTO `blog` VALUES (1, 'test', 'param', '# title');
INSERT INTO `blog` VALUES (2, 'ssm配置--地狱级', '周靖宇', '@[TOC](记录一下ssm整合的步骤)\n\n\n\n> **环境：**\n> \n>  - IDEA 2021.2.4\n>  - MySQL 8.0.22\n>  - Tomcat 8.5.73\n>  - Maven 3.8.4\n\n### 搭建数据库\n\n```sql\nCREATE DATABASE `ssmbuild`;\nUSE `ssmbuild`;\nDROP TABLE IF EXISTS `books`;\nCREATE TABLE `books`(\n    `bookID` INT(10) NOT NULL AUTO_INCREMENT COMMENT \'书id\',\n    `bookName` VARCHAR(100) NOT NULL COMMENT \'书名\',\n    `bookCounts` INT(11) NOT NULL COMMENT \'数量\',\n    `detail` VARCHAR(200) NOT NULL COMMENT \'描述\',\n    KEY `bookID`(`bookID`)        \n)ENGINE=INNODB DEFAULT CHARSET=utf8;\nINSERT INTO `books` (`bookID`,`bookName`,`bookCounts`,`detail`)VALUES\n(1,\'JAVA\',1,\'从入门到入土\'),\n(2,\'MySQL\',1,\'从删库到跑路\'),\n(3,\'Linux\',1,\'从入门到坐牢\');\n```\n\n### 导入依赖\n\n>  Junit\n 数据库驱动\n 连接池\n servlet\n jsp\n MyBatis\n mybatis-spring\n spring\n\n\n```xml\n<!--prom.xml-->\n<dependencies>\n    <dependency>\n        <groupId>junit</groupId>\n        <artifactId>junit</artifactId>\n        <version>4.12</version>\n        <scope>test</scope>\n    </dependency>\n    <dependency>\n        <groupId>mysql</groupId>\n        <artifactId>mysql-connector-java</artifactId>\n        <version>8.0.22</version>\n    </dependency>\n    <dependency>\n        <groupId>javax.servlet</groupId>\n        <artifactId>servlet-api</artifactId>\n        <version>2.5</version>\n    </dependency>\n    <dependency>\n        <groupId>javax.servlet.jsp</groupId>\n        <artifactId>jsp-api</artifactId>\n        <version>2.2</version>\n    </dependency>\n    <dependency>\n        <groupId>javax.servlet</groupId>\n        <artifactId>jstl</artifactId>\n        <version>1.2</version>\n    </dependency>\n    <dependency>\n        <groupId>org.mybatis</groupId>\n        <artifactId>mybatis</artifactId>\n        <version>3.5.6</version>\n    </dependency>\n    <dependency>\n        <groupId>org.mybatis</groupId>\n        <artifactId>mybatis-spring</artifactId>\n        <version>2.0.6</version>\n    </dependency>\n    <dependency>\n        <groupId>org.springframework</groupId>\n        <artifactId>spring-webmvc</artifactId>\n        <version>5.3.3</version>\n    </dependency>\n    <dependency>\n        <groupId>org.springframework</groupId>\n        <artifactId>spring-jdbc</artifactId>\n        <version>5.3.3</version>\n    </dependency>\n    <dependency>\n        <groupId>com.mchange</groupId>\n        <artifactId>c3p0</artifactId>\n        <version>0.9.5.2</version>\n    </dependency>\n        <dependency>\n            <groupId>org.projectlombok</groupId>\n            <artifactId>lombok</artifactId>\n            <version>1.18.16</version>\n        </dependency>\n</dependencies>\n```\n\n### 静态资源导出\n\n```xml\n<!--prom.xml-->\n<build>\n    <resources>\n        <resource>\n            <directory>src/main/resources</directory>\n            <includes>\n                <include>**/*.properties</include>\n                <include>**/*.xml</include>\n            </includes>\n            <filtering>true</filtering>\n        </resource>\n        <resource>\n            <directory>src/main/java</directory>\n            <includes>\n                <include>**/*.properties</include>\n                <include>**/*.xml</include>\n            </includes>\n            <filtering>true</filtering>\n        </resource>\n    </resources>\n</build>\n```\n### 连接数据库\n![在这里插入图片描述](https://img-blog.csdnimg.cn/aecb2ba85dac42559a6cf3b38ea44711.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAcGFyYW1Ba2k=,size_20,color_FFFFFF,t_70,g_se,x_16)\n### 创建包与核心配置文件\n\n - 包结构如下：\n\n![在这里插入图片描述](https://img-blog.csdnimg.cn/6413f3aad98942018c0bb19ecc6c2d03.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAcGFyYW1Ba2k=,size_20,color_FFFFFF,t_70,g_se,x_16)\n\n - applicationContext.xml（作为整合配置文件的总文件）\n\n```xml\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<beans xmlns=\"http://www.springframework.org/schema/beans\"\n       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n           http://www.springframework.org/schema/beans/spring-beans.xsd\">\n</beans>\n```\n\n - mybatis-config.xml（作为Mybatis的配置文件）\n\n```xml\n<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<!DOCTYPE configuration\n        PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\"\n        \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\n<configuration>\n    <typeAliases>\n        <package name=\"com.paramaki.pojo\"/>\n    </typeAliases>\n</configuration>\n```\n\n - database.properties\n\n```xml\njdbc.driver=com.mysql.cj.jdbc.Driver\njdbc.url=jdbc:mysql://localhost:3306/ssmbuild?useSSL=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai\njdbc.username=root\njdbc.password=\n```\n\n### 编写实体类\n\n - Books.java\n\n```java\n@Data\n@NoArgsConstructor\n@AllArgsConstructor\npublic class Books {\n    private int bookID;\n    private String bookName;\n    private int bookCounts;\n    private String detail;\n}\n```\n### Mapper接口\n\n - BookMapper.java\n\n```java\npublic interface BookMapper {\n    //增加一本书\n    int addBook(Books books);\n    //删除一本书\n    int deleteBookById(@Param(\"bookID\") int id);\n    //更新一本书\n    int updateBook(Books books);\n    //查询一本书\n    Books queryById(@Param(\"bookID\") int id);\n    //查询全部书\n    List<Books> queryAllBook();\n}\n```\n**实现BookMapper借接口**\n\n - BookMapper.xml\n\n```xml\n<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<!DOCTYPE mapper\n        PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\"\n        \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n<mapper namespace=\"com.paramaki.dao.BookMapper\">\n    <insert id=\"addBook\" parameterType=\"Book\">\n        insert into ssmbuild.books(bookName, bookCounts, detail)\n        values (#{bookName}, #{bookCounts}, #{detail});\n    </insert>\n\n    <delete id=\"deleteBookById\" parameterType=\"int\">\n        delete\n        from ssmbuild.books\n        where bookID = #{bookId};\n    </delete>\n\n    <update id=\"updateBook\" parameterType=\"Book\">\n        update ssmbuild.books set bookName = #{bookName},bookCounts =#{bookCounts},detail=#{detail}\n        where bookID=#{bookId};\n    </update>\n\n    <select id=\"queryBookById\" parameterType=\"int\">\n        select * from ssmbuild.books where bookID = #{bookId};\n    </select>\n\n    <select id=\"queryAllBook\" parameterType=\"Book\">\n        select * from ssmbuild.books;\n    </select>\n</mapper>\n\n```\n\n**注册接口**\n\n - mybatis-config.xml\n\n```xml\n<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<!--mybatis的核心配置文件-->\n<!DOCTYPE configuration\n        PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\"\n        \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\n<configuration>\n    <typeAliases>\n        <package name=\"com.paramaki.pojo\"/>\n    </typeAliases>\n\n    <mappers>\n        <mapper class=\"com.paramaki.dao.BookMapper\"/>\n    </mappers>\n</configuration>\n```\n\n### Servise接口\n\n - BookService.java\n\n```java\npublic interface BookService {\n    //增加一本书\n    int addBook(Books books);\n    //删除一本书\n    int deleteBookById(int id);\n    //更新一本书\n    int updateBook(Books books);\n    //查询一本书\n    Books queryById(int id);\n    //查询全部书\n    List<Books> queryAllBook();\n}\n```\n\n实现service接口\n\n - BookServiceImpl.java\n\n```java\npackage com.paramaki.service;\n\nimport com.paramaki.dao.BookMapper;\nimport com.paramaki.pojo.Book;\n\nimport java.util.List;\n\npublic class BookServiceImpl implements BookService{\n\n    // service层调dao层\n    private BookMapper bookMapper;\n\n    public void setBookMapper(BookMapper bookMapper) {\n        this.bookMapper = bookMapper;\n    }\n\n    @Override\n    public int addBook(Book book) {\n        return bookMapper.addBook(book);\n    }\n\n    @Override\n    public int deleteBookById(int id) {\n        return bookMapper.deleteBookById(id);\n    }\n\n    @Override\n    public int updateBook(Book book) {\n        return bookMapper.updateBook(book);\n    }\n\n    @Override\n    public Book queryBookById(int id) {\n        return bookMapper.queryBookById(id);\n    }\n\n    @Override\n    public List<Book> queryAllBook() {\n        return bookMapper.queryAllBook();\n    }\n}\n```\n### 完善数据库配置\n\n - spring-mapper.xml\n \n \n\n```xml\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<beans xmlns=\"http://www.springframework.org/schema/beans\"\n       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n       xmlns:context=\"http://www.springframework.org/schema/context\"\n       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n           http://www.springframework.org/schema/beans/spring-beans.xsd\n           http://www.springframework.org/schema/context\n           https://www.springframework.org/schema/context/spring-context.xsd\">\n\n    <!--1.关联数据库文件-->\n    <context:property-placeholder location=\"database.properties\"/>\n\n    <!--2.连接池\n        dbcp: 半自动化操作，不能自动连接\n        c3p0: 自动化操作（自动化加载配置文件，并且可以自动设置到对象中）\n        druid:\n        hikari\n    -->\n    <bean id=\"dataSource\" class=\"com.mchange.v2.c3p0.ComboPooledDataSource\">\n        <property name=\"driverClass\" value=\"${jdbc.driver}\"/>\n        <property name=\"jdbcUrl\" value=\"${jdbc.url}\"/>\n        <property name=\"user\" value=\"${jdbc.username}\"/>\n        <property name=\"password\" value=\"${jdbc.password}\"/>\n\n        <property name=\"maxPoolSize\" value=\"30\"/>\n        <property name=\"minPoolSize\" value=\"10\"/>\n        <property name=\"autoCommitOnClose\" value=\"false\"/>\n        <property name=\"checkoutTimeout\" value=\"5000\"/>\n        <property name=\"acquireRetryAttempts\" value=\"2\"/>\n    </bean>\n\n    <!--3.sqlSessionFactory-->\n    <bean id=\"sqlSessionFactory\" class=\"org.mybatis.spring.SqlSessionFactoryBean\">\n        <property name=\"dataSource\" ref=\"dataSource\"/>\n        <!--绑定Mybatis的配置文件-->\n        <property name=\"configLocation\" value=\"classpath:mybatis-config.xml\"/>\n    </bean>\n\n    <!--配置dao接口扫描包，动态实现Dao接口可以注入到Spring容器中-->\n    <bean class=\"org.mybatis.spring.mapper.MapperScannerConfigurer\">\n        <!--注入sqlSessionFactory-->\n        <property name=\"sqlSessionTemplateBeanName\" value=\"sqlSessionFactory\"/>\n        <!--扫描dao包-->\n        <property name=\"basePackage\" value=\"com.paramaki.dao\"/>\n    </bean>\n\n</beans>\n```\n\n - spring-service.xml\n\n```xml\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<beans xmlns=\"http://www.springframework.org/schema/beans\"\n       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n       xmlns:context=\"http://www.springframework.org/schema/context\"\n       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n           http://www.springframework.org/schema/beans/spring-beans.xsd\n           http://www.springframework.org/schema/context\n           https://www.springframework.org/schema/context/spring-context.xsd\">\n\n    <!--1.扫描service下的包-->\n    <context:component-scan base-package=\"com.paramaki.service\"/>\n\n    <!--2.将所有业务类注入到spring中，可以通过配置或者注解实现-->\n    <bean id=\"BookServiceImpl\" class=\"com.paramaki.service.BookServiceImpl\">\n        <property name=\"bookMapper\" ref=\"bookMapper\"/>\n    </bean>\n\n    <!--3.声明事务位置-->\n    <bean id=\"transactionManager\" class=\"org.springframework.jdbc.datasource.DataSourceTransactionManager\">\n        <!--注入数据源-->\n        <property name=\"dataSource\" ref=\"dataSource\"/>\n    </bean>\n\n    <!--4.aop事务支持-->\n\n</beans>\n```\n**然后添加web支持**\n### 修改web.xml配置文件\n\n - web.xml\n\n```xml\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<web-app xmlns=\"http://xmlns.jcp.org/xml/ns/javaee\"\n         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n         xsi:schemaLocation=\"http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd\"\n         version=\"4.0\">\n    <servlet>\n        <servlet-name>springmvc</servlet-name>\n        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>\n        <init-param>\n            <param-name>contextConfigLocation</param-name>\n            <param-value>classpath:applicationContext.xml</param-value>\n        </init-param>\n        <load-on-startup>1</load-on-startup>\n    </servlet>\n    <servlet-mapping>\n        <servlet-name>springmvc</servlet-name>\n        <url-pattern>/</url-pattern>\n    </servlet-mapping>\n    <filter>\n        <filter-name>encodingFilter</filter-name>\n        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>\n        <init-param>\n            <param-name>encoding</param-name>\n            <param-value>utf-8</param-value>\n        </init-param>\n    </filter>\n    <filter-mapping>\n        <filter-name>encodingFilter</filter-name>\n        <url-pattern>/*</url-pattern>\n    </filter-mapping>\n    <session-config>\n        <session-timeout>15</session-timeout>\n    </session-config>\n</web-app>\n```\n\n - spring-mvc.xml\n\n```xml\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<beans xmlns=\"http://www.springframework.org/schema/beans\"\n       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n       xmlns:context=\"http://www.springframework.org/schema/context\"\n       xmlns:mvc=\"http://www.springframework.org/schema/mvc\"\n       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n           http://www.springframework.org/schema/beans/spring-beans.xsd\n           http://www.springframework.org/schema/mvc\n           http://www.springframework.org/schema/mvc/spring-mvc.xsd\n           http://www.springframework.org/schema/context\n           https://www.springframework.org/schema/context/spring-context.xsd\">\n\n    <!--注解驱动-->\n    <mvc:annotation-driven/>\n    <!--静态资源过滤-->\n    <mvc:default-servlet-handler/>\n    <!--扫描包：controller-->\n    <context:component-scan base-package=\"com.paramaki.controller\"/>\n    <!--视图解析器-->\n    <bean class=\"org.springframework.web.servlet.view.InternalResourceViewResolver\">\n        <property name=\"prefix\" value=\"/WEB-INF/jsp/\" />\n        <property name=\"suffix\" value=\".jsp\"/>\n    </bean>\n\n</beans>\n```\n### 将所有的spring的xml导入到总配置中\n\n - ApplicationContext.xml\n\n```xml\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<beans xmlns=\"http://www.springframework.org/schema/beans\"\n       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n       xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n           http://www.springframework.org/schema/beans/spring-beans.xsd\">\n\n    <import resource=\"classpath:spring-dao.xml\"/>\n    <import resource=\"classpath:spring-service.xml\"/>\n    <import resource=\"classpath:spring-mvc.xml\"/>\n\n</beans>\n```\n到这里，就整合结束了，还是比较折磨人的🐑');
COMMIT;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `bookID` int NOT NULL AUTO_INCREMENT COMMENT '书id',
  `bookName` varchar(100) NOT NULL COMMENT '书名',
  `bookCounts` int NOT NULL COMMENT '数量',
  `detail` varchar(200) NOT NULL COMMENT '描述',
  PRIMARY KEY (`bookID`),
  KEY `bookID` (`bookID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of books
-- ----------------------------
BEGIN;
INSERT INTO `books` VALUES (1, 'JAVA', 3, '从跨平台到跨行业');
INSERT INTO `books` VALUES (2, 'MySQL', 10, '从删库到跑路');
INSERT INTO `books` VALUES (3, 'Linux', 51, '从入门到坐牢');
INSERT INTO `books` VALUES (4, 'C++', 99, '从入门到转行');
INSERT INTO `books` VALUES (5, 'IOS开发', 3, '从入门到下架');
INSERT INTO `books` VALUES (7, 'Debug455个经典案例', 26, '让电脑开机蓝屏');
INSERT INTO `books` VALUES (8, '服务器运维管理', 75, '从网维到网管');
INSERT INTO `books` VALUES (9, 'Office三件套', 71, '从入门到手写');
INSERT INTO `books` VALUES (10, '黑客攻防', 23, '从入门到入狱');
INSERT INTO `books` VALUES (11, '零基础学c语言', 32, '从零基础到负基础');
INSERT INTO `books` VALUES (12, 'CSS', 73, '从绘制框架到改行画画');
INSERT INTO `books` VALUES (13, 'c语言', 45, '从研发到脱发');
INSERT INTO `books` VALUES (14, 'Spring实战', 20, '从入门到炒粉');
COMMIT;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `CityID` int NOT NULL AUTO_INCREMENT,
  `CityName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `State` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Country` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`CityID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of city
-- ----------------------------
BEGIN;
INSERT INTO `city` VALUES (1, '北京', '1', 'CN');
INSERT INTO `city` VALUES (2, '广州', '1', 'CN');
COMMIT;

-- ----------------------------
-- Table structure for library
-- ----------------------------
DROP TABLE IF EXISTS `library`;
CREATE TABLE `library` (
  `LibraryID` int unsigned NOT NULL AUTO_INCREMENT COMMENT '图书馆ID',
  `LibraryName` varchar(20) NOT NULL COMMENT '图书馆名',
  `LibraryAddress` varchar(50) DEFAULT NULL COMMENT '图书馆位置',
  `LibraryGrade` int DEFAULT NULL COMMENT '图书馆等级，共三级',
  PRIMARY KEY (`LibraryID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of library
-- ----------------------------
BEGIN;
INSERT INTO `library` VALUES (1, '花都图书馆', '广东省广州市花都区新华街宝华路38号', 1);
INSERT INTO `library` VALUES (2, '江西财经大学图书馆', '江西省南昌市昌北国家经济技术开发区双港东大街169号', 1);
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userID` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(12) NOT NULL COMMENT '用户密码',
  `status` int NOT NULL DEFAULT '1' COMMENT '用户状态（0锁定，1可用）',
  `userEmail` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户邮箱',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=976944084 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (1, 'admin', '123456', 1, '976944083@qq.com');
INSERT INTO `users` VALUES (2, 'zjy', '1', 1, 'vemnmt@foxmail.com');
INSERT INTO `users` VALUES (5, 'root', '123456', 1, '111');
INSERT INTO `users` VALUES (12, '12', '12', 1, '123@123');
INSERT INTO `users` VALUES (13, '13', '13', 0, '13@13');
INSERT INTO `users` VALUES (976944083, 'param', 'zhoujy123', 0, '976944083@qq.com');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
