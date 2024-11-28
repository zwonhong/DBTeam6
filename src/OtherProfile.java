import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class OtherProfile {

    // 프로필 화면 반환 메서드
    public JPanel getProfilePanel() {
        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(Color.BLACK); // 배경색
        profilePanel.setLayout(null); // 자유 배치
        profilePanel.setBounds(0, 0, 850, 700); // 오른쪽 패널 크기 설정 (고정값)

        // 프로필 이미지 파일 경로
        final String profileImagePath = "D:\\DBTermProject\\lib\\defaultProfile.png";
        final String backgroundImagePath = null; // 상단 배경 이미지 초기값은 널값으로 설정 (회색 배경)

        // 프로필 이미지 (원형 패널로 생성)
        JPanel profileImagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // 이미지 로드
                BufferedImage profileImage = null;
                try {
                    profileImage = ImageIO.read(new File(profileImagePath)); // 이미지 경로
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (profileImage != null) {
                    // 패널 크기와 원형 크기 결정
                    int diameter = Math.min(getWidth(), getHeight()); // 원형 크기
                    BufferedImage circularImage = createCircularImage(profileImage, diameter);

                    // 원형 이미지 그리기
                    g2.drawImage(circularImage, 0, 0, diameter, diameter, null);
                }

                g2.dispose();
            }
        };

        // 패널을 원형으로 설정
        profileImagePanel.setBounds(50, 125, 150, 150); // 위치 설정
        profileImagePanel.setOpaque(false); // 배경 투명화
        profilePanel.add(profileImagePanel); // 프로필 이미지를 먼저 추가

        // 상단 회색 배경
        JPanel topGrayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImagePath != null) {
                    try {
                        // 배경 이미지 로드 및 그리기
                        BufferedImage backgroundImage = ImageIO.read(new File(backgroundImagePath));
                        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // 기본 배경색
                    g.setColor(Color.GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        topGrayPanel.setBounds(0, 0, 850, 200); // 상단 영역
        profilePanel.add(topGrayPanel, Integer.valueOf(0)); // 배경을 항상 뒤에 추가

        // 사용자 이름 라벨
        JLabel NicknameLabel = new JLabel("Others");
        NicknameLabel.setForeground(Color.WHITE);
        NicknameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        NicknameLabel.setBounds(220, 210, 200, 30); // 이름 위치
        profilePanel.add(NicknameLabel);

        // 사용자 아이디 라벨
        JLabel UsernameLabel = new JLabel("@userName");
        UsernameLabel.setForeground(Color.GRAY);
        UsernameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        UsernameLabel.setBounds(230, 240, 200, 20); // 이름 위치
        profilePanel.add(UsernameLabel);

        // 상태 표시 라벨
        JLabel nowLabel = new JLabel("Hello, World!");
        nowLabel.setForeground(Color.GRAY);
        nowLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nowLabel.setBounds(80, 285, 200, 20); // 위치 설정
        profilePanel.add(nowLabel);

        // 가입일 라벨
        JLabel joinDateLabel = new JLabel("Subscription: day");
        joinDateLabel.setForeground(Color.GRAY);
        joinDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        joinDateLabel.setBounds(80, 305, 200, 20); // 가입일 위치
        profilePanel.add(joinDateLabel);

        JButton likeButton = new JButton("Follow");
            likeButton.setBounds(720, 210, 80, 30);
            likeButton.setForeground(Color.WHITE);
            likeButton.setBackground(Color.PINK);
            likeButton.setFont(new Font("Arial", Font.PLAIN, 12));
            likeButton.setBorderPainted(false);
            likeButton.setFocusPainted(false);
            likeButton.addActionListener(e -> {
                if (likeButton.getText().equals("Follow")) {
                    likeButton.setText("unFollow");
                    likeButton.setForeground(Color.RED);
                } else {
                    likeButton.setText("Follow");
                    likeButton.setForeground(Color.WHITE);
                }
            });
            profilePanel.add(likeButton);

        // 토글 버튼 패널
        JPanel togglePanel = new JPanel();
        togglePanel.setBounds(0, 340, 850, 50); // 토글 버튼 영역
        togglePanel.setLayout(new GridLayout(1, 3, 1, 0)); // 1행 3열, 버튼 간 간격 10px
        togglePanel.setBackground(Color.BLACK);

        // 토글 버튼 1
        JButton toggle1 = new JButton("Articles");
        toggle1.setBackground(Color.BLACK);
        toggle1.setForeground(Color.WHITE);
        toggle1.setFocusPainted(false);
        togglePanel.add(toggle1);

        // 토글 버튼 2
        JButton toggle2 = new JButton("Comments");
        toggle2.setBackground(Color.BLACK);
        toggle2.setForeground(Color.WHITE);
        toggle2.setFocusPainted(false);
        togglePanel.add(toggle2);

        // 토글 버튼 3
        JButton toggle3 = new JButton("Likes");
        toggle3.setBackground(Color.BLACK);
        toggle3.setForeground(Color.WHITE);
        toggle3.setFocusPainted(false);
        togglePanel.add(toggle3);

        profilePanel.add(togglePanel);

        // CardLayout으로 전환 패널 설정
        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.setBounds(0, 390, 850, 300);

        // TestArticle, TestComments, TestLikes에서 패널 가져오기
        TestArticle testArticle = new TestArticle();
        TestComments testComments = new TestComments();
        TestLikes testLikes = new TestLikes();

        JScrollPane articleScrollPane = new JScrollPane(testArticle.getArticlePanel());
        JScrollPane commentsScrollPane = new JScrollPane(testComments.getCommentsPanel());
        JScrollPane likesScrollPane = new JScrollPane(testLikes.getLikesPanel());

        // CardLayout에 스크롤 가능한 패널 추가
        cardPanel.add(articleScrollPane, "Articles");
        cardPanel.add(commentsScrollPane, "Comments");
        cardPanel.add(likesScrollPane, "Likes");

        profilePanel.add(cardPanel);

        // 토글 버튼 클릭 이벤트 추가
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        toggle1.addActionListener(e -> cl.show(cardPanel, "Articles"));
        toggle2.addActionListener(e -> cl.show(cardPanel, "Comments"));
        toggle3.addActionListener(e -> cl.show(cardPanel, "Likes"));

        return profilePanel;
    }

    // 원형 이미지를 생성하는 메서드
    private BufferedImage createCircularImage(BufferedImage image, int diameter) {
        // 원형 BufferedImage 생성
        BufferedImage circularImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circularImage.createGraphics();

        // 안티앨리어싱 설정
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 원형 클리핑 설정
        Shape clip = new Ellipse2D.Float(0, 0, diameter, diameter);
        g2.setClip(clip);

        // 이미지를 원형 클리핑에 맞게 그림
        g2.drawImage(image, 0, 0, diameter, diameter, null);
        g2.dispose();

        return circularImage;
    }
}
