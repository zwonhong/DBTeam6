import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Profile {
    private String userID; // userID 필드 추가
    private JLabel NicknameLabel;
    private JLabel joinDateLabel;
    private JLabel introductionLabel;
    private final String[] profileImagePath = { UserProfileManager.getImagePath(userID) }; // 데이터베이스에서 프로필 이미지 경로 가져오기
    private final String[] backgroundImagePath = {UserProfileManager.getwallPaperPath(userID)};
    private JPanel profileImagePanel;
    private JPanel topGrayPanel;

    public Profile(String userID) {
        this.userID = userID; // user_ID 저장
        getProfilePanel();
        loadUserData();
    }

    // 프로필 화면 반환 메서드
    public JPanel getProfilePanel() {
        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(Color.BLACK); // 배경색
        profilePanel.setLayout(null); // 자유 배치
        profilePanel.setBounds(0, 0, 850, 700); // 오른쪽 패널 크기 설정 (고정값)

        // 프로필 이미지 (원형 패널로 생성)
        profileImagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // 이미지 로드
                BufferedImage profileImage = null;
                try {
                    profileImage = ImageIO.read(new File(profileImagePath[0])); // 이미지 경로
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

        // **클릭 이벤트 추가 (이미지 변경)**
        profileImagePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose Profile Image");
                int result = fileChooser.showOpenDialog(null);
        
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String newImagePath = selectedFile.getAbsolutePath();
                    
                    // 데이터베이스에 경로 저장
                    if (UserProfileManager.saveImagePath(userID, newImagePath)) {
                        profileImagePath[0] = newImagePath; // 경로 저장
                        profileImagePanel.repaint(); // 변경된 이미지 다시 그리기
                        JOptionPane.showMessageDialog(null, "Profile image updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update profile image path.");
                    }
                }
            }
        });  

        // 상단 회색 배경 (이미지 변경 가능)
        topGrayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImagePath[0] != null) {
                    try {
                        // 배경 이미지 로드 및 그리기
                        BufferedImage backgroundImage = ImageIO.read(new File(backgroundImagePath[0]));
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

        // **배경 이미지 클릭 이벤트 추가**
        topGrayPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose Profile Image");
                int result = fileChooser.showOpenDialog(null);
        
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String newWallPaperPath = selectedFile.getAbsolutePath();
                    
                    // 데이터베이스에 경로 저장
                    if (UserProfileManager.saveWallPaperPath(userID, newWallPaperPath)) {
                        backgroundImagePath[0] = newWallPaperPath; // 경로 저장
                        topGrayPanel.repaint(); // 변경된 이미지 다시 그리기
                        JOptionPane.showMessageDialog(null, "Wallpaper image updated successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update Wallpaper image path.");
                    }
                }
            }
        });
        

        // 사용자 이름 라벨
        NicknameLabel = new JLabel();
        NicknameLabel.setForeground(Color.WHITE);
        NicknameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        NicknameLabel.setBounds(220, 210, 200, 30); // 이름 위치
        profilePanel.add(NicknameLabel);

        // 사용자 아이디 라벨
        JLabel UsernameLabel = new JLabel("@" + userID);
        UsernameLabel.setForeground(Color.GRAY);
        UsernameLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        UsernameLabel.setBounds(230, 240, 200, 20); // 이름 위치
        profilePanel.add(UsernameLabel);

        // 상태 표시 라벨
        introductionLabel = new JLabel("Put your introduction");
        introductionLabel.setForeground(Color.GRAY);
        introductionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        introductionLabel.setBounds(80, 285, 200, 20); // 위치 설정
        profilePanel.add(introductionLabel);

        // now 텍스트를 변경할 JTextField (초기에는 숨김)
        JTextField introductionTextField = new JTextField("Put your introduction");
        introductionTextField.setBounds(80, 285, 200, 20); // 라벨과 동일한 위치
        introductionTextField.setVisible(false); // 처음에는 보이지 않게 설정
        
        // 입력칸 디폴트 (흰 바탕에 검은 텍스트에서 반대로 설정)
        introductionTextField.setBackground(Color.BLACK); // 배경색 검정
        introductionTextField.setForeground(Color.WHITE); // 텍스트 색상 흰색
        introductionTextField.setCaretColor(Color.WHITE); // 커서 색상 흰색
        introductionTextField.setBorder(BorderFactory.createEmptyBorder()); // 테두리 제거

        profilePanel.add(introductionTextField);

        // **nowLabel 클릭 이벤트 (JTextField로 전환)**
        introductionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                introductionLabel.setVisible(false); // 라벨 숨김
                introductionTextField.setVisible(true); // 텍스트필드 표시
                introductionTextField.requestFocus(); // 텍스트필드에 포커스 설정
            }
        });

        // **nowTextField 입력 완료 이벤트 (Enter 키로 업데이트)**
        introductionTextField.addActionListener(e -> {
            String updatedText = introductionTextField.getText().trim(); // 입력된 텍스트 가져오기
            if (UserProfileManager.saveIntroduction(userID, updatedText)) {
                introductionLabel.setText(updatedText.isEmpty() ? "Update your introduction." : updatedText); // 업데이트된 텍스트 반영
                JOptionPane.showMessageDialog(null, "Introduction updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update introduction.");
            }
            introductionTextField.setVisible(false); // 텍스트필드 숨김
            introductionLabel.setVisible(true); // 라벨 다시 표시
        });


        // 가입일: 라벨
        JLabel printJoinDateLabel = new JLabel("Subcription :");
        printJoinDateLabel.setForeground(Color.GRAY);
        printJoinDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        printJoinDateLabel.setBounds(80, 305, 200, 20); // 가입일 위치
        profilePanel.add(printJoinDateLabel);

        // 가입일 라벨
        joinDateLabel = new JLabel();
        joinDateLabel.setForeground(Color.GRAY);
        joinDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        joinDateLabel.setBounds(180, 305, 200, 20); // 가입일 위치
        profilePanel.add(joinDateLabel);

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

        // 프로필 데이터 수정 버튼 추가
        JPanel SettingTogglePanel = new JPanel();
        SettingTogglePanel.setBounds(740, 210, 80, 30); // 토글 버튼 영역
        SettingTogglePanel.setLayout(new GridLayout(1, 1, 1, 0));

        JButton setting = new JButton("Settings");
        setting.setBackground(Color.BLACK);
        setting.setForeground(Color.WHITE);
        setting.setFont(new Font("Plain", Font.PLAIN, 12));
        setting.setFocusPainted(false);
        SettingTogglePanel.add(setting);

        profilePanel.add(SettingTogglePanel);

        // 설정 버튼 클릭 이벤트 추가
        setting.addActionListener(e -> {
            // Settings 창 열기
            new Settings(userID); // 새 창 열기
        });

        // CardLayout으로 전환 패널 설정
        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.setBounds(0, 390, 850, 300);

        // TestArticle, TestComments, TestLikes에서 패널 가져오기
        TestArticle testArticle = new TestArticle(userID);
        TestComments testComments = new TestComments(userID);
        TestLikes testLikes = new TestLikes(userID);

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

    private void loadUserData() {
        SwingUtilities.invokeLater(() -> {
            // 닉네임 가져오기
            String nickname = UserProfileManager.getNickname(userID);
            if (nickname != null && !nickname.isEmpty()) {
                NicknameLabel.setText(nickname);
            } else {
                NicknameLabel.setText("No nickname found");
            }
    
            // 생성일 가져오기
            String joinDate = UserProfileManager.getCreateTime(userID);
            if (joinDate != null && !joinDate.isEmpty()) {
                joinDateLabel.setText(joinDate);
            } else {
                joinDateLabel.setText("No join date found");
            }

            // 소개글 가져오기
            String introduction = UserProfileManager.getIntroduction(userID);
            if (introduction != null && !introduction.isEmpty()) {
                introductionLabel.setText(introduction);
            } else {
                introductionLabel.setText("No introduction found");
            }

            // 프로필 이미지 경로 가져오기
            String imagePath = UserProfileManager.getImagePath(userID);
            if (imagePath != null && !imagePath.isEmpty()) {
                profileImagePath[0] = imagePath; // 경로 저장
            } else {
                profileImagePath[0] = UserProfileManager.DEFAULT_PROFILE_IMAGE; // 기본 이미지로 설정
            }
            profileImagePanel.repaint(); // 패널 다시 그리기
            
            // 프로필 이미지 경로 가져오기
            String wallPaperPath = UserProfileManager.getwallPaperPath(userID);
            if (wallPaperPath != null && !wallPaperPath.isEmpty()) {
                backgroundImagePath[0] = wallPaperPath; // 경로 저장
            } else {
                backgroundImagePath[0] = null; // 기본 이미지로 설정
            }
            profileImagePanel.repaint(); // 패널 다시 그리기
        });
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