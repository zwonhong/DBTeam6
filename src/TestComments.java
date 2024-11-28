import javax.swing.*;
import java.awt.*;

public class TestComments {
    public JPanel getCommentsPanel() {
        JPanel commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS)); // 세로 정렬
        commentsPanel.setBackground(Color.BLACK);

        // 더미 데이터 생성
        String[][] dummyComments = {
            {"John Doe", "@user1", "This is comment 1", "5m ago"},
            {"Jane Smith", "@user2", "This is comment 2", "10m ago"},
            {"Mike Brown", "@user3", "This is comment 3", "15m ago"},
            {"Alice Johnson", "@user4", "This is comment 4", "20m ago"},
            {"Bob White", "@user5", "This is comment 5", "25m ago"}
        };

        // 댓글 생성
        for (String[] comment : dummyComments) {
            JPanel commentPanel = new JPanel();
            commentPanel.setLayout(null); // 자유 배치
            commentPanel.setBackground(new Color(30, 30, 30)); // 어두운 배경색
            commentPanel.setPreferredSize(new Dimension(800, 80)); // 패널 크기

            // 프로필 이미지
            ImageIcon profileImageIcon = new ImageIcon("C:\\Users\\Notebook\\Desktop\\profile.png"); // 이미지 경로
            Image profileImage = profileImageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JButton profileButton = new JButton() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g.create();

                    // 원형 클리핑
                    g2d.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, getWidth(), getHeight()));
                    g2d.drawImage(profileImage, 0, 0, getWidth(), getHeight(), null);
                    g2d.dispose();
                }
            };
            profileButton.setBounds(10, 10, 40, 40);
            profileButton.setBorder(BorderFactory.createEmptyBorder());
            profileButton.setContentAreaFilled(false);
            commentPanel.add(profileButton);

            // 유저 이름과 아이디
            JLabel userNameLabel = new JLabel(comment[0] + " " + comment[1]);
            userNameLabel.setBounds(60, 10, 300, 20);
            userNameLabel.setForeground(Color.LIGHT_GRAY);
            userNameLabel.setFont(new Font("Arial", Font.BOLD, 12));
            commentPanel.add(userNameLabel);

            // 댓글 내용
            JLabel commentLabel = new JLabel("<html>" + comment[2] + "</html>");
            commentLabel.setBounds(60, 30, 600, 20);
            commentLabel.setForeground(Color.WHITE);
            commentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            commentPanel.add(commentLabel);

            // 시간
            JLabel timeLabel = new JLabel(comment[3]);
            timeLabel.setBounds(60, 55, 100, 20);
            timeLabel.setForeground(Color.GRAY);
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            commentPanel.add(timeLabel);

            // 댓글 패널 간 간격 추가
            commentsPanel.add(commentPanel);
            commentsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 간격
        }

        return commentsPanel;
    }
}
