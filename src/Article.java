import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 

public class Article {
    private JPanel articlePanel;
    private JPanel horizontalLineLower; // 하단선을 동적으로 업데이트하기 위해 선언
    private JScrollPane scrollPane;  // scrollPane을 클래스의 인스턴스 변수로 선언
    private StringBuilder replyText; // 댓글 내용을 저장할 변수

    public Article(String tweetContent) {
        replyText = new StringBuilder(); // 댓글 저장을 위한 StringBuilder 초기화        
        // 오른쪽 패널 (Article 페이지 레이아웃 설정)
        articlePanel = new JPanel();
        articlePanel.setBackground(Color.BLACK); // 패널 배경색
        articlePanel.setLayout(null); // 자유 배치
        
        JButton backButton = new JButton("←"); // Left arrow button
        JLabel postLabel = new JLabel("Post"); // Post label

        // Customize button and label appearance if needed
        backButton.setFont(new Font("Arial", Font.BOLD, 16)); // Change font size for the arrow button
        backButton.setBackground(Color.BLACK); // Set button background to black
        backButton.setForeground(Color.WHITE); // Set button text color to white
        backButton.setBounds(0, 0, 50, 40); // 패널 위치

        postLabel.setForeground(Color.WHITE);
        postLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Make the "Post" label bold
        postLabel.setBounds(60, 5, 100, 40); // 패널 위치
        
        backButton.addActionListener(e -> {
            // 뒤로 가기 동작 수행 (예: 다른 화면으로 이동)
            System.out.println("Back button clicked");
        });


        // Add components to the top panel
        articlePanel.add(backButton);
        articlePanel.add(postLabel);

     // 상단 프로필 이미지 + 유저명, 닉네임 패널
        JPanel userPanel = new JPanel();
        userPanel.setBackground(Color.BLACK);
        userPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // 왼쪽 정렬
        userPanel.setBounds(10, 60, 800, 40); // 패널 위치

        // 프로필 이미지 버튼 (원형 이미지)
        ImageIcon profileImageIcon = new ImageIcon("C:\\Users\\Notebook\\Desktop\\profile.png"); // 이미지 경로
        Image profileImage = profileImageIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // 크기 조정

        JButton profileButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                // 안티앨리어싱 설정
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // 원형 클리핑 설정
                g2d.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, getWidth(), getHeight()));

                // 이미지 그리기
                g2d.drawImage(profileImage, 0, 0, getWidth(), getHeight(), null);
                g2d.dispose();
            }
        };

        profileButton.setPreferredSize(new Dimension(40, 40)); // 동그란 크기 설정
        profileButton.setBorder(BorderFactory.createEmptyBorder()); // 테두리 없애기
        profileButton.setContentAreaFilled(false); // 배경 투명으로 설정
        profileButton.setFocusPainted(false); // 포커스 테두리 없애기

        // 프로필 버튼에 이벤트 추가
        profileButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(userPanel, "Profile clicked!");
            // 현재 articlePanel을 포함하는 최상위 Container 가져오기
            Container parent = articlePanel.getParent();

            if (parent != null) {
                parent.remove(articlePanel); // 현재 Article 패널 제거
                OtherProfile otherProfile = new OtherProfile(); // OtherProfile 객체 생성
                parent.add(otherProfile.getProfilePanel()); // OtherProfile 패널 추가
                parent.revalidate(); // UI 갱신
                parent.repaint(); // 화면 갱신
            } else {
                JOptionPane.showMessageDialog(articlePanel, "Parent container not found.");
            }
        });

        // 패널에 추가
        userPanel.add(profileButton);

        
        // 유저명과 닉네임 표시
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS)); // 세로로 정렬
        userInfoPanel.setBackground(Color.BLACK);
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // 왼쪽에 10px 여백 추가

        JLabel usernameLabel = new JLabel("nickname"); // 유저명
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel nicknameLabel = new JLabel("@" + "userid"); // 닉네임
        nicknameLabel.setForeground(Color.WHITE);
        nicknameLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        userInfoPanel.add(usernameLabel);
        userInfoPanel.add(nicknameLabel);

        // 프로필 이미지 + 유저 정보 패널
        userPanel.add(profileButton);
        userPanel.add(userInfoPanel);

        // 클릭 이벤트 처리 (프로필 이미지와 유저명, 닉네임 그룹화)
        userPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 클릭했을 때 수행할 기능
                JOptionPane.showMessageDialog(articlePanel, "User profile clicked!"); // 예시로 메시지 출력
                // 현재 articlePanel을 포함하는 최상위 Container 가져오기
                Container parent = articlePanel.getParent();

                if (parent != null) {
                    parent.remove(articlePanel); // 현재 Article 패널 제거
                    OtherProfile otherProfile = new OtherProfile(); // OtherProfile 객체 생성
                    parent.add(otherProfile.getProfilePanel()); // OtherProfile 패널 추가
                    parent.revalidate(); // UI 갱신
                    parent.repaint(); // 화면 갱신
                } else {
                    JOptionPane.showMessageDialog(articlePanel, "Parent container not found.");
                }
            }
        });

        articlePanel.add(userPanel);
        
        // 상단선
        JPanel horizontalLineUpper = new JPanel();
        horizontalLineUpper.setBackground(Color.WHITE); // 선 색: 흰색
        horizontalLineUpper.setBounds(1, 50, 800, 1); // x, y, width, height
        articlePanel.add(horizontalLineUpper);

        // 트윗 내용 패널
        JPanel tweetPanel = new JPanel();
        tweetPanel.setBackground(Color.BLACK); // 배경색
        tweetPanel.setBounds(0, horizontalLineUpper.getY() + 60, 800, 0); // 높이는 내용에 따라 변경
        tweetPanel.setLayout(null); // 자유 배치
        articlePanel.add(tweetPanel);
        
        // 트윗 내용 (JTextArea 사용)
        JTextArea tweetTextArea = new JTextArea(tweetContent); // 텍스트 내용
        tweetTextArea.setBackground(Color.BLACK); // 배경색
        tweetTextArea.setForeground(Color.WHITE); // 텍스트 색상
        tweetTextArea.setFont(new Font("Arial", Font.PLAIN, 16)); // 글꼴 설정
        tweetTextArea.setLineWrap(true); // 자동 줄 바꿈
        tweetTextArea.setWrapStyleWord(true); // 단어 단위로 줄 바꿈
        tweetTextArea.setEditable(false); // 수정 불가

        // 텍스트 영역 크기 설정 (여백 포함)
        tweetTextArea.setBounds(20, 10, 760, 100); // 초기 높이 지정 (자동으로 늘어날 예정)
        tweetPanel.add(tweetTextArea);

        // 내용 높이에 맞게 크기 조정
        Dimension textAreaSize = tweetTextArea.getPreferredSize(); // 텍스트 내용에 맞는 크기 계산
        tweetTextArea.setSize(760, textAreaSize.height); // JTextArea 크기 설정
        tweetPanel.setSize(800, textAreaSize.height + 20); // 패널 크기 설정 (여백 포함)
        

        // 생성 시간 JLabel 생성
        JLabel creationTimeLabel = new JLabel("date:");
        creationTimeLabel.setForeground(Color.WHITE); // 텍스트 색상
        creationTimeLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // 폰트 설정
        creationTimeLabel.setBounds(20, tweetPanel.getY() + tweetPanel.getHeight()+20, 70, 20); // 위치 설정

        // 트윗 패널에 생성 시간 추가
        articlePanel.add(creationTimeLabel);


        // 하단선1
        horizontalLineLower = new JPanel();
        horizontalLineLower.setBackground(Color.WHITE); // 선 색: 흰색
        horizontalLineLower.setBounds(1, tweetPanel.getY() + tweetPanel.getHeight() +50 , 800, 1); // 동적 Y 좌표
        articlePanel.add(horizontalLineLower);
        
     // 좋아요 버튼 생성
        JButton likeButton = new JButton("like");
        likeButton.setForeground(Color.WHITE);
        likeButton.setFont(new Font("Arial", Font.PLAIN, 16));
        likeButton.setBackground(Color.pink); // 배경색
        likeButton.setBorderPainted(false); // 기본 테두리 없애기
        likeButton.setFocusPainted(false); // 포커스 테두리 없애기
        likeButton.setBounds(700, tweetPanel.getY() + tweetPanel.getHeight(), 70, 40); // 위치 설정 (하단선 위 오른쪽 끝)

        // 좋아요 버튼 클릭 이벤트 (토글)
        likeButton.addActionListener(e -> {
            if (likeButton.getText().equals("like")) {
                likeButton.setText("unlike"); // 버튼 텍스트 변경
                likeButton.setForeground(Color.red); // 눌린 상태에서 빨간색으로 변경
            } else {
                likeButton.setText("like"); // 버튼 텍스트 원래대로 변경
                likeButton.setForeground(Color.WHITE); // 취소 상태에서 원래 색상으로 변경
            }
        });

        // 트윗 내용 패널에 좋아요 버튼 추가
        articlePanel.add(likeButton);
        
     // 코멘트 입력칸 (JTextArea 사용) - 테두리 없애기
        JTextArea commentInput = new JTextArea();
        commentInput.setFont(new Font("Arial", Font.PLAIN, 14));
        commentInput.setLineWrap(true);  // 자동 줄바꿈
        commentInput.setWrapStyleWord(true);  // 단어 단위로 줄바꿈
        commentInput.setBackground(Color.BLACK);
        commentInput.setForeground(Color.gray);
        commentInput.setCaretColor(Color.WHITE);  // 커서 색상
        commentInput.setBorder(null);  // 테두리 없애기
        commentInput.setText("Post your reply");  // 기본 텍스트 설정
        commentInput.setPreferredSize(new Dimension(600, 60));  // 높이를 제한

        
        // 포커스가 가면 텍스트를 지우고, 포커스를 잃으면 텍스트가 없다면 다시 기본 텍스트 설정
        commentInput.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (commentInput.getText().equals("Post your reply")) {
                    commentInput.setText("");
                    commentInput.setForeground(Color.WHITE);  // 입력 중에는 텍스트 색상 흰색으로 변경
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (commentInput.getText().isEmpty()) {
                    commentInput.setText("Post your reply");
                    commentInput.setForeground(Color.GRAY);  // 기본 텍스트는 흐리게 설정
                }
            }
        });
        
     // 'Reply' 버튼 추가
        JButton replyButton = new JButton("Reply");
        replyButton.setForeground(Color.WHITE);
        replyButton.setFont(new Font("Arial", Font.PLAIN, 14));
        replyButton.setBackground(Color.pink);
        replyButton.setBorderPainted(false); // 기본 테두리 없애기
        replyButton.setFocusPainted(false); // 포커스 테두리 없애기
        replyButton.setBounds(700, tweetPanel.getY() + tweetPanel.getHeight() + 70, 70, 40); // 위치 설정 (댓글 입력칸 옆에 배치)

        replyButton.addActionListener(e -> {
            replyText.setLength(0); // 기존 내용 초기화
            replyText.append(commentInput.getText()); // 입력된 댓글 저장
            commentInput.setText(""); // 댓글 입력창 비우기
            commentInput.setForeground(Color.GRAY); // 기본 텍스트 색상으로 되돌리기
            commentInput.setText("Post your reply");  // 기본 텍스트로 설정
            System.out.println("작성된 댓글: " + replyText);
        });

        // 댓글 입력칸과 reply 버튼 추가
        articlePanel.add(commentInput);
        articlePanel.add(replyButton);

        // 스크롤 기능 추가
        JScrollPane commentScrollPane = new JScrollPane(commentInput);
        commentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        commentScrollPane.setBounds(75, tweetPanel.getY() + tweetPanel.getHeight() + 65, 600, 60);  // 위치 설정
        articlePanel.add(commentScrollPane);        
        
        
     // // 댓글 입력창 옆에 동그란 이미지 버튼 추가
        ImageIcon myProfileImageIcon = new ImageIcon("C:\\Users\\Notebook\\Desktop\\profile.png"); // 이미지 경로
        Image myProfileImage = myProfileImageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // 이미지 크기 조정

        JButton profileImageButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                // 안티앨리어싱 설정
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // 원형 클리핑 설정
                g2d.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, getWidth(), getHeight()));

                // 이미지 그리기
                g2d.drawImage(myProfileImage, 0, 0, getWidth(), getHeight(), null);
                g2d.dispose();
            }
        };

        profileImageButton.setPreferredSize(new Dimension(40, 40)); // 버튼 크기 설정
        profileImageButton.setBorder(BorderFactory.createEmptyBorder()); // 테두리 없애기
        profileImageButton.setContentAreaFilled(false); // 배경 투명으로 설정
        profileImageButton.setFocusPainted(false); // 포커스 테두리 없애기

        // 이미지 버튼을 댓글 입력칸 왼쪽에 배치
        profileImageButton.setBounds(20, tweetPanel.getY() + tweetPanel.getHeight() + 70, 40, 40);  // 위치 설정

        // 이미지 버튼에 클릭 이벤트 추가
        profileImageButton.addActionListener(e -> {
            // 이미지 버튼 클릭 시 수행할 기능
            JOptionPane.showMessageDialog(articlePanel, "Profile image clicked!");    
        });

        // 트윗 내용 패널에 이미지 버튼 추가
        articlePanel.add(profileImageButton);
        
        // 하단선2
        horizontalLineLower = new JPanel();
        horizontalLineLower.setBackground(Color.WHITE); // 선 색: 흰색
        horizontalLineLower.setBounds(1, tweetPanel.getY() + tweetPanel.getHeight() + 145 , 800, 1); // 동적 Y 좌표
        articlePanel.add(horizontalLineLower);
        
     // 댓글을 저장하는 리스트 (더미 데이터)
        String[][] dummyComments = {
        	    {"user1", "John Doe", "This is comment 1"},
        	    {"user2", "Jane Smith", "This is comment 2"},
        	    {"user3", "Mike Brown", "This is comment 3"},
        	    {"user4", "Alice Johnson", "This is comment 4"},
        	    {"user5", "Bob White", "This is comment 5"},
        	    {"user6", "Charlie Davis", "This is comment 6"},
        	    {"user7", "Eve Adams", "This is comment 7"}
        };

     // 댓글을 표시할 패널
        JPanel commentsPanel = new JPanel();
        commentsPanel.setLayout(null); // 자유 배치
        commentsPanel.setBackground(Color.BLACK);
        commentsPanel.setBounds(0, horizontalLineLower.getY() + 10, 800, 500); // 초기 높이를 적절히 설정
        articlePanel.add(commentsPanel);

        // 댓글을 추가하는 메서드 (댓글 추가마다 호출)
        int commentStartY = 0; // 첫 댓글의 Y 좌표

        // 댓글이 추가될 때마다 반복
        for (String[] commentData : dummyComments) {
        	
        	 String userId = commentData[0];    // 유저 아이디
        	    String userName = commentData[1]; // 유저 이름
        	    String commentText = commentData[2]; // 댓글 내용
        	    
            // 댓글 내용의 높이 계산
            JTextArea commentTextArea = new JTextArea(commentText);
            commentTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
            commentTextArea.setWrapStyleWord(true);
            commentTextArea.setLineWrap(true);
            commentTextArea.setEditable(false);
            
            // JTextArea의 텍스트가 차지하는 높이를 계산 (줄 수에 맞춰 크기 조정)
            int textHeight = commentTextArea.getPreferredSize().height;
            
            // 댓글 패널 생성
            JPanel commentPanel = new JPanel();
            commentPanel.setLayout(null);
            commentPanel.setBackground(Color.BLACK);
            commentPanel.setBounds(0, commentStartY, 800, textHeight + 40); // 텍스트 높이에 맞게 댓글 패널 크기 설정

         // 유저 정보 라벨
            JLabel userInfoLabel = new JLabel(userName+" @"+userId );
            userInfoLabel.setBounds(75, 5, 700, 20); // 유저 정보 위치와 크기
            userInfoLabel.setFont(new Font("Arial", Font.BOLD, 12));
            userInfoLabel.setForeground(Color.LIGHT_GRAY);
            commentPanel.add(userInfoLabel);
            
            // 댓글 내용
            commentTextArea.setBounds(75, 20, 600, textHeight); // 댓글 텍스트 위치와 크기
            commentTextArea.setBackground(Color.BLACK);
            commentTextArea.setForeground(Color.WHITE);
            commentPanel.add(commentTextArea);

            // 댓글 작성자의 프로필 이미지
            
            
            ImageIcon commentProfileImageIcon = new ImageIcon("C:\\Users\\Notebook\\Desktop\\profile.png");
            Image commentProfileImage = commentProfileImageIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            JButton commentProfileButton = new JButton() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g.create();

                    // 안티앨리어싱 설정
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // 원형 클리핑 설정
                    g2d.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, getWidth(), getHeight()));

                    // 이미지 그리기
                    g2d.drawImage(commentProfileImage, 0, 0, getWidth(), getHeight(), null);
                    g2d.dispose();
                }
            };
            commentProfileButton.setBounds(20, 10, 40, 40);
            commentProfileButton.setBorder(BorderFactory.createEmptyBorder());
            commentProfileButton.setContentAreaFilled(false);
            commentProfileButton.setFocusPainted(false);
            commentPanel.add(commentProfileButton);

            // 댓글 생성 시간을 표시하는 라벨
            JLabel commentTimeLabel = new JLabel("5m ago"); // 더미 시간
            commentTimeLabel.setBounds(75, textHeight + 20, 100, 20);
            commentTimeLabel.setForeground(Color.GRAY);
            commentTimeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            commentPanel.add(commentTimeLabel);
            
            // 댓글에 좋아요 버튼 추가
            JButton commentLikeButton = new JButton("like");
            commentLikeButton.setForeground(Color.WHITE);
            commentLikeButton.setFont(new Font("Arial", Font.PLAIN, 10));
            commentLikeButton.setBackground(Color.pink); // 배경색
            commentLikeButton.setBorderPainted(false); // 기본 테두리 없애기
            commentLikeButton.setFocusPainted(false); // 포커스 테두리 없애기
            commentLikeButton.setBounds(700, textHeight + 10, 50,30); // 위치 설정

            // 좋아요 버튼 클릭 이벤트 (각 댓글마다 토글)
            commentLikeButton.addActionListener(e -> {
                if (commentLikeButton.getText().equals("like")) {
                    commentLikeButton.setText("unlike");
                    commentLikeButton.setForeground(Color.red);
                } else {
                    commentLikeButton.setText("like");
                    commentLikeButton.setForeground(Color.WHITE);
                }
            });

            commentPanel.add(commentLikeButton);


            // 댓글 패널을 commentsPanel에 추가
            commentsPanel.add(commentPanel);

            // 댓글 사이에 흰색 선 추가
            JPanel whiteLinePanel = new JPanel();
            whiteLinePanel.setBounds(0, commentStartY + textHeight + 50, 800, 2); // 댓글과 댓글 사이에 선을 추가
            whiteLinePanel.setBackground(Color.WHITE); // 흰색 선
            commentsPanel.add(whiteLinePanel);

            // 다음 댓글의 Y 위치 업데이트
            commentStartY += textHeight + 60; // 댓글 높이 + 간격 (50px) 추가
             
        }
        
        commentsPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 클릭했을 때 수행할 기능
                JOptionPane.showMessageDialog(articlePanel, "User profile clicked!"); // 예시로 메시지 출력
                Container parent = articlePanel.getParent();

                if (parent != null) {
                    parent.remove(articlePanel); // 현재 Article 패널 제거
                    OtherProfile otherProfile = new OtherProfile(); // OtherProfile 객체 생성
                    parent.add(otherProfile.getProfilePanel()); // OtherProfile 패널 추가
                    parent.revalidate(); // UI 갱신
                    parent.repaint(); // 화면 갱신
                } else {
                    JOptionPane.showMessageDialog(articlePanel, "Parent container not found.");
                }
            }
        });

        // 댓글 패널 크기 업데이트
        commentsPanel.setSize(800, commentStartY); // 댓글이 추가될 때마다 commentsPanel의 크기 조정
        articlePanel.setPreferredSize(new Dimension(900, commentStartY + 400));  // 댓글 외 여백 추가

        
        // 전체 패널 재배치
        articlePanel.revalidate();
        articlePanel.repaint();

              
    }

    // 하단선 업데이트 메서드 (필요 시 추가로 업데이트 가능)
    public void updateLowerLinePosition(int newY) {
        horizontalLineLower.setBounds(0, newY, 800, 1);
        articlePanel.revalidate();
        articlePanel.repaint();
    }

    // Article 패널 반환
    public JPanel getArticlePanel() {
        return articlePanel;
    }
    
        
    public JScrollPane getScrollPanel() {
        return scrollPane;
    }
}

