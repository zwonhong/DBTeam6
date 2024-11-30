-- Users 테이블
CREATE TABLE Users (
    user_ID VARCHAR(50) NOT NULL PRIMARY KEY,
    nickname VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    profilImg VARCHAR(255) DEFAULT NULL,
    wallPaper VARCHAR(255) DEFAULT NULL,
    introduction TEXT DEFAULT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    birth DATE DEFAULT NULL,
    phoneNum VARCHAR(15) DEFAULT NULL,
    gender ENUM('Male', 'Female', 'Other') DEFAULT NULL
);

-- Following 테이블
CREATE TABLE Following (
    following_ID VARCHAR(50) NOT NULL PRIMARY KEY,
    user_ID VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_ID) REFERENCES Users(user_ID)
);

-- Articles 테이블
CREATE TABLE Articles (
    articles_ID VARCHAR(50) NOT NULL PRIMARY KEY,
    user_ID VARCHAR(50) NOT NULL,
    contents TEXT NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_ID) REFERENCES Users(user_ID)
);

-- Comments 테이블
CREATE TABLE Comments (
    comment_ID VARCHAR(50) NOT NULL PRIMARY KEY,
    user_ID VARCHAR(50) NOT NULL,
    article_ID VARCHAR(50) NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    contents TEXT NOT NULL,
    FOREIGN KEY (user_ID) REFERENCES Users(user_ID),
    FOREIGN KEY (article_ID) REFERENCES Articles(articles_ID)
);

-- Recomments 테이블
/*CREATE TABLE Recomments (
    recomment_ID VARCHAR(50) PRIMARY KEY,             
    user_ID VARCHAR(50) NOT NULL,                     
    article_ID VARCHAR(50) NOT NULL,                   
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,   
    contents TEXT NOT NULL                            
);*/

-- Likes 테이블
CREATE TABLE Likes (
   user_ID varchar(50) NOT NULL,
   article_ID varchar(50) DEFAULT NULL,
   comment_ID varchar(50) DEFAULT NULL,
   KEY user_ID (user_ID),
   KEY article_ID (article_ID),
   KEY comment_ID (comment_ID),
   CONSTRAINT likes_ibfk_1 FOREIGN KEY (user_ID) REFERENCES users (user_ID),
   CONSTRAINT likes_ibfk_2 FOREIGN KEY (article_ID) REFERENCES articles (articles_ID),
   CONSTRAINT likes_ibfk_3 FOREIGN KEY (comment_ID) REFERENCES comments (comment_ID)
 ) ;
