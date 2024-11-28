import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class UserSettingsManager {

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

    public static String getGender(String userId) {
        String gender = null;
        try (Connection conn = DBManager.getConnection()) {
            String query = "SELECT gender FROM Users WHERE User_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                gender = rs.getString("gender");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching gender: " + e.getMessage());
        }
        return gender;
    }
    

    public static String getPhoneNum(String userId) {
        String phoneNum = null; // 전화번호를 저장할 변수
        try (Connection conn = DBManager.getConnection()) {
            // SQL 쿼리 작성
            String query = "SELECT phoneNum FROM Users WHERE User_ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId); // ?에 userId 값 설정
    
            // 쿼리 실행 및 결과 처리
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                phoneNum = rs.getString("phoneNum"); // phoneNum 컬럼에서 값 가져오기
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching phone number: " + e.getMessage());
        }
        return phoneNum;
    }
    
    public static boolean updateUserSettings(int userId, String newNickname, String currentPassword, String newPassword, String phone, String gender) {
        if (newPassword != null && newPassword.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.");
            return false;
        }

        if (gender == null) {
            JOptionPane.showMessageDialog(null, "Please select a gender.");
            return false;
        }
        
    
        if (phone != null && phone.length() != 13) {
            JOptionPane.showMessageDialog(null, "Phone number must be 13 characters long.");
            return false;
        }
    
        try (Connection conn = DBManager.getConnection()) {
            // 현재 비밀번호 확인
            String passwordQuery = "SELECT password FROM Users WHERE User_ID = ?";
            PreparedStatement passwordStmt = conn.prepareStatement(passwordQuery);
            passwordStmt.setInt(1, userId);
            ResultSet rs = passwordStmt.executeQuery();
            if (rs.next() && !rs.getString("password").equals(currentPassword)) {
                JOptionPane.showMessageDialog(null, "Current password is incorrect.");
                return false;
            }
    
            // 업데이트 쿼리 생성
            String updateQuery = "UPDATE Users SET "
                    + (newNickname != null ? "nickname = ?, " : "")
                    + (phone != null ? "phoneNum = ?, " : "")
                    + (newPassword != null ? "password = ?, " : "")
                    + (gender != null ? "gender = ? " : "")
                    + "WHERE User_ID = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
    
            // 파라미터 설정
            int paramIndex = 1;
            if (newNickname != null) updateStmt.setString(paramIndex++, newNickname);
            if (phone != null) updateStmt.setString(paramIndex++, phone);
            if (newPassword != null) updateStmt.setString(paramIndex++, newPassword);
            if (gender != null) updateStmt.setString(paramIndex++, gender);
            updateStmt.setInt(paramIndex, userId);
    
            // 쿼리 실행
            int rowsUpdated = updateStmt.executeUpdate();
            return rowsUpdated > 0; // 업데이트 성공 여부 반환
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating user settings: " + e.getMessage());
        }
        return false;
    }
}
    
