import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class mainPage {

    private final JPanel mainPanel;
    private final List<JPanel> dynamicPanels = new ArrayList<>();
    int panelHeight = 50;

    public mainPage() {
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK); // 패널 배경색
        mainPanel.setBounds(0, 0, 1200 - 350 - 1, 700); // 세로선 오른쪽 공간
        mainPanel.setLayout(null); // 자유 레이아웃

        // 오른쪽 패널_게시글 작성란 상단선
        JPanel horizontalLine_upper = new JPanel();
        horizontalLine_upper.setBackground(Color.WHITE); // 선 색
        horizontalLine_upper.setBounds(mainPanel.getY(), 50, mainPanel.getWidth(), 1);
        mainPanel.add(horizontalLine_upper);

        // 오른쪽 패널_게시글 작성란 하단선
        JPanel horizontalLine_lower = new JPanel();
        horizontalLine_lower.setBackground(Color.WHITE);
        horizontalLine_lower.setBounds(mainPanel.getY(), 180, mainPanel.getWidth(), 1);
        mainPanel.add(horizontalLine_lower);

        // 오른쪽 패널_게시글 작성란 (메인 텍스트 입력란)
        JPanel main_text = new JPanel();
        main_text.setBackground(Color.BLACK);
        main_text.setBounds(
            0, // X 좌표
            horizontalLine_upper.getY() + horizontalLine_upper.getHeight(),
            mainPanel.getWidth(),
            horizontalLine_lower.getY() - (horizontalLine_upper.getY() + horizontalLine_upper.getHeight())
        );
        mainPanel.add(main_text);

        JLabel profileLabel = new JLabel(new ImageIcon(new ImageIcon("C:\\Users\\starl\\OneDrive\\바탕 화면\\twitter2\\lib\\profile.png").getImage()
            .getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        profileLabel.setBounds(10, 10, 50, 50);
        main_text.add(profileLabel);
        profileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                mainPanel.removeAll();
                Profile profile = new Profile();
                mainPanel.add(profile.getProfilePanel());
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        // 텍스트 입력 영역 추가
        JTextArea textArea = new JTextArea(4, 10);
        textArea.setBounds(100, 10, main_text.getWidth() - 190, 100);
        textArea.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setCaretColor(Color.WHITE);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        main_text.add(textArea);

        // 글자 제한 추가 (최대 200자)
        ((AbstractDocument) textArea.getDocument()).setDocumentFilter(new DocumentFilter() {
            private static final int MAX_LENGTH = 160;

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if ((fb.getDocument().getLength() + string.length()) <= MAX_LENGTH) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() - length + text.length()) <= MAX_LENGTH) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        // 포스트 버튼 추가
        JButton postButton = new JButton();
        postButton.setBounds(0, textArea.getY() + 50, 50, 50);
        postButton.setText("Post");
        postButton.setBackground(Color.PINK);
        main_text.add(postButton);

        // 삽입된 이미지 이름 확인
        JLabel imageNameLabel = new JLabel();
        imageNameLabel.setBounds(115, 10, 350, 50);
        imageNameLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
        imageNameLabel.setForeground(Color.WHITE);
        main_text.add(imageNameLabel);

        // 이미지 삽입 버튼 추가
        JButton imageInsertButton = new JButton();
        imageInsertButton.setBounds(0, 10, 50, 50);
        imageInsertButton.setText("Image");
        imageInsertButton.setBackground(Color.CYAN);
        main_text.add(imageInsertButton);

        // 이미지 삽입 버튼 actionListener
        imageInsertButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select an Image");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int result = fileChooser.showOpenDialog(mainPanel);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imageNameLabel.setText(selectedFile.getName());
            }
        });


        // 동적 패널 생성
        int numberOfPanels = 50;
        int yOffset = horizontalLine_lower.getY() + horizontalLine_lower.getHeight() + 10;
        for (int i = 0; i < numberOfPanels; i++) {
            JPanel dynamicPanel = createDynamicPanel("게시글 내용 " + (i + 1) + "\n추가 텍스트 예시.", yOffset);
            dynamicPanels.add(dynamicPanel);
            mainPanel.add(dynamicPanel);
            yOffset += dynamicPanel.getHeight() + 10;
        }

        // mainPanel 높이 갱신
        mainPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), yOffset));
        mainPanel.revalidate();
        mainPanel.repaint();

        // mainPanel 크기 변경 이벤트 처리
        mainPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                int panelWidth = mainPanel.getWidth();
                int yOffset = horizontalLine_lower.getY() + horizontalLine_lower.getHeight() + 10;
                for (JPanel dynamicPanel : dynamicPanels) {
                    for (Component component : dynamicPanel.getComponents()) {
                        if (component instanceof JTextArea) {
                            component.setBounds(70, 10, panelWidth - 90, component.getHeight());
                        } else if (component instanceof JPanel) {
                            component.setBounds(70, dynamicPanel.getHeight() - 40, panelWidth - 90, 30);
                        }
                    }
                    dynamicPanel.setBounds(0, yOffset, panelWidth, dynamicPanel.getHeight());
                    yOffset += dynamicPanel.getHeight() + 10;
                }
                mainPanel.setPreferredSize(new Dimension(panelWidth, yOffset));
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
    }
        

    // 동적 패널 생성 메서드
    private JPanel createDynamicPanel(String postContent, int yOffset) {
        JPanel dynamicPanel = new JPanel(null);
        dynamicPanel.setBackground(Color.BLACK);
        dynamicPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        int panelWidth = mainPanel.getWidth();

        JLabel profileLabel = new JLabel(new ImageIcon(new ImageIcon("C:\\Users\\starl\\OneDrive\\바탕 화면\\twitter2\\lib\\profile.png").getImage()
            .getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        profileLabel.setBounds(10, 10, 50, 50);
        dynamicPanel.add(profileLabel);
        profileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                mainPanel.removeAll();
                Profile profile = new Profile();
                mainPanel.add(profile.getProfilePanel());
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        JTextArea postTextArea = new JTextArea(postContent);
        postTextArea.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
        postTextArea.setForeground(Color.WHITE);
        postTextArea.setBackground(Color.DARK_GRAY);
        postTextArea.setLineWrap(true);
        postTextArea.setWrapStyleWord(true);
        postTextArea.setEditable(false);
        postTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                //아티클 불러오는 메소드
                //Article article = new Article(JFrame frame, String tweetContent);
                
            }
        });

        int textHeight = calculateTextHeight(postTextArea, panelWidth - 90);
        postTextArea.setBounds(70, 10, panelWidth - 90, textHeight);
        dynamicPanel.add(postTextArea);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        buttonPanel.setBounds(70, textHeight + 20, panelWidth - 90, 30);
        buttonPanel.setBackground(Color.BLACK);

        JButton commentButton = new JButton("Comment");
        commentButton.setForeground(Color.WHITE);
        commentButton.setBackground(Color.BLACK);
        buttonPanel.add(commentButton);
        commentButton.addActionListener(e-> {
                openAddCommentPanel();
            });

        JToggleButton likeButton = new JToggleButton("Like");
        likeButton.setForeground(Color.WHITE);
        likeButton.setBackground(Color.BLACK);
        likeButton.addActionListener(e -> {
            if (likeButton.isSelected()) {
                likeButton.setText("Liked");
                likeButton.setForeground(Color.RED);
                //좋아요 count +1
            } else {
                likeButton.setText("Like");
                likeButton.setForeground(Color.WHITE);
                //좋아요 count -1
            }
        });
        buttonPanel.add(likeButton);

        dynamicPanel.add(buttonPanel);

        panelHeight = textHeight + 20 + 30 + 10;
        dynamicPanel.setBounds(0, yOffset, panelWidth, panelHeight);

        return dynamicPanel;
    }

    private int calculateTextHeight(JTextArea textArea, int textAreaWidth) {
        FontMetrics metrics = textArea.getFontMetrics(textArea.getFont());
        int lineHeight = metrics.getHeight();
        int textWidth = metrics.stringWidth(textArea.getText());
        int lines = (int) Math.ceil((double) textWidth / textAreaWidth);
        return lines * lineHeight;
    }

    private void openAddCommentPanel() {
        // 새로운 JPanel 생성
        AddCommentPanel addCommentPanel = new AddCommentPanel();
        JPanel commentPanel = addCommentPanel.createAddCommentPanel();
    
        // JDialog 설정
        JDialog commentDialog = new JDialog((Frame) null, "Add Comment", true);
        commentDialog.setUndecorated(true);
        commentDialog.setSize(480, 360);
    
        // 화면 중앙 위치 계산
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - commentDialog.getWidth()) / 2;
        int y = (screenSize.height - commentDialog.getHeight()) / 2;
        commentDialog.setLocation(x, y);
    
        // JPanel 추가
        commentDialog.add(commentPanel);
    
        // 다이얼로그 표시
        commentDialog.setVisible(true);
    }
    

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
