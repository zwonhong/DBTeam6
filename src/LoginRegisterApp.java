import java.awt.*;
import java.sql.*;
import java.util.regex.Pattern;
import javax.swing.*;

public class LoginRegisterApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginRegisterApp().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Login and Register");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        // Initial screen setup
        LoginPanel loginPanel = new LoginPanel(frame);
        frame.add(loginPanel);

        frame.setVisible(true);
    }
}

// Login screen
class LoginPanel extends JPanel {
    public LoginPanel(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // 배경을 흰색으로 설정

        // Top logo panel
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        logoPanel.setBackground(Color.WHITE); // 배경을 흰색으로 설정

        // Load and resize the logo
        ImageIcon originalIcon = new ImageIcon("src/twitter_logo.jpeg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel logoLabel = new JLabel(scaledIcon);
        logoPanel.add(logoLabel);

        // Center input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE); // 배경을 흰색으로 설정
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblUsername = new JLabel("User ID:");
        JTextField txtUsername = new JTextField();
        txtUsername.setPreferredSize(new Dimension(300, 25));

        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(300, 25));

        JButton btnLogin = new JButton("Login");
        btnLogin.setPreferredSize(new Dimension(100, 30));
        JButton btnRegister = new JButton("Register");
        btnRegister.setPreferredSize(new Dimension(100, 30));

        // Adding components to layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(lblUsername, gbc);

        gbc.gridx = 1;
        inputPanel.add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(lblPassword, gbc);

        gbc.gridx = 1;
        inputPanel.add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(btnLogin, gbc);

        gbc.gridy = 3;
        inputPanel.add(btnRegister, gbc);

        // Add panels to the layout
        add(logoPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);

        // Login button event
        btnLogin.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = String.valueOf(txtPassword.getPassword());

            try (Connection conn = DBManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                         "SELECT * FROM Users WHERE User_ID = ? AND password = ?")) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Login failed! Please check your username or password.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
            }
        });

        // Register button event
        btnRegister.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.add(new RegisterPanel(frame));
            frame.revalidate();
            frame.repaint();
        });
    }
}

// Registration screen
class RegisterPanel extends JPanel {
    public RegisterPanel(JFrame frame) {
        setLayout(new GridLayout(9, 1, 10, 10)); // 닉네임 포함 필드 추가
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE); // 배경을 흰색으로 설정

        JLabel lblUsername = new JLabel("User ID:");
        JTextField txtUsername = new JTextField();

        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();

        JLabel lblNickname = new JLabel("Nickname:");
        JTextField txtNickname = new JTextField();

        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        JPasswordField txtConfirmPassword = new JPasswordField();

        JLabel lblBirth = new JLabel("Birth (YYYYMMDD):"); // YYYYMMDD 형식으로 수정
        JTextField txtBirth = new JTextField();

        JLabel lblPhone = new JLabel("Phone Number (01012345678):");
        JTextField txtPhone = new JTextField();

        JLabel lblGender = new JLabel("Gender (male/female/others):");
        JComboBox<String> cbGender = new JComboBox<>(new String[]{"Male", "Female", "Others"});

        JButton btnRegister = new JButton("Register");
        JButton btnBack = new JButton("Back to Login");

        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(lblConfirmPassword);
        add(txtConfirmPassword);
        add(lblNickname); // 닉네임 필드 추가
        add(txtNickname);
        add(lblBirth);
        add(txtBirth);
        add(lblPhone);
        add(txtPhone);
        add(lblGender);
        add(cbGender);
        add(btnRegister);
        add(btnBack);

        // Register 버튼 이벤트
        btnRegister.addActionListener(e -> {
            String username = txtUsername.getText();
            String password = String.valueOf(txtPassword.getPassword());
            String nickname = txtNickname.getText(); // 닉네임 값
            String confirmPassword = String.valueOf(txtConfirmPassword.getPassword());
            String birth = txtBirth.getText();
            String phone = txtPhone.getText();
            String gender = cbGender.getSelectedItem().toString();

            // 입력 값 검증
            if (username.isEmpty() || password.isEmpty() || nickname.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.");
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.");
                return;
            }

            // Birth 값 검증 (YYYYMMDD 형식)
            if (!Pattern.matches("\\d{4}\\d{2}\\d{2}", birth)) {
                JOptionPane.showMessageDialog(this, "Birth must be in YYYYMMDD format.");
                return;
            }

            // Phone number 검증 (10~11자리 숫자)
            if (!Pattern.matches("\\d{11}", phone)) {
                JOptionPane.showMessageDialog(this, "Phone number must be 11 digits without hyphens.");
                return;
            }

            try (Connection conn = DBManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO Users (User_ID, nickname, password, profilImg, wallPaper, introduction, birth, phoneNum, gender) " +
                                        "VALUES (?, ?, ?, NULL, NULL, NULL, ?, ?, ?)")) {
                stmt.setString(1, username);
                stmt.setString(2, nickname);
                stmt.setString(3, password); // 닉네임 저장
                stmt.setString(4, birth);
                stmt.setString(5, phone);
                stmt.setString(6, gender);

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Registration successful!");
                frame.getContentPane().removeAll();
                frame.add(new LoginPanel(frame));
                frame.revalidate();
                frame.repaint();
            } catch (SQLIntegrityConstraintViolationException ex) {
                JOptionPane.showMessageDialog(this, "User already exists.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
            }
        });

        // Back 버튼 이벤트
        btnBack.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.add(new LoginPanel(frame));
            frame.revalidate();
            frame.repaint();
        });
    }
}
