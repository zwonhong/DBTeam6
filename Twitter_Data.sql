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
-- SET FOREIGN_KEY_CHECKS = 0;

-- DELETE FROM Recomments;
-- DELETE FROM Comments;
-- DELETE FROM Articles;
-- DELETE FROM Following;
-- DELETE FROM Users;

-- SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO Users (user_ID ,nickname, password, profilImg, wallPaper, introduction, birth, phoneNum, gender) 
VALUES ('john03','john_doe', 'password123', 'profile.jpg', 'wallpaper.jpg', 'Hello, I am John!', '1990-05-15', '010-1234-5678', 'Male');

INSERT INTO Users (user_ID, nickname, password) VALUES
('Luffy', 'gomugomu', 'password1'),
('Shinchan', 'buriburi','password2'),
('Naruto', 'chidori', 'password3');

INSERT INTO Following (following_ID, user_ID) VALUES
('Zoro', 'Luffy'),
('Nami', 'Luffy'),
('Usopp', 'Luffy'),
('Sanji', 'Luffy'),
('Robin', 'Luffy'),
('Chulsoo', 'Shinchan'),
('Yuri', 'Shinchan'),
('Hooni', 'Shinchan'),
('Maenggu', 'Shinchan'),
('Bong Miseon', 'Shinchan'),
('Shin Hyungman', 'Shinchan'),
('Sasuke', 'Naruto'),
('Hinata', 'Naruto'),
('Sakura', 'Naruto'),
('Kakashi', 'Naruto'),
('Madara', 'Naruto');

INSERT INTO Articles (articles_ID, user_ID, contents) VALUES
('01', 'Luffy', 'Ace rescue successful, taking a cool photo'),
('02', 'Shinchan', 'So happy when I\'m with dad, mom, Jjanga, and Hwandungie~'),
('03', 'Naruto', 'Iruka-sensei, thank you for the ramen!'),
('04', 'Naruto', 'Sorry, I caused a fuss to show you this... Naruto vs Sasuke, is this real? The battle between the world\'s strongest!'),
('05', 'Shinchan', 'I\'m going to punish the guy who kidnapped Jjanga!'),
('06', 'Luffy', 'You must become my crew!'),
('07', 'Luffy', 'Our pirate crew reunited after 2 years!'),
('08', 'Luffy', 'Nothing to fear when I\'m with my crew'),
('09', 'Shinchan', 'Ttak-Ip Village Defense Team, fire!'),
('10', 'Shinchan', 'I hope Hooni and Maenggu become friends~'),
('11', 'Shinchan', 'Chulsoo loves my ear whispers too much, haha'),
('12', 'Naruto', 'The 7th squad was really great!'),
('13', 'Naruto', 'The legendary 3 ninjas are back!'),
('14', 'Naruto', 'Shadow clones are really hard;;'),
('15', 'Shinchan', 'My mom is a superwoman~');

INSERT INTO Comments (comment_ID, user_ID, article_ID, contents) VALUES
('C01', 'Luffy', '01', 'Such a great photo!'),
('C02', 'Shinchan', '02', 'Shinchan\'s family is so cute!'),
('C03', 'Naruto', '03', 'The ramen looks really delicious!'),
('C04', 'Naruto', '04', 'The fight scene was the best!'),
('C05', 'Shinchan', '05', 'We need to punish the kidnapper of Jjanga!'),
('C06', 'Luffy', '06', 'Great line!'),
('C07', 'Luffy', '07', 'I\'m happy we\'re all together!'),
('C08', 'Luffy', '08', 'Adventure is always better with the crew!'),
('C09', 'Shinchan', '09', 'Ttak-Ip Village Defense Team is the best!'),
('C10', 'Shinchan', '10', 'Why did Hooni and Maenggu fight?'),
('C11', 'Shinchan', '11', 'Chulsoo is so cute, haha'),
('C12', 'Naruto', '12', 'I miss the 7th squad~'),
('C13', 'Naruto', '13', 'Madara was really powerful!'),
('C14', 'Naruto', '14', 'Shadow clones are hard but fun~'),
('C15', 'Shinchan', '15', 'Bong Miseon is really amazing!');

