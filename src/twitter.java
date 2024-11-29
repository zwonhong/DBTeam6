package twitter;
import java.awt.*;
import javax.swing.*;

public class twitter {
    // 둥근 버튼 생성 메서드 (클래스 레벨에서 정의)
    private static JButton createRoundedButton() {
        JButton button = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 안티앨리어싱 활성화
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // 둥근 사각형 수치 변경으로 둥근 각도 조절
                super.paintComponent(g2);
                g2.dispose();
            }
        };

        button.setContentAreaFilled(false); // 기본 배경 제거
        button.setFocusPainted(false); // 포커스 테두리 제거
        button.setBorderPainted(false); // 기본 테두리 제거
        button.setForeground(Color.WHITE); // 텍스트 색상

        // 마우스 올렸을 때 테두리 효과 추가
        return button;
    }

    private static void initializeUI() {
        ImageIcon originalImg = new ImageIcon("D:\\DBTermProject\\lib\\twitter_logo.png");
        //각자 파일 로컬에 저장해서 알아서 주소 다시 붙여넣어야 합니다
        //이미지 이름은 모두 통일해서 쓰는 걸로 합시다

        // JFrame 생성
        JFrame frame = new JFrame("Twitter");
        frame.setSize(1200, 700); // 창 크기 설정
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기 버튼 클릭 시 프로그램 종료
        frame.setLocationRelativeTo(null); // 창을 화면 중앙에 위치
        frame.setResizable(false); // 창 크기 조절 비활성화
        frame.setLayout(null); // 레이아웃 매니저 비활성화 (자유 배치)
    
        // 배경 패널 설정 (검은색)
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.BLACK); // 배경색: 검은색
        backgroundPanel.setBounds(0, 0, 1200, 700); // 전체 창 크기로 설정
        backgroundPanel.setLayout(null); // 레이아웃 매니저 비활성화
        frame.add(backgroundPanel);
    
        // 세로선 패널 추가 (하얀색)
        JPanel verticalLine = new JPanel();
        int lineX = 350; // 하얀 선의 X 좌표 (고정 위치)
        verticalLine.setBackground(Color.WHITE); // 선 색: 하얀색
        verticalLine.setBounds(lineX, 0, 1, 700); // 세로선: (x, y, width, height)
        backgroundPanel.add(verticalLine);
    
        // 왼쪽 패널 추가
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLACK); // 패널 배경색: 어두운 회색
        leftPanel.setBounds(0, 0, lineX, 700); // (x, y, width, height)
        leftPanel.setLayout(null); // 자유 배치
        backgroundPanel.add(leftPanel);
    
        // 오른쪽 패널 추가
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.BLACK); // 패널 배경색: 밝은 회색
        rightPanel.setBounds(lineX + 1, 0, 1200 - (lineX + 1), 700); // 세로선 오른쪽 공간, +1은 세로선 공간
        rightPanel.setLayout(null); // 자유 배치
        backgroundPanel.add(rightPanel);

        // 오른쪽 패널_게시글 작성란
        // 오른쪽 패널_게시글 작성란_상단선
        JPanel horizontalLine_upper = new JPanel();
        horizontalLine_upper.setBackground(Color.WHITE); // 선 색: 흰색
        horizontalLine_upper.setBounds(rightPanel.getY(), 50, rightPanel.getWidth(), 1); // x, y, width, height
        rightPanel.add(horizontalLine_upper); // 오른쪽 패널에 가로선 추가

        // 오른쪽 패널_게시글 작성란_하단선
        JPanel horizontalLine_lower = new JPanel();
        horizontalLine_lower.setBackground(Color.WHITE); // 선 색: 흰색
        horizontalLine_lower.setBounds(rightPanel.getY(), 180, rightPanel.getWidth(), 1); // x, y, width, height
        //horizontal은 가로선이라서 height가 width 역할
        rightPanel.add(horizontalLine_lower); // 오른쪽 패널에 가로선 추가

        // 오른쪽 패널_게시글 작성란 (메인 텍스트 입력란)
        JPanel main_text = new JPanel();
        main_text.setBackground(Color.BLACK); // 패널 배경색: 검은색
        main_text.setBounds(
            0, // X 좌표: 오른쪽 패널의 왼쪽 가장자리
            horizontalLine_upper.getY() + horizontalLine_upper.getHeight(), // Y 좌표: 상단선 아래
            rightPanel.getWidth(), // 너비: 오른쪽 패널의 너비
            horizontalLine_lower.getY() - (horizontalLine_upper.getY() + horizontalLine_upper.getHeight()) // 높이: 두 가로선 사이의 거리
        );
        rightPanel.add(main_text); // 오른쪽 패널에 게시글 입력란 추가

        // 트위터 로고 버튼
        int padding1 = 20; // 양쪽 여백
        int Width = leftPanel.getWidth() - 2 * padding1; // 패널 너비 = leftPanel 너비 - 양쪽 여백
        Image img = originalImg.getImage(); // 이미지 객체 얻기
        Image scaledImg = img.getScaledInstance(100, 80, Image.SCALE_SMOOTH); // 크기 조정
        ImageIcon scaledIcon = new ImageIcon(scaledImg); // 크기 조정된 이미지를 다시 ImageIcon으로 감싸기
        JButton twitterLogo = createRoundedButton();
        twitterLogo.setBounds(padding1, 10, Width, 100); // 왼쪽 패널 내 위치
        twitterLogo.setIcon(scaledIcon);
        twitterLogo.setBackground(Color.BLACK); // 버튼 초기 배경색
    
        // 홈 버튼
        JButton homeButton = createRoundedButton();
        homeButton.setBounds(padding1, 110, Width, 40); // 왼쪽 패널 내 위치
        homeButton.setText("Home");
        homeButton.setBackground(Color.BLACK); // 버튼 초기 배경색
    
        // 홈 버튼 클릭 이벤트
        homeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                homeButton.setBackground(Color.GRAY); // 마우스 오버 시 색상 변경
            }
        
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                homeButton.setBackground(Color.BLACK); // 마우스가 나가면 원상 복구
            }
        
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // 오른쪽 패널 초기화
                rightPanel.removeAll();
        
                // 게시글 내용을 보여주는 Article 생성
                String articleid="article1";
                String userid="user1";
                Article article = new Article(articleid,userid);
        
                // JScrollPane으로 감싸기
                JScrollPane scrollPane = new JScrollPane(article.getArticlePanel());
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setBounds(0, 0, 835, 700); // x, y, width, height
        
                // 오른쪽 패널에 추가
                rightPanel.add(scrollPane);
        
                // UI 업데이트
                rightPanel.revalidate();
                rightPanel.repaint();
            }
        });
    
        // 검색 버튼
        JButton searchButton = createRoundedButton();
        searchButton.setBounds(padding1, 160, Width, 40); // 왼쪽 패널 내 위치
        searchButton.setText("Search");
        searchButton.setBackground(Color.BLACK); // 버튼 초기 배경색
        
        // 검색 버튼 클릭 이벤트
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                searchButton.setBackground(Color.GRAY); // 마우스 오버 시 색상 변경
            }
        
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                searchButton.setBackground(Color.BLACK); // 마우스가 나가면 원상 복구
            }
        
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // 버튼 클릭 동작
                rightPanel.removeAll(); // 오른쪽 패널 초기화
                rightPanel.add(new Hashtag().getHashtagPanel()); // 해시태그 패널 추가
                rightPanel.revalidate();
                rightPanel.repaint();
            }
        });
        

        // 프로필 버튼
        JButton profileButton = createRoundedButton();
        profileButton.setBounds(padding1, 210, Width, 40); // 왼쪽 패널 내 위치
        profileButton.setText("profileButton");
        profileButton.setBackground(Color.BLACK); // 버튼 초기 배경색
        leftPanel.add(profileButton); // Profile을 위해 추가됨
    
        // 프로필 버튼 클릭 이벤트
        profileButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                profileButton.setBackground(Color.GRAY); // 마우스 오버 시 색상 변경
            }
        
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
               profileButton.setBackground(Color.BLACK); // 마우스가 나가면 원상 복구
            }
        
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // 버튼 클릭 동작
                rightPanel.removeAll(); // 오른쪽 패널 초기화
                rightPanel.add(new Profile("1").getProfilePanel()); // 프로필 패널 추가
