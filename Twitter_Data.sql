-- SHOW TABLES;
-- SELECT * FROM Users; 
-- SELECT * FROM Following;
-- SELECT * FROM Articles;
-- SELECT * FROM Comments; 
-- SELECT * FROM Recomments;
-- SELECT * FROM Likes;
-- DESCRIBE Users;
-- -- Foreign Key로 참조된 테이블부터 드롭
-- DROP TABLE IF EXISTS Likes;
-- DROP TABLE IF EXISTS Recomments;
-- DROP TABLE IF EXISTS Comments;
-- DROP TABLE IF EXISTS Articles;
-- DROP TABLE IF EXISTS Following;
-- DROP TABLE IF EXISTS Users;

INSERT INTO Users (user_ID ,nickname, password, profilImg, wallPaper, introduction, birth, phoneNum, gender) 
VALUES ('john03','john_doe', 'password123', 'profile.jpg', 'wallpaper.jpg', 'Hello, I am John!', '1990-05-15', '010-1234-5678', 'Male');

INSERT INTO Users (user_ID, nickname, password) VALUES
('Luffy', '루피', 'password1'),
('Shinchan', '짱구','password2'),
('Naruto', '나루토', 'password3');

INSERT INTO Following (following_ID, user_ID) VALUES
('조로', 'Luffy'),
('나미', 'Luffy'),
('우솝', 'Luffy'),
('상디', 'Luffy'),
('로빈', 'Luffy'),
('철수', 'Shinchan'),
('유리', 'Shinchan'),
('훈이', 'Shinchan'),
('맹구', 'Shinchan'),
('봉미선', 'Shinchan'),
('신형만', 'Shinchan'),
('사스케', 'Naruto'),
('히나타', 'Naruto'),
('사쿠라', 'Naruto'),
('카카시', 'Naruto'),
('마다라', 'Naruto');

INSERT INTO Articles (articles_ID, user_ID, contents) VALUES
('01', 'Luffy', '에이스 구출 성공 후 멋지게 사진 한 장'),
('02', 'Shinchan', '아빠 엄마 짱아 흰둥이랑 함께라면 너무 행복해~'),
('03', 'Naruto', '이루카 선생님 라면 감사하다니깐!!'),
('04', 'Naruto', '미안하다 이거 보여주려고 어그로끌었다.. 나루토 사스케 싸움수준 ㄹㅇ실화냐? 진짜 세계관최강자들의 싸움이다..'),
('05', 'Shinchan', '우리 짱아 납치한 놈 혼내주러 간다!'),
('06', 'Luffy', '너 내 동료가 되라!'),
('07', 'Luffy', '2년만에 모인 우리 해적단!'),
('08', 'Luffy', '동료와 함께라면 그 무엇도 두렵지 않아'),
('09', 'Shinchan', '떡잎마을 방범대 파이어!'),
('10', 'Shinchan', '훈이랑 맹구 친해지길 바래~'),
('11', 'Shinchan', '철수는 내 귓바람을 너무 좋아한단 말이야 ㅎㅎ'),
('12', 'Naruto', '7반 때가 정말 좋았다니깐!'),
('13', 'Naruto', '신 전설의 3닌자 출격'),
('14', 'Naruto', '분신술은 너무 어려워;;'),
('15', 'Shinchan', '우리 엄마는 슈퍼우먼이에요~');

INSERT INTO Comments (comment_ID, user_ID, article_ID, contents) VALUES
('C01', 'Luffy', '01', '정말 멋진 사진이에요!'),
('C02', 'Shinchan', '02', '짱구네 가족 너무 귀엽다!'),
('C03', 'Naruto', '03', '라면 진짜 맛있겠네요!'),
('C04', 'Naruto', '04', '싸움 장면 최고였습니다!'),
('C05', 'Shinchan', '05', '우리 짱아 납치범 혼내줘야죠!'),
('C06', 'Luffy', '06', '멋진 대사네요!'),
('C07', 'Luffy', '07', '모두가 함께해서 좋아요!'),
('C08', 'Luffy', '08', '모험은 역시 동료와 함께!'),
('C09', 'Shinchan', '09', '떡잎마을 방범대 최고!'),
('C10', 'Shinchan', '10', '훈이와 맹구는 왜 싸운 거죠?'),
('C11', 'Shinchan', '11', '철수 귀엽다 ㅎㅎ'),
('C12', 'Naruto', '12', '제7반 그립네요~'),
('C13', 'Naruto', '13', '마다라는 진짜 강력했어요!'),
('C14', 'Naruto', '14', '분신술 어렵지만 재밌어요~'),
('C15', 'Shinchan', '15', '봉미선님은 정말 멋진 분!');

INSERT INTO Recomments (recomment_ID, user_ID, article_ID, contents) VALUES
('R01', 'Luffy', '01', '맞아요! 에이스는 정말 소중했죠!'),
('R02', 'Shinchan', '02', '짱아도 귀엽고 흰둥이도 귀엽다~'),
('R03', 'Naruto', '03', '라면은 언제 먹어도 맛있죠!'),
('R04', 'Naruto', '04', '싸움 정말 인상 깊었어요!'),
('R05', 'Shinchan', '05', '짱아 구출 작전 대박!'),
('R06', 'Luffy', '06', '항상 동료가 우선입니다!'),
('R07', 'Luffy', '07', '재회 장면 너무 감동적이었어요!'),
('R08', 'Luffy', '08', '우정은 진짜 힘이 됩니다!'),
('R09', 'Shinchan', '09', '방범대 모이면 항상 웃겨요 ㅋㅋ'),
('R10', 'Shinchan', '10', '훈이랑 맹구 화해했나요?'),
('R11', 'Shinchan', '11', '철수 귀여워 ㅎㅎ'),
('R12', 'Naruto', '12', '제7반 최고죠~'),
('R13', 'Naruto', '13', '마다라는 진짜 전설입니다!'),
('R14', 'Naruto', '14', '분신술은 연습이 답이에요!'),
('R15', 'Shinchan', '15', '봉미선님은 정말 대단하세요!');

INSERT INTO Likes (user_ID, article_ID, comment_ID) VALUES
('Luffy', '01', 'C01'),
('Shinchan', '02', 'C02'),
('Naruto', '03', 'C03'),
('Naruto', '04', 'C04'),
('Shinchan', '05', 'C05'),
('Luffy', '06', 'C06'),
('Luffy', '07', 'C07'),
('Luffy', '08', 'C08'),
('Shinchan', '09', 'C09'),
('Shinchan', '10', 'C10'),
('Shinchan', '11', 'C11'),
('Naruto', '12', 'C12'),
('Naruto', '13', 'C13'),
('Naruto', '14', 'C14'),
('Shinchan', '15', 'C15');