package twitter;

import java.sql.*;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class DatabaseService {
    private Connection connection;

    // Constructor: 데이터베이스 연결 초기화
    public DatabaseService() {
        initDatabaseConnection();
    }

    // 데이터베이스 연결 설정
    private void initDatabaseConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/university";
            String username = "root";
            String password = "1234";
			System.out.println("success");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 // 게시글 상세 정보 가져오기
    public ArticleDetails loadArticleDetails(String articleId) {
        ArticleDetails details = null;
        try {
            String query = "SELECT A.contents, A.create_time, U.user_ID, U.nickname,U.profilImg " +
                           "FROM Articles A " +
                           "JOIN Users U ON A.user_ID = U.user_ID " +
                           "WHERE A.articles_ID = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, articleId); 
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String content = resultSet.getString("contents");
                String creationTime = resultSet.getString("create_time");
                String userID = resultSet.getString("user_ID");
                String nickname = resultSet.getString("nickname");
                String profileImg = resultSet.getString("profilImg");
                details = new ArticleDetails(content, creationTime,userID,nickname, profileImg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return details;
    }
 // 게시글 상세 정보 저장 클래스
    class ArticleDetails {
        private String content;
        private String creationTime;
        private String nickname;
        private String profileImg;
        private String userID;

        public ArticleDetails(String content, String creationTime, String userID,String nickname, String profileImg) {
            this.content = content;
            this.creationTime = creationTime;
            this.nickname = nickname;
            this.profileImg = profileImg;
            this.userID = userID;

        }

        public String getContent() {
            return content;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public String getNickname() {
            return nickname;
        }

        public String getProfileImg() {
            return profileImg;
        }
        public String getuserID() {
            return userID;
        }
    }
    
    // 게시물 좋아요 수 가져오기
    public int getLikesForArticle(String articleId) {
        int likeCount = 0;
        try {
            String query = "SELECT COUNT(*) AS like_count FROM Likes WHERE article_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, articleId); 
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                likeCount = resultSet.getInt("like_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likeCount;
    }
    
    
    //게시물 좋아요 추가.
    public void addLikeToArticle(String userId, String articleId) {
        try {
            String query = "INSERT INTO Likes (user_ID, article_ID) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId); 
            preparedStatement.setString(2, articleId); 
            preparedStatement.executeUpdate();
            System.out.println("Like added to article: " + articleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //게시물 좋아요 취소
    public void removeLikeFromArticle(String userId, String articleId) {
        try {
            String query = "DELETE FROM Likes WHERE user_ID = ? AND article_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId); 
            preparedStatement.setString(2, articleId); 
            preparedStatement.executeUpdate();
            System.out.println("Like removed from article: " + articleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    // 댓글 가져오기
    public List<CommentDetails> fetchComments(String articleId) {
        List<CommentDetails> comments = new ArrayList<>();
        try {
            String query = "SELECT C.Comment_ID, C.contents, C.create_time, U.user_ID, U.nickname, U.profilImg " +
                           "FROM Comments C " +
                           "JOIN Users U ON C.user_ID = U.user_ID " +
                           "WHERE C.article_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, articleId); 
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String commentID = resultSet.getString("Comment_ID");
                String content = resultSet.getString("contents");
                String creationTime = resultSet.getString("create_time");
                String nickname = resultSet.getString("nickname");
                String userID=resultSet.getString("user_ID");
                String profileImg = resultSet.getString("profilImg");

                comments.add(new CommentDetails(commentID,content, creationTime, userID,nickname, profileImg));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }
    
    // 댓글 상세 정보 저장 클래스
    class CommentDetails {
        private String content;
        private String creationTime;
        private String nickname;
        private String profileImg;
        private String userID;
        private String commentID;

        public CommentDetails(String comnentID,String content, String creationTime, String userID, String nickname, String profileImg) {
            this.content = content;
            this.creationTime = creationTime;
            this.nickname = nickname;
            this.profileImg = profileImg;
            this.userID = userID;
            this.commentID = comnentID;


        }

        public String getContent() {
            return content;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public String getNickname() {
            return nickname;
        }

        public String getProfileImg() {
            return profileImg;
        }
        
        public String getuserID() {
            return userID;
        }
        public String getcommentID() {
            return commentID;
        }
    }

    
    // 댓글 저장
    public void saveComment(String userId, String articleId, String comment) {
        try {
        	
            String commentID = generateCommentID(); // UUID 생성
            String query = "INSERT INTO Comments (Comment_ID,user_ID, article_ID, contents) VALUES (?,?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, commentID);
            preparedStatement.setString(2, userId); 
            preparedStatement.setString(3, articleId); 
            preparedStatement.setString(4, comment);
            preparedStatement.executeUpdate();
            System.out.println("Comment saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    // 댓글 좋아요 수 가져오기
    public int getLikesForComment(String commentId) {
        int likeCount = 0;
        try {
            String query = "SELECT COUNT(*) AS like_count FROM Likes WHERE comment_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, commentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                likeCount = resultSet.getInt("like_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likeCount;
    }
    
    //댓글에 좋아요 추가
    public void addLikeToComment(String userId, String commentId) {
        try {
            String query = "INSERT INTO Likes (user_ID, comment_ID) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, commentId);
            preparedStatement.executeUpdate();
            System.out.println("Like added to comment: " + commentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //댓글 좋아요 취소
    public void removeLikeFromComment(String userId, String commentId) {
        try {
            String query = "DELETE FROM Likes WHERE user_ID = ? AND comment_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, commentId);
            preparedStatement.executeUpdate();
            System.out.println("Like removed from comment: " + commentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 사용자 프로필 가져오기
    public UserProfile getUserProfile(String userId) {
        UserProfile profile = null;
        try {
            String query = "SELECT nickname, profilImg FROM Users WHERE user_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nickname = resultSet.getString("nickname");
                String profileImg = resultSet.getString("profilImg");
                profile = new UserProfile(nickname, profileImg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }
    

    // 사용자 프로필 정보를 저장하는 클래스
    class UserProfile {
        private String nickname;
        private String profileImg;

        public UserProfile(String nickname, String profileImg) {
            this.nickname = nickname;
            this.profileImg = profileImg;
        }

        public String getNickname() {
            return nickname;
        }

        public String getProfileImg() {
            return profileImg;
        }
    }
    
    public String generateCommentID() {
        return UUID.randomUUID().toString(); // UUID를 문자열로 생성
    }
    
    // Close the database connection
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
