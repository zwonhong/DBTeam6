import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TestComments {
    private String userId; // 특정 사용자 ID

    public TestComments(String userId) {
        this.userId = userId; // 사용자 ID 저장
    }

    public JPanel getCommentsPanel() {
        JPanel commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS)); // 세로 정렬
        commentsPanel.setBackground(Color.BLACK);

        // 특정 사용자의 닉네임과 프로필 이미지 로드
        String nickname = PostLoadManager.loadNickname(userId);
        String profileImagePath = PostLoadManager.loadProfileImagePath(userId);

        // 특정 사용자가 작성한 댓글 데이터 로드
        List<PostLoadManager.Comment> comments = PostLoadManager.loadUserComments(userId);

        for (PostLoadManager.Comment comment : comments) {
            JPanel commentPanel = new JPanel();
            commentPanel.setLayout(null); // 자유 배치
            commentPanel.setBackground(new Color(30, 30, 30)); // 어두운 배경색
            commentPanel.setPreferredSize(new Dimension(800, 80)); // 패널 크기

            // 프로필 이미지
            ImageIcon profileImageIcon = new ImageIcon(profileImagePath); // 프로필 이미지 경로
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

            // 유저 닉네임과 ID
            JLabel userNameLabel = new JLabel(nickname + " @" + userId);
            userNameLabel.setBounds(60, 10, 300, 20);
            userNameLabel.setForeground(Color.LIGHT_GRAY);
            userNameLabel.setFont(new Font("Arial", Font.BOLD, 12));
            commentPanel.add(userNameLabel);

            // 댓글 내용
            JLabel commentLabel = new JLabel("<html>" + comment.getContent() + "</html>");
            commentLabel.setBounds(60, 30, 600, 20);
            commentLabel.setForeground(Color.WHITE);
            commentLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            commentPanel.add(commentLabel);

            // 시간
            JLabel timeLabel = new JLabel(comment.getCreateTime());
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
