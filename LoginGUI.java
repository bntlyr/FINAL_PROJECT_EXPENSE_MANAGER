import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI implements ActionListener {
    // global declaration for button and textfield
    Window window;
    JTextField userNameTextBox;
    JPasswordField passwordTextBox;
    JCheckBox showPassword;
    JButton signupButton;
    JButton loginButton;
    private Accounts currentUser;

    public LoginGUI() {
        // titleLabel
        JLabel titleLabel = new JLabel();
        titleLabel.setText("EXPENSE MANAGER");
        titleLabel.setBounds(45, 40, 400, 100);
        titleLabel.setFont(new Font("Serif Sans", Font.BOLD, 30));

        // userNameLabel
        JLabel userNameLabel = new JLabel();
        userNameLabel.setText("USERNAME");
        userNameLabel.setBounds(45, 140, 400, 50);
        userNameLabel.setFont(new Font("Serif Sans", Font.PLAIN, 14));

        // userName TextField
        userNameTextBox = new JTextField();
        userNameTextBox.setFont(new Font("Serif Sans", Font.PLAIN, 15));
        userNameTextBox.setPreferredSize(new Dimension(310, 30));
        userNameTextBox.setBounds(45, 180, 310, 30);

        // PasswordLabel
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("PASSWORD");
        passwordLabel.setBounds(45, 230, 400, 50);
        passwordLabel.setFont(new Font("Serif Sans", Font.PLAIN, 14));

        // password TextField
        passwordTextBox = new JPasswordField();
        passwordTextBox.setFont(new Font("Serif Sans", Font.PLAIN, 15));
        passwordTextBox.setPreferredSize(new Dimension(310, 30));
        passwordTextBox.setBounds(45, 270, 310, 30);

        // checkbox
        showPassword = new JCheckBox();
        showPassword.setText("SHOW PASSWORD");
        showPassword.setFocusable(false);
        showPassword.setBounds(45, 300, 190, 25);
        showPassword.setFont(new Font("Serif Sans", Font.PLAIN, 9));
        showPassword.addActionListener(new ActionListener() { // listener for check box
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == showPassword) {
                    if (showPassword.isSelected()) {
                        passwordTextBox.setEchoChar((char) 0);
                    } else {
                        passwordTextBox.setEchoChar('*');
                    }
                }
            }
        });

        // Signup Button
        signupButton = new JButton();
        signupButton.setText("SIGN-UP");
        signupButton.setBounds(45, 340, 150, 25);
        signupButton.setFont(new Font("Serif Sans", Font.BOLD, 15));
        signupButton.addActionListener(new ActionListener() { // listener for signup Button
            @Override
            public void actionPerformed(ActionEvent e) {
                // signup button
                if (e.getSource() == signupButton) {
                    window.setVisible(false);
                    new SignUp();
                }
            }
        });

        // Login Button
        loginButton = new JButton();
        loginButton.setText("LOGIN");
        loginButton.setBounds(205, 340, 150, 25);
        loginButton.setFont(new Font("Serif Sans", Font.BOLD, 15));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loginButton) {
                    if (userNameTextBox.getText().length() == 0
                            || passwordTextBox.getPassword().toString().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Please Fill out all fields", "Blank fields",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // check if userName and Passwords match
                        AccountsOperations userAccounts = new AccountsOperations();
                        currentUser = userAccounts.validateAccounts(userNameTextBox.getText(),
                                new String(passwordTextBox.getPassword()));

                        if (currentUser != null) {
                            window.setVisible(false);
                            new ExpenseManager(currentUser);
                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect UserName or Password", "Wrong Credentials",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        });

        // gridPanel
        JPanel gridPanel = new JPanel();
        // gridPanel.setBackground(Color.DARK_GRAY);
        gridPanel.setLayout(null);
        gridPanel.setBounds(45, 30, 400, 500);
        gridPanel.add(titleLabel);
        gridPanel.add(userNameLabel);
        gridPanel.add(userNameTextBox);
        gridPanel.add(passwordLabel);
        gridPanel.add(passwordTextBox);
        gridPanel.add(showPassword);
        gridPanel.add(signupButton);
        gridPanel.add(loginButton);

        // Window Frame
        window = new Window();
        window.add(gridPanel);
        window.setTitle("LOGIN PAGE");
        window.setSize(500, 600);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public Accounts getCurrentUser() {
        return currentUser;
    }

    public java.awt.Window getWindow() {
        return window;
    }

}