import javax.swing.*;
import java.awt.*;

public class Settings extends JFrame {
    private String userID;
    private JLabel CurrentNickfromDB;
    private JLabel CurrentPhonefromDB;
    private JCheckBox maleCheckBox;
    private JCheckBox femaleCheckBox;
    private JCheckBox otherCheckBox;


    public Settings(String userID) {
        this.userID = userID; // user_ID 저장
        initializeUI(); // UI 초기화
        loadUserData(); // 데이터 불러오기
    }

    public void initializeUI() {
        // 기본 설정
        setTitle("Settings Page");
        setSize(400, 500); // 창 크기
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창 닫기 설정 (메인 프로그램은 종료되지 않음)
        setLocationRelativeTo(null); // 화면 중앙에 표시
        setResizable(false); // 창 크기 고정

        // 패널 생성
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(null); // 자유 배치

        // 패널 소개 창
        JLabel UserSetLabel = new JLabel("@Username's Setting Prompt");
        UserSetLabel.setForeground(Color.WHITE);
        UserSetLabel.setFont(new Font("Arial", Font.BOLD, 16));
        UserSetLabel.setBounds(70, 20, 300, 20);
        panel.add(UserSetLabel);

        // 닉네임 설정 탭
        JLabel NicknameLabel = new JLabel("Nickname");
        NicknameLabel.setForeground(Color.WHITE);
        NicknameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        NicknameLabel.setBounds(10, 60, 200, 20);
        panel.add(NicknameLabel);

        JLabel CurrentNick = new JLabel("Current Nickname :");
        CurrentNick.setForeground(Color.GRAY);
        CurrentNick.setFont(new Font("Arial", Font.PLAIN, 12));
        CurrentNick.setBounds(20, 90, 130, 20); // 위치 설정
        panel.add(CurrentNick);

        // DB 연동하고 DB에서 닉네임 불러와서 출력해야함 바로 이 아래!
        CurrentNickfromDB = new JLabel();
        CurrentNickfromDB.setForeground(Color.GRAY);
        CurrentNickfromDB.setFont(new Font("Arial", Font.PLAIN, 12));
        CurrentNickfromDB.setBounds(140, 90, 130, 20); // 위치 설정
        panel.add(CurrentNickfromDB);

        JLabel ChangeNick = new JLabel("Nickname to change :");
        ChangeNick.setForeground(Color.GRAY);
        ChangeNick.setFont(new Font("Arial", Font.PLAIN, 12));
        ChangeNick.setBounds(20, 110, 130, 20); // 위치 설정
        panel.add(ChangeNick);

        JTextField NicknameField = new JTextField();
        NicknameField.setBounds(140, 110, 130, 20); // 위치 설정
        NicknameField.setBackground(Color.DARK_GRAY); // 배경색 다크그레이
        NicknameField.setForeground(Color.WHITE); // 텍스트 색상 흰색
        NicknameField.setCaretColor(Color.WHITE); // 커서 색상 흰색
        NicknameField.setBorder(BorderFactory.createEmptyBorder()); // 테두리 없음
        panel.add(NicknameField);

        // 비밀번호 설정 탭
        JLabel PasswordLabel = new JLabel("Password");
        PasswordLabel.setForeground(Color.WHITE);
        PasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        PasswordLabel.setBounds(10, 140, 200, 20);
        panel.add(PasswordLabel);

        // 현재 비밀번호
        JLabel CurrentPW = new JLabel("Current Password :");
        CurrentPW.setForeground(Color.GRAY);
        CurrentPW.setFont(new Font("Arial", Font.PLAIN, 12));
        CurrentPW.setBounds(20, 170, 130, 20); // 위치 설정
        panel.add(CurrentPW);

        // 현재 비밀번호 입력란
        JPasswordField CurrentPWField = new JPasswordField();
        CurrentPWField.setBounds(140, 169, 130, 19); // 입력란 위치
        CurrentPWField.setBackground(Color.DARK_GRAY); // 배경색 다크그레이
        CurrentPWField.setForeground(Color.WHITE); // 텍스트 색상 흰색
        CurrentPWField.setCaretColor(Color.WHITE); // 커서 색상 흰색
        CurrentPWField.setBorder(BorderFactory.createEmptyBorder()); // 테두리 제거
        panel.add(CurrentPWField);

        // 바꿀 비밀번호
        JLabel ChangePW = new JLabel("Password to change :");
        ChangePW.setForeground(Color.GRAY);
        ChangePW.setFont(new Font("Arial", Font.PLAIN, 12));
        ChangePW.setBounds(20, 190, 130, 20); // 위치 설정
        panel.add(ChangePW);

        // 바꿀 비밀번호 입력란
        JPasswordField PasswordField = new JPasswordField();
        PasswordField.setBounds(140, 191, 130, 19); // 입력란 위치
        PasswordField.setBackground(Color.DARK_GRAY); // 배경색 다크그레이
        PasswordField.setForeground(Color.WHITE); // 텍스트 색상 흰색
        PasswordField.setCaretColor(Color.WHITE); // 커서 색상 흰색
        PasswordField.setBorder(BorderFactory.createEmptyBorder()); // 테두리 제거
        panel.add(PasswordField);

        // 비밀번호 확인
        JLabel CheckPW = new JLabel("Check password :");
        CheckPW.setForeground(Color.GRAY);
        CheckPW.setFont(new Font("Arial", Font.PLAIN, 12));
        CheckPW.setBounds(20, 210, 130, 20); // 위치 설정
        panel.add(CheckPW);

        // 비밀번호 확인 입력란
        JPasswordField CheckPWField = new JPasswordField();
        CheckPWField.setBounds(140, 212, 130, 19); // 입력란 위치
        CheckPWField.setBackground(Color.DARK_GRAY); // 배경색 다크그레이
        CheckPWField.setForeground(Color.WHITE); // 텍스트 색상 흰색
        CheckPWField.setCaretColor(Color.WHITE); // 커서 색상 흰색
        CheckPWField.setBorder(BorderFactory.createEmptyBorder()); // 테두리 제거
        panel.add(CheckPWField);

        // 성별 설정 탭
        JLabel GenderLabel = new JLabel("Gender");
        GenderLabel.setForeground(Color.WHITE);
        GenderLabel.setFont(new Font("Arial", Font.BOLD, 14));
        GenderLabel.setBounds(10, 240, 200, 20);
        panel.add(GenderLabel);

        // 성별 선택지 패널
        JPanel genderPanel = new JPanel();
        genderPanel.setBounds(10, 260, 350, 20); // 위치와 크기 설정
        genderPanel.setBackground(Color.BLACK); // 배경색 설정
        genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0)); // 가로로 배치 (간격: 10px)

        // 선택지 1: Male
        maleCheckBox = new JCheckBox("Male");
        maleCheckBox.setForeground(Color.WHITE);
        maleCheckBox.setBackground(Color.BLACK); // 배경색 설정
        maleCheckBox.setFocusPainted(false); // 포커스 테두리 제거
        genderPanel.add(maleCheckBox);

        // 선택지 2: Female
        femaleCheckBox = new JCheckBox("Female");
        femaleCheckBox.setForeground(Color.WHITE);
        femaleCheckBox.setBackground(Color.BLACK); // 배경색 설정
        femaleCheckBox.setFocusPainted(false); // 포커스 테두리 제거
        genderPanel.add(femaleCheckBox);

        // 선택지 3: Other
        otherCheckBox = new JCheckBox("Other");
        otherCheckBox.setForeground(Color.WHITE);
        otherCheckBox.setBackground(Color.BLACK); // 배경색 설정
        otherCheckBox.setFocusPainted(false); // 포커스 테두리 제거
        genderPanel.add(otherCheckBox);

        // DB에서 성별 불러와야 함 그리고 체크박스에 투영

        // 체크박스 상태 관리 (중복 선택 방지)
        maleCheckBox.addActionListener(e -> {
            if (maleCheckBox.isSelected()) {
                femaleCheckBox.setSelected(false);
                otherCheckBox.setSelected(false);
            }
        });

        femaleCheckBox.addActionListener(e -> {
            if (femaleCheckBox.isSelected()) {
                maleCheckBox.setSelected(false);
                otherCheckBox.setSelected(false);
            }
        });

        otherCheckBox.addActionListener(e -> {
            if (otherCheckBox.isSelected()) {
                maleCheckBox.setSelected(false);
                femaleCheckBox.setSelected(false);
            }
        });

        // 패널에 추가
        panel.add(genderPanel);

        // 전화번호 설정 탭
        JLabel PhoneNumLabel = new JLabel("Phone number");
        PhoneNumLabel.setForeground(Color.WHITE);
        PhoneNumLabel.setFont(new Font("Arial", Font.BOLD, 14));
        PhoneNumLabel.setBounds(10, 300, 200, 20);
        panel.add(PhoneNumLabel);

        // 현재 폰 번호
        JLabel CurrentPhone = new JLabel("Current Phone :");
        CurrentPhone.setForeground(Color.GRAY);
        CurrentPhone.setFont(new Font("Arial", Font.PLAIN, 12));
        CurrentPhone.setBounds(20, 330, 130, 20); // 위치 설정
        panel.add(CurrentPhone);

        // DB에서 폰 번호 불러오기
        CurrentPhonefromDB = new JLabel("Current Phone :");
        CurrentPhonefromDB.setForeground(Color.GRAY);
        CurrentPhonefromDB.setFont(new Font("Arial", Font.PLAIN, 12));
        CurrentPhonefromDB.setBounds(140, 330, 130, 20); // 위치 설정
        panel.add(CurrentPhonefromDB);

        // 바꿀 번호
        JLabel ChangePhone = new JLabel("New phone number :");
        ChangePhone.setForeground(Color.GRAY);
        ChangePhone.setFont(new Font("Arial", Font.PLAIN, 12));
        ChangePhone.setBounds(20, 350, 130, 20); // 위치 설정
        panel.add(ChangePhone);

        // 폰 번호 입력란
        JTextField PhoneField = new JTextField();
        PhoneField.setBounds(140, 350, 130, 19); // 입력란 위치
        PhoneField.setBackground(Color.DARK_GRAY); // 배경색 다크그레이
        PhoneField.setForeground(Color.WHITE); // 텍스트 색상 흰색
        PhoneField.setCaretColor(Color.WHITE); // 커서 색상 흰색
        PhoneField.setBorder(BorderFactory.createEmptyBorder()); // 테두리 제거
        panel.add(PhoneField);

        // 닫기 버튼
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(150, 400, 100, 30);
        saveButton.setBackground(Color.BLACK);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(e -> {
            // 입력된 값 가져오기
            String newNickname = NicknameField.getText();
            String currentPassword = new String(CurrentPWField.getPassword());
            String newPassword = new String(PasswordField.getPassword());
            String confirmPassword = new String(CheckPWField.getPassword());
            String phoneNumber = PhoneField.getText();
        
            // 성별 가져오기
            String gender = null;
            if (maleCheckBox.isSelected()) {
                gender = "Male";
            } else if (femaleCheckBox.isSelected()) {
                gender = "Female";
            } else if (otherCheckBox.isSelected()) {
                gender = "Other";
            }
        
            // 유효성 검사
            if (newNickname.isEmpty() || currentPassword.isEmpty() || phoneNumber.isEmpty() || gender == null) {
                JOptionPane.showMessageDialog(this, "All fields must be filled, including gender.");
                return;
            }
        
            if (!newPassword.isEmpty() && !newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "New password and confirm password do not match.");
                return;
            }
        
            // 데이터베이스 업데이트 호출
            boolean updateSuccess = UserSettingsManager.updateUserSettings(
                    Integer.parseInt(userID), newNickname, currentPassword,
                    newPassword.isEmpty() ? null : newPassword, phoneNumber, gender
            );
        
            // 결과 처리
            if (updateSuccess) {
                JOptionPane.showMessageDialog(this, "Settings updated successfully!");
                dispose(); // 창 닫기
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update settings. Please check your inputs.");
            }
        });
        
        
        panel.add(saveButton);

        // 패널 추가
        add(panel);

        // 창 보이기
        setVisible(true);
    }

    private void loadUserData() {
        // 닉네임 가져오기
        String nickname = UserSettingsManager.getNickname(userID);
        if (nickname != null) {
            CurrentNickfromDB.setText(nickname);
        } else {
            CurrentNickfromDB.setText("No nickname found");
        }
    
        // 전화번호 가져오기
        String phoneNum = UserSettingsManager.getPhoneNum(userID);
        if (phoneNum != null) {
            CurrentPhonefromDB.setText(phoneNum);
        } else {
            CurrentPhonefromDB.setText("No phone number found");
        }
    
        // 성별 가져오기
        String gender = UserSettingsManager.getGender(userID);
        if (gender != null) {
            if ("Male".equalsIgnoreCase(gender)) {
                maleCheckBox.setSelected(true);
            } else if ("Female".equalsIgnoreCase(gender)) {
                femaleCheckBox.setSelected(true);
            } else if ("Other".equalsIgnoreCase(gender)) {
                otherCheckBox.setSelected(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No gender data found for the user.");
        }
    }
}
