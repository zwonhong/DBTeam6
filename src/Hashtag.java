import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Hashtag {

    int padding = 20; // 양쪽 여백
    int panelWidth = 850; // 패널 전체 너비
    int searchFieldHeight = 40; // 검색창 높이
    int searchFieldWidth = panelWidth - 2 * padding; // 검색창 너비 = 패널 너비 - 양쪽 여백

    public JPanel getHashtagPanel() {
        // 메인 패널 설정
        JPanel hashtagPanel = new JPanel();
        hashtagPanel.setBackground(Color.BLACK); // 패널 배경색
        hashtagPanel.setLayout(null); // 자유 배치
        hashtagPanel.setBounds(0, 0, 850, 700); // 오른쪽 패널 크기 설정

        // 둥근 모양 검색어 입력창
        JTextField searchField = new RoundedTextField(40, 40); // 둥근 모서리 텍스트 필드
        searchField.setFont(new Font("Arial", Font.PLAIN, 18));
        searchField.setForeground(Color.WHITE);
        searchField.setBackground(Color.BLACK);
        searchField.setCaretColor(Color.WHITE); // 커서 색상
        searchField.setBorder(new EmptyBorder(5, 10, 5, 10)); // 내부 여백 설정
        searchField.setBounds(padding, padding, searchFieldWidth, searchFieldHeight); // 가운데 정렬
        hashtagPanel.add(searchField);

        // 검색 결과 표시 패널
        JPanel resultsPanel = new JPanel();
        resultsPanel.setBackground(Color.BLACK);
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS)); // 세로 방향 정렬

        // 검색어 입력 후 Enter 키 이벤트 처리
        searchField.addActionListener(e -> {
            String query = searchField.getText().toLowerCase().trim(); // 입력받은 검색어
            resultsPanel.removeAll(); // 기존 결과 초기화

            // 일치하는 버튼만 추가
            //i값 수정 필요
            for (int i = 1; i <= 20; i++) {
                String buttonText = "Item " + i;
                if (buttonText.toLowerCase().contains(query)) {
                resultsPanel.add(createClickableButton(buttonText)); // 일치하는 버튼 추가
                }
            }

            resultsPanel.revalidate(); // 결과 패널 업데이트
            resultsPanel.repaint();
        });

        // 스크롤 기능 추가
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        scrollPane.setBounds(20, 80, 800, 600);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        hashtagPanel.add(scrollPane);

        // JScrollPane에 마우스 휠 리스너 추가 (스크롤 속도 수정)
        scrollPane.addMouseWheelListener(e -> {
            int rotation = e.getWheelRotation(); // 마우스 휠 회전 값 (양수는 아래, 음수는 위)
    
            // 스크롤 속도 조정 (1로 나누어 속도 조정)
            JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
            int newValue = verticalBar.getValue() + (rotation * 20); // 20을 곱해 스크롤 속도 증가
            verticalBar.setValue(newValue); // 새로운 위치로 스크롤
        });

        // 더미 데이터로 JPanels 추가
        //i값 수정 필요
        for (int i = 1; i <= 20; i++) {
            resultsPanel.add(createClickableButton("Item " + i));
        }

        return hashtagPanel;
    }

    // 클릭 가능한 JButton 생성 메서드
    private JButton createClickableButton(String text) {
        JButton clickableButton = new JButton(text); // 버튼 자체에 텍스트 설정
        clickableButton.setBackground(Color.BLACK);
        clickableButton.setForeground(Color.WHITE);
        clickableButton.setFont(new Font("Arial", Font.PLAIN, 16));
        clickableButton.setFocusPainted(false); // 포커스 테두리 제거
        clickableButton.setPreferredSize(new Dimension(0, 50)); // 높이만 고정, 가로는 부모 패널에 맞춤
        clickableButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); // 부모 패널의 가로 길이에 맞추기
        clickableButton.setBorder(BorderFactory.createEmptyBorder()); // 테두리를 투명하게 설정

        // 클릭 이벤트 (ActionListener 사용)
        clickableButton.addActionListener(e -> {
            System.out.println("Clicked on: " + text);
            JOptionPane.showMessageDialog(clickableButton, "You clicked on: " + text);
        });

        // 마우스 이벤트 처리 (마우스 오버 및 원상복귀)
        clickableButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                clickableButton.setBackground(Color.DARK_GRAY); // 마우스 오버 시 색상 변경
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                clickableButton.setBackground(Color.BLACK); // 마우스가 나가면 원상 복구
            }
        });

        return clickableButton;
    }

    // 둥근 텍스트 필드 구현
    public class RoundedTextField extends JTextField {
        private int cornerRadius; // 둥근 정도를 설정하는 변수

        public RoundedTextField(int columns, int cornerRadius) {
            super(columns); // 텍스트 필드의 기본 열 수 설정
            this.cornerRadius = cornerRadius; // 둥근 정도 설정
            setOpaque(false); // 배경을 투명하게 설정
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            // 부드러운 렌더링 설정
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 배경 색상 채우기
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

            // 부모 클래스의 기본 페인팅 호출
            super.paintComponent(g);

            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            // 부드러운 렌더링 설정
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 테두리 색상 설정
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

            g2.dispose();
        }
    }
}
