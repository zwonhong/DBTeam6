import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TestLikes {
    private String userId;

    public TestLikes(String userId) {
        this.userId = userId;
    }

    public JPanel getLikesPanel() {
        JPanel likesPanel = new JPanel();
        likesPanel.setLayout(new BoxLayout(likesPanel, BoxLayout.Y_AXIS));
        likesPanel.setBackground(Color.BLACK);

        // Load likes data
        List<LikeLoadManager.Like> likes = LikeLoadManager.loadLikesByUser(userId);

        for (LikeLoadManager.Like like : likes) {
            if (like.getArticleId() != null) {
                // Load article data
                LikeLoadManager.Article article = LikeLoadManager.loadArticleById(like.getArticleId());
                if (article != null) {
                    JPanel articlePanel = createArticlePanel(article);
                    likesPanel.add(articlePanel);
                    likesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                }
            } else if (like.getCommentId() != null) {
                // Load comment data
                LikeLoadManager.Comment comment = LikeLoadManager.loadCommentById(like.getCommentId());
                if (comment != null) {
                    JPanel commentPanel = createCommentPanel(comment);
                    likesPanel.add(commentPanel);
                    likesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                }
            }
        }

        return likesPanel;
    }

    private JPanel createArticlePanel(LikeLoadManager.Article article) {
        String nickname = LikeLoadManager.loadNickname(article.getUserId());
        String profileImagePath = LikeLoadManager.loadProfileImagePath(article.getUserId());

        JPanel articlePanel = new JPanel();
        articlePanel.setLayout(null);
        articlePanel.setBackground(new Color(30, 30, 30));
        articlePanel.setPreferredSize(new Dimension(500, 120));

        ImageIcon profileImageIcon = new ImageIcon(profileImagePath);
        Image profileImage = profileImageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton profileButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, getWidth(), getHeight()));
                g2d.drawImage(profileImage, 0, 0, getWidth(), getHeight(), null);
                g2d.dispose();
            }
        };
        profileButton.setBounds(10, 10, 50, 50);
        profileButton.setBorder(BorderFactory.createEmptyBorder());
        profileButton.setContentAreaFilled(false);
        articlePanel.add(profileButton);

        JLabel usernameLabel = new JLabel(nickname);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(70, 10, 200, 20);
        articlePanel.add(usernameLabel);

        JLabel articleContentLabel = new JLabel("<html>" + article.getContent() + "</html>");
        articleContentLabel.setForeground(Color.WHITE);
        articleContentLabel.setBounds(70, 50, 400, 40);
        articlePanel.add(articleContentLabel);

        JLabel dateLabel = new JLabel(article.getCreatedAt());
        dateLabel.setForeground(Color.GRAY);
        dateLabel.setBounds(70, 90, 180, 20);
        articlePanel.add(dateLabel);

        return articlePanel;
    }

    private JPanel createCommentPanel(LikeLoadManager.Comment comment) {
        String nickname = LikeLoadManager.loadNickname(comment.getUserId());
        String profileImagePath = LikeLoadManager.loadProfileImagePath(comment.getUserId());

        JPanel commentPanel = new JPanel();
        commentPanel.setLayout(null);
        commentPanel.setBackground(new Color(30, 30, 30));
        commentPanel.setPreferredSize(new Dimension(800, 80));

        ImageIcon profileImageIcon = new ImageIcon(profileImagePath);
        Image profileImage = profileImageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JButton profileButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, getWidth(), getHeight()));
                g2d.drawImage(profileImage, 0, 0, getWidth(), getHeight(), null);
                g2d.dispose();
            }
        };
        profileButton.setBounds(10, 10, 40, 40);
        profileButton.setBorder(BorderFactory.createEmptyBorder());
        profileButton.setContentAreaFilled(false);
        commentPanel.add(profileButton);

        JLabel userNameLabel = new JLabel(nickname);
        userNameLabel.setBounds(60, 10, 300, 20);
        userNameLabel.setForeground(Color.LIGHT_GRAY);
        commentPanel.add(userNameLabel);

        JLabel commentLabel = new JLabel("<html>" + comment.getContent() + "</html>");
        commentLabel.setBounds(60, 30, 600, 20);
        commentLabel.setForeground(Color.WHITE);
        commentPanel.add(commentLabel);

        JLabel timeLabel = new JLabel(comment.getCreateTime());
        timeLabel.setBounds(60, 55, 180, 20);
        timeLabel.setForeground(Color.GRAY);
        commentPanel.add(timeLabel);

        return commentPanel;
    }
}
