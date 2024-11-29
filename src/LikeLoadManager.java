import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LikeLoadManager {

    // Inner classes representing data
    public static class Like {
        private String userId;
        private String articleId; // Nullable
        private String commentId; // Nullable

        public Like(String userId, String articleId, String commentId) {
            this.userId = userId;
            this.articleId = articleId;
            this.commentId = commentId;
        }

        public String getUserId() {
            return userId;
        }

        public String getArticleId() {
            return articleId;
        }

        public String getCommentId() {
            return commentId;
        }
    }

    public static class Article {
        private String content;
        private String createdAt;
        private String userId;

        public Article(String content, String createdAt, String userId) {
            this.content = content;
            this.createdAt = createdAt;
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public String getUserId() {
            return userId;
        }
    }

    public static class Comment {
        private String content;
        private String createTime;
        private String userId;

        public Comment(String content, String createTime, String userId) {
            this.content = content;
            this.createTime = createTime;
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public String getUserId() {
            return userId;
        }
    }

    // Load Likes for a specific user
    public static List<Like> loadLikesByUser(String userId) {
        List<Like> likes = new ArrayList<>();
        String query = "SELECT article_ID, comment_ID FROM Likes WHERE user_ID = ?";

        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String articleId = resultSet.getString("article_ID");
                    String commentId = resultSet.getString("comment_ID");
                    likes.add(new Like(userId, articleId, commentId));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception properly in production
        }
        return likes;
    }

    // Load Article by article_ID
    public static Article loadArticleById(String articleId) {
        String query = "SELECT contents, create_time, user_ID FROM Articles WHERE Articles_ID = ?";
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, articleId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String content = resultSet.getString("contents");
                    String createdAt = resultSet.getString("create_time");
                    String userId = resultSet.getString("user_ID");
                    return new Article(content, createdAt, userId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception properly in production
        }
        return null;
    }

    // Load Comment by comment_ID
    public static Comment loadCommentById(String commentId) {
        String query = "SELECT contents, create_time, user_ID FROM comments WHERE Comment_ID = ?";
        try (Connection connection = DBManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, commentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String content = resultSet.getString("contents");
                    String createTime = resultSet.getString("create_time");
                    String userId = resultSet.getString("user_ID");
                    return new Comment(content, createTime, userId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception properly in production
        }
        return null;
    }

        // Load nickname for a user_ID
        public static String loadNickname(String userId) {
            String query = "SELECT nickname FROM Users WHERE user_ID = ?";
            try (Connection connection = DBManager.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    
                preparedStatement.setString(1, userId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("nickname");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(); // Handle exception properly in production
            }
            return "Unknown"; // Default nickname if not found
        }
    
        // Load profile image path for a user_ID
        public static String loadProfileImagePath(String userId) {
            String query = "SELECT profilImg FROM Users WHERE user_ID = ?";
            try (Connection connection = DBManager.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    
                preparedStatement.setString(1, userId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("profilImg");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(); // Handle exception properly in production
            }
            return "defaultProfile.png"; // Default profile image path if not found
        }
    }
    