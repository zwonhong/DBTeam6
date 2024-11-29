import java.awt.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class AddCommentPanel {

    public JPanel createAddCommentPanel() {
        // 패널 배경 및 기본 설정
        JPanel containerPanel = new JPanel(null);
        containerPanel.setBackground(Color.DARK_GRAY);
        containerPanel.setPreferredSize(new Dimension(480, 360));

        // 상단 패널 (닫기 버튼)
        JPanel topPanel = new JPanel(null);
        topPanel.setBounds(0, 0, 480, 36);
        topPanel.setBackground(Color.DARK_GRAY);
        JButton closeButton = new JButton("X");
        closeButton.setBounds(10, 5, 26, 26);
        closeButton.setFont(new Font("Arial Unicode MS", Font.PLAIN, 10));
        closeButton.setBackground(Color.RED);

        // 닫기 버튼 액션 수정
        closeButton.addActionListener(e -> {
            // 부모 다이얼로그를 찾아 닫기
            Window parentWindow = SwingUtilities.getWindowAncestor(containerPanel);
            if (parentWindow instanceof JDialog) {
                parentWindow.dispose();
            }
        });
        topPanel.add(closeButton);
        containerPanel.add(topPanel);

        // 중간 패널 (프로필 이미지 및 기존 댓글)
        JPanel middlePanel = new JPanel(null);
        middlePanel.setBounds(0, 36, 480, 108);
        middlePanel.setBackground(Color.DARK_GRAY);

        ImageIcon originalIcon1 = new ImageIcon("C:\\Users\\starl\\OneDrive\\바탕 화면\\twitter2\\lib\\profile.png"); // 이미지 경로에서 로드
        Image scaledImage1 = originalIcon1.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        JLabel profileLabel1 = new JLabel(new ImageIcon(scaledImage1)); // 크기 조정 후 JLabel에 설정
        profileLabel1.setBounds(10, 10, 48, 48);
        profileLabel1.setBackground(Color.WHITE);
        middlePanel.add(profileLabel1);

        JTextArea existingComment = new JTextArea("Existing comment text");
        existingComment.setBounds(70, 10, 400, 88);
        existingComment.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
        existingComment.setForeground(Color.BLACK);
        existingComment.setBackground(Color.WHITE);
        existingComment.setLineWrap(true);
        existingComment.setWrapStyleWord(true);
        existingComment.setEditable(false);
        middlePanel.add(existingComment);
        containerPanel.add(middlePanel);

        // 입력 패널 (프로필 이미지 및 댓글 입력창)
        JPanel inputPanel = new JPanel(null);
        inputPanel.setBounds(0, 144, 480, 144);
        inputPanel.setBackground(Color.DARK_GRAY);

        ImageIcon originalIcon2 = new ImageIcon("C:\\Users\\starl\\OneDrive\\바탕 화면\\twitter2\\lib\\profile.png"); // 이미지 경로에서 로드
        Image scaledImage2 = originalIcon2.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
        JLabel profileLabel2 = new JLabel(new ImageIcon(scaledImage2)); // 크기 조정 후 JLabel에 설정
        profileLabel2.setBounds(10, 10, 48, 48);
        inputPanel.add(profileLabel2);

        JTextArea userInput = new JTextArea();
        userInput.setBounds(70, 10, 400, 124);
        userInput.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
        userInput.setForeground(Color.BLACK);
        userInput.setBackground(Color.WHITE);
        userInput.setLineWrap(true);
        userInput.setWrapStyleWord(true);
        ((AbstractDocument) userInput.getDocument()).setDocumentFilter(new DocumentFilter() {
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
        inputPanel.add(userInput);
        containerPanel.add(inputPanel);

        // 하단 패널 (Post 버튼)
        JPanel bottomPanel = new JPanel(null);
        bottomPanel.setBounds(0, 288, 480, 36);
        bottomPanel.setBackground(Color.DARK_GRAY);

        JButton postButton = new JButton("Post");
        postButton.setBounds(400, 5, 70, 26);
        postButton.setForeground(Color.BLACK);
        postButton.setBackground(Color.WHITE);
        postButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        postButton.addActionListener(e -> {
            // Add Post action logic here
            // 부모 다이얼로그를 찾아 닫기
            Window parentWindow = SwingUtilities.getWindowAncestor(containerPanel);
            if (parentWindow instanceof JDialog) {
                parentWindow.dispose();
            }
        });
        bottomPanel.add(postButton);
        containerPanel.add(bottomPanel);

        return containerPanel;
    }
}