INSERT INTO Recomments (recomment_ID, user_ID, article_ID, contents) VALUES
('R01', 'Luffy', '01', 'Yes, Ace was really precious!'),
('R02', 'Shinchan', '02', 'Jjanga and Hwandungie are both so cute~'),
('R03', 'Naruto', '03', 'Ramen is always delicious!'),
('R04', 'Naruto', '04', 'The fight was really impressive!'),
('R05', 'Shinchan', '05', 'The Jjanga rescue operation was awesome!'),
('R06', 'Luffy', '06', 'My crew always comes first!'),
('R07', 'Luffy', '07', 'The reunion scene was so emotional!'),
('R08', 'Luffy', '08', 'Friendship really is powerful!'),
('R09', 'Shinchan', '09', 'The Defense Team is always funny when they get together, haha'),
('R10', 'Shinchan', '10', 'Did Hooni and Maenggu make up?'),
('R11', 'Shinchan', '11', 'Chulsoo is so cute, haha'),
('R12', 'Naruto', '12', 'The 7th squad is the best~'),
('R13', 'Naruto', '13', 'Madara is truly a legend!'),
('R14', 'Naruto', '14', 'Shadow clones are all about practice!'),
('R15', 'Shinchan', '15', 'Bong Miseon is really amazing!');

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

INSERT INTO users (User_ID, nickname, password)
VALUES
('1', 'Zehn', '12345'),
('2', 'John', '12345678');

INSERT INTO articles (Articles_ID, user_ID, contents, create_time) VALUES
('A001', '1', 'This is the first article about database design.', '2024-11-29 10:00:00'),
('A002', '1', 'An insightful post on improving SQL queries.', '2024-11-29 10:15:00'),
('A003', '1', 'Exploring the basics of foreign keys in relational databases.', '2024-11-29 10:30:00'),
('A004', '1', 'A comprehensive guide to primary keys.', '2024-11-29 10:45:00'),
('A005', '1', 'How to optimize table indexing for performance.', '2024-11-29 11:00:00'),
('A006', '2', 'Understanding the role of JOIN operations in SQL.', '2024-11-29 11:15:00'),
('A007', '2', '10 tips for writing efficient database queries.', '2024-11-29 11:30:00'),
('A008', '2', 'Explaining the difference between UNION and UNION ALL.', '2024-11-29 11:45:00'),
('A009', '2', 'An overview of normalization techniques.', '2024-11-29 12:00:00'),
('A010', '2', 'When to use indexes in large datasets.', '2024-11-29 12:15:00');

INSERT INTO comments (Comment_ID, user_ID, article_ID, create_time, contents) VALUES
('C001', '1', 'A001', '2024-11-29 13:00:00', 'Great article! Very informative.'),
('C002', '1', 'A002', '2024-11-29 13:15:00', 'Thanks for sharing these tips.'),
('C003', '1', 'A003', '2024-11-29 13:30:00', 'Could you elaborate on foreign keys?'),
('C004', '1', 'A004', '2024-11-29 13:45:00', 'This guide is super helpful.'),
('C005', '1', 'A005', '2024-11-29 14:00:00', 'Indexing made easy! Thanks!'),
('C006', '1', 'A006', '2024-11-29 14:15:00', 'JOINs are tricky but necessary.'),
('C007', '1', 'A007', '2024-11-29 14:30:00', 'Awesome tips on query efficiency.'),
('C008', '2', 'A001', '2024-11-29 14:45:00', 'This was exactly what I was looking for.'),
('C009', '2', 'A002', '2024-11-29 15:00:00', 'Can you provide more examples?'),
('C010', '2', 'A003', '2024-11-29 15:15:00', 'Good overview of foreign keys.'),
('C011', '2', 'A004', '2024-11-29 15:30:00', 'Primary keys are simple yet essential.'),
('C012', '2', 'A005', '2024-11-29 15:45:00', 'Clear explanation of indexing.'),
('C013', '2', 'A008', '2024-11-29 16:00:00', 'UNION ALL vs UNION is now clear.'),
('C014', '2', 'A009', '2024-11-29 16:15:00', 'Normalization techniques are crucial.');

INSERT INTO likes (user_ID, article_ID, comment_ID) VALUES
('1', 'A001', NULL),
('1', NULL, 'C001'),
('1', 'A002', NULL),
('2', NULL, 'C008'),
('2', 'A006', NULL),
('2', NULL, 'C014'),
('1', 'A006', NULL),
('2', 'A003', NULL);

INSERT INTO following (Following_ID, user_ID)
VALUES
('F001', '1'),
('F002', '2');
