import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TestArticle {
    private String userID; // 사용자 ID

    public TestArticle(String userID) {
        this.userID = userID; // user_ID 저장
    }

    public JPanel getArticlePanel() {
        JPanel articlePanel = new JPanel();
        articlePanel.setLayout(new BoxLayout(articlePanel, BoxLayout.Y_AXIS)); // 세로로 정렬
        articlePanel.setBackground(Color.BLACK);

        // 닉네임과 프로필 사진 불러오기
        String nickname = PostLoadManager.loadNickname(userID);
        String profileImagePath = PostLoadManager.loadProfileImagePath(userID);

        // 아티클 불러오기
        List<PostLoadManager.Article> articles = PostLoadManager.loadUserArticles(userID);

        for (PostLoadManager.Article article : articles) {
            // 각 Article을 표시하는 JPanel 생성
            JPanel articleItemPanel = new JPanel();
            articleItemPanel.setLayout(null); // 자유 배치
            articleItemPanel.setBackground(new Color(30, 30, 30)); // 약간 어두운 회색 배경
            articleItemPanel.setPreferredSize(new Dimension(500, 120)); // 패널 크기 지정
            articleItemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 여백 설정

            // 프로필 사진 추가
            ImageIcon profileImageIcon = new ImageIcon(profileImagePath); // 동적으로 가져온 프로필 이미지 경로
            Image profileImage = profileImageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
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
            profileButton.setBounds(10, 10, 50, 50);
            profileButton.setBorder(BorderFactory.createEmptyBorder());
            profileButton.setContentAreaFilled(false); // 배경 제거
            articleItemPanel.add(profileButton);

            // 닉네임 및 ID 레이블
            JLabel usernameLabel = new JLabel(nickname); // 동적으로 가져온 닉네임
            usernameLabel.setForeground(Color.WHITE);
            usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            usernameLabel.setBounds(70, 10, 200, 20);
            articleItemPanel.add(usernameLabel);

            JLabel userIdLabel = new JLabel("@" + userID); // 내 User_ID
            userIdLabel.setForeground(Color.GRAY);
            userIdLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            userIdLabel.setBounds(70, 30, 200, 20);
            articleItemPanel.add(userIdLabel);

            // 트윗 내용
            JLabel tweetLabel = new JLabel("<html>" + article.getContent() + "</html>"); // 아티클 내용
            tweetLabel.setForeground(Color.WHITE);
            tweetLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            tweetLabel.setBounds(70, 50, 400, 40); // 위치 및 크기 설정
            articleItemPanel.add(tweetLabel);

            // 날짜
            JLabel dateLabel = new JLabel(article.getCreatedAt()); // 작성 날짜
            dateLabel.setForeground(Color.GRAY);
            dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            dateLabel.setBounds(70, 90, 180, 20);
            articleItemPanel.add(dateLabel);

            // 메인 패널에 추가
            articlePanel.add(articleItemPanel);
            articlePanel.add(Box.createRigidArea(new Dimension(0, 10))); // 간격 추가
        }

        return articlePanel;
    }
}