// 임의의 아이디
                rightPanel.revalidate();
                rightPanel.repaint();
            }
        });
        

        // 현재 로그인 정보 버튼
        JButton current_Login = createRoundedButton();
        current_Login.setBounds(padding1, 260, Width, 40); // 하얀 선 왼쪽에 배치
        current_Login.setText("Current_Login");
        current_Login.setBackground(Color.BLACK); // 버튼 초기 배경색
    
        // 현재 로그인 정보 이벤트
        current_Login.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                current_Login.setBackground(Color.GRAY); // 마우스 오버 시 색상 변경
            }
        
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
               current_Login.setBackground(Color.BLACK); // 마우스가 나가면 원상 복구
            }
        
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                // 버튼 클릭 동작
                rightPanel.removeAll(); // 오른쪽 패널 초기화
                // 더 액션이 들어감
            }
        });

        // 왼쪽 패널에 버튼 추가
        leftPanel.add(twitterLogo);
        leftPanel.add(homeButton);
        leftPanel.add(searchButton);
        leftPanel.add(profileButton);
        leftPanel.add(current_Login);

        int padding2 = 20; // 양쪽 여백
        int hashtagWidth = leftPanel.getWidth() - 2 * padding2; // 패널 너비 = leftPanel 너비 - 양쪽 여백
        // 해시태그 패널
        JPanel hashtagPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 안티앨리어싱
                g2.setColor(Color.BLACK); // 사각형 색상
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // 둥근 사각형 (x, y, width, height, arcWidth, arcHeight)
                // 테두리 색상 설정 (하얀색)
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(2)); // 테두리 두께 설정
                g2.drawRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // 둥근 테두리 그리기
                g2.dispose();
            }
        };
        hashtagPanel.setBounds(padding2, 330, hashtagWidth, 300); // 위치 및 크기 설정
        hashtagPanel.setOpaque(false); // 배경 투명 설정 (배경색 제거)
        leftPanel.add(hashtagPanel);

        // JFrame 표시
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        initializeUI();
    }
}