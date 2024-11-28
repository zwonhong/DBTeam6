import javax.swing.*;
import java.awt.*;

public class TestLikes {
    public JPanel getLikesPanel() {
        JPanel likesPanel = new JPanel();
        likesPanel.setLayout(new BoxLayout(likesPanel, BoxLayout.Y_AXIS));
        likesPanel.setBackground(Color.BLACK);

        for (int i = 1; i <= 30; i++) {
            JLabel likeLabel = new JLabel("Liked Item " + i);
            likeLabel.setForeground(Color.WHITE);
            likeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            likeLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            likesPanel.add(likeLabel);
        }
        return likesPanel;
    }
}
