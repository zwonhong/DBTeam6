import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class UserProfileManager {

    public static String getNickname(String userId) {
        String nickname = null;
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
            JOptionPane.showMessageDialog(null, "Error fetching nickname: " + e.getMessage());
        }
        return nickname;
    }

    public static String getCreateTime(String userId) {
        String create_time = null;
        try (Connection conn = DBManager.getConnection()) {
            String query = "SELECT create_time FROM Users WHERE User_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                create_time = rs.getString("create_time");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching create_time: " + e.getMessage());
        }
        return create_time;
    }

    public static String getIntroduction(String userId) {
        String introduction = null;
        try (Connection conn = DBManager.getConnection()) {
            String query = "SELECT introduction FROM Users WHERE User_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                introduction = rs.getString("introduction");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching introduction: " + e.getMessage());
        }
        return introduction;
    }

    public static final String DEFAULT_PROFILE_IMAGE = "D:/DBforGIT/DBTeam6/lib/defaultProfile.png";

    public static String getImagePath(String userId) {
        String imagePath = null;
        try (Connection conn = DBManager.getConnection()) {
            String query = "SELECT profilImg FROM Users WHERE User_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                imagePath = rs.getString("profilImg");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching profile image path: " + e.getMessage());
        }
        return (imagePath != null && !imagePath.isEmpty()) ? imagePath : DEFAULT_PROFILE_IMAGE;
    }

    public static String getwallPaperPath(String userId) {
        String wallPaperPath = null;
        try (Connection conn = DBManager.getConnection()) {
            String query = "SELECT wallPaper FROM Users WHERE User_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                wallPaperPath = rs.getString("wallPaper");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching wallpaper path: " + e.getMessage());
        }
        return (wallPaperPath != null && !wallPaperPath.isEmpty()) ? wallPaperPath : null;
    }
    

    public static boolean saveIntroduction(String userId, String newIntroduction) {
        boolean isSaved = false;
        try (Connection conn = DBManager.getConnection()) {
            String query = "UPDATE Users SET introduction = ? WHERE User_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newIntroduction);
            pstmt.setString(2, userId);
    
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                isSaved = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving introduction: " + e.getMessage());
        }
        return isSaved;
    }

    public static boolean saveImagePath(String userId, String imagePath) {
        boolean isSaved = false;
        try (Connection conn = DBManager.getConnection()) {
            String query = "UPDATE Users SET profilImg = ? WHERE User_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, imagePath);
            pstmt.setString(2, userId);
    
            int rowsUpdated = pstmt.executeUpdate();
            isSaved = rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving profile image path: " + e.getMessage());
        }
        return isSaved;
    }

    public static boolean saveWallPaperPath(String userId, String wallPaperPath) {
        boolean isSaved = false;
        try (Connection conn = DBManager.getConnection()) {
            String query = "UPDATE Users SET wallPaper = ? WHERE User_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, wallPaperPath);
            pstmt.setString(2, userId);
    
            int rowsUpdated = pstmt.executeUpdate();
            isSaved = rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving wall paper path: " + e.getMessage());
        }
        return isSaved;
    }
}
    
