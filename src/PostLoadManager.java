import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PostLoadManager {

    // 사용자 닉네임 로드 메서드
    public static String loadNickname(String userId) {
        String nickname = "Unknown"; // 기본값
        try (Connection conn = DBManager.getConnection()) {
            String query = "SELECT nickname FROM Users WHERE User_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                nickname = rs.getString("nickname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading nickname: " + e.getMessage());
        }
        return nickname;
    }

    // 사용자 프로필 이미지 경로 로드 메서드
    public static String loadProfileImagePath(String userId) {
        String profileImagePath = "D:\\DBforGIT\\DBTeam6\\lib\\defaultProfile.png"; // 기본 프로필 이미지 경로
        try (Connection conn = DBManager.getConnection()) {
            String query = "SELECT profilImg FROM Users WHERE User_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                profileImagePath = rs.getString("profilImg");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading profile image: " + e.getMessage());
        }
        return profileImagePath;
    }

    // 특정 사용자가 작성한 모든 아티클 로드 메서드
    public static List<Article> loadUserArticles(String userId) {
        List<Article> articles = new ArrayList<>();
        try (Connection conn = DBManager.getConnection()) {
            String query = "SELECT contents, create_time FROM Articles WHERE user_ID = ? ORDER BY create_time DESC";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String content = rs.getString("contents");
                String createdAt = rs.getString("create_time");
                articles.add(new Article(content, createdAt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading articles: " + e.getMessage());
        }
        return articles;
    }

    // 아티클 데이터 클래스
    public static class Article {
        private final String content;
        private final String createdAt;

        public Article(String content, String createdAt) {
            this.content = content;
            this.createdAt = createdAt;
        }

        public String getContent() {
            return content;
        }

        public String getCreatedAt() {
            return createdAt;
        }
    }

    // 특정 사용자가 작성한 댓글 데이터 로드 메서드
    public static List<Comment> loadUserComments(String userId) {
        List<Comment> comments = new ArrayList<>();
        try (Connection conn = DBManager.getConnection()) {
            String query = "SELECT contents, create_time " +
                           "FROM Comments " +
                           "WHERE user_ID = ? " +
                           "ORDER BY create_time DESC";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String content = rs.getString("contents");
                String createTime = rs.getString("create_time");
                comments.add(new Comment(content, createTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading comments: " + e.getMessage());
        }
        return comments;
    }

    // 댓글 데이터 클래스
    public static class Comment {
        private final String content;
        private final String createTime;

        public Comment(String content, String createTime) {
            this.content = content;
            this.createTime = createTime;
        }

        public String getContent() {
            return content;
        }

        public String getCreateTime() {
            return createTime;
        }
    }
}
