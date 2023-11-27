import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUp implements ActionListener {
    Window window;
    JButton nextButton;
    JTextField firstNameTextBox;
    JTextField lastNameTextBox;
    JTextField addressTextBox;
    JTextField phoneNumberTextBox;
    JTextField userNameTextBox;
    JPasswordField passwordTextBox;
    JPasswordField confirmPasswordTextBox;

    SignUp() {
        // Title label
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Create Account");
        titleLabel.setBounds(145, 5, 400, 35);
        titleLabel.setFont(new Font("Serif Sans", Font.BOLD, 30));

        // firstname label
        JLabel firstNameLabel = new JLabel();
        firstNameLabel.setText("FIRST NAME");
        firstNameLabel.setBounds(20, 70, 400, 50);
        firstNameLabel.setFont(new Font("Serif Sans", Font.PLAIN, 16));

        firstNameTextBox = new JTextField();
        firstNameTextBox.setFont(new Font("Serif Sans", Font.PLAIN, 15));
        firstNameTextBox.setPreferredSize(new Dimension(310, 30));
        firstNameTextBox.setBounds(20, 110, 460, 30);

        // last name label
        JLabel lastNameLabel = new JLabel();
        lastNameLabel.setText("LAST NAME");
        lastNameLabel.setBounds(20, 135, 400, 50);
        lastNameLabel.setFont(new Font("Serif Sans", Font.PLAIN, 16));

        lastNameTextBox = new JTextField();
        lastNameTextBox.setFont(new Font("Serif Sans", Font.PLAIN, 15));
        lastNameTextBox.setPreferredSize(new Dimension(310, 30));
        lastNameTextBox.setBounds(20, 175, 460, 30);

        // address label
        JLabel addressLabel = new JLabel();
        addressLabel.setText("ADDRESS");
        addressLabel.setBounds(20, 200, 400, 50);
        addressLabel.setFont(new Font("Serif Sans", Font.PLAIN, 16));

        addressTextBox = new JTextField();
        addressTextBox.setFont(new Font("Serif Sans", Font.PLAIN, 15));
        addressTextBox.setPreferredSize(new Dimension(310, 30));
        addressTextBox.setBounds(20, 240, 460, 30);

        // phone Number label
        JLabel phoneNumberLabel = new JLabel();
        phoneNumberLabel.setText("PHONE NUMBER");
        phoneNumberLabel.setBounds(20, 265, 400, 50);
        phoneNumberLabel.setFont(new Font("Serif Sans", Font.PLAIN, 16));

        phoneNumberTextBox = new JTextField();
        phoneNumberTextBox.setFont(new Font("Serif Sans", Font.PLAIN, 15));
        phoneNumberTextBox.setPreferredSize(new Dimension(310, 30));
        phoneNumberTextBox.setBounds(20, 305, 460, 30);

        // userName Label
        JLabel userNameLabel = new JLabel();
        userNameLabel.setText("USERNAME");
        userNameLabel.setBounds(20, 330, 400, 50);
        userNameLabel.setFont(new Font("Serif Sans", Font.PLAIN, 16));

        userNameTextBox = new JTextField();
        userNameTextBox.setFont(new Font("Serif Sans", Font.PLAIN, 15));
        userNameTextBox.setPreferredSize(new Dimension(310, 30));
        userNameTextBox.setBounds(20, 370, 460, 30);

        // password Label
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("PASSWORD");
        passwordLabel.setBounds(20, 395, 400, 50);
        passwordLabel.setFont(new Font("Serif Sans", Font.PLAIN, 16));

        passwordTextBox = new JPasswordField();
        passwordTextBox.setFont(new Font("Serif Sans", Font.PLAIN, 15));
        passwordTextBox.setPreferredSize(new Dimension(310, 30));
        passwordTextBox.setBounds(20, 435, 460, 30);

        // confrim Password label
        JLabel confirmPasswordLabel = new JLabel();
        confirmPasswordLabel.setText("CONFIRM PASSWORD");
        confirmPasswordLabel.setBounds(20, 460, 400, 50);
        confirmPasswordLabel.setFont(new Font("Serif Sans", Font.PLAIN, 16));

        confirmPasswordTextBox = new JPasswordField();
        confirmPasswordTextBox.setFont(new Font("Serif Sans", Font.PLAIN, 15));
        confirmPasswordTextBox.setPreferredSize(new Dimension(310, 30));
        confirmPasswordTextBox.setBounds(20, 500, 460, 30);

        // Next Button
        nextButton = new JButton();
        nextButton.setText("NEXT");
        nextButton.setBounds(180, 560, 150, 30);
        nextButton.addActionListener(this);
        // gridPanel
        JPanel gridPanel = new JPanel();
        // gridPanel.setBackground(Color.DARK_GRAY);
        gridPanel.setLayout(null);
        gridPanel.setBounds(45, 50, 500, 600);
        gridPanel.add(titleLabel);
        gridPanel.add(firstNameLabel);
        gridPanel.add(firstNameTextBox);
        gridPanel.add(lastNameLabel);
        gridPanel.add(lastNameTextBox);
        gridPanel.add(addressLabel);
        gridPanel.add(addressTextBox);
        gridPanel.add(phoneNumberLabel);
        gridPanel.add(phoneNumberTextBox);
        gridPanel.add(userNameLabel);
        gridPanel.add(userNameTextBox);
        gridPanel.add(passwordLabel);
        gridPanel.add(passwordTextBox);
        gridPanel.add(confirmPasswordLabel);
        gridPanel.add(confirmPasswordTextBox);
        gridPanel.add(nextButton);

        // Window Frame
        window = new Window();
        window.setSize(600, 750);
        window.setTitle("Create Account");
        window.add(gridPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            if (firstNameTextBox.getText().length() == 0 || lastNameTextBox.getText().length() == 0 ||
                    addressTextBox.getText().length() == 0 || phoneNumberTextBox.getText().length() == 0 ||
                    passwordTextBox.getPassword().toString().length() == 0
                    || confirmPasswordTextBox.getPassword().toString().length() == 0) {
                JOptionPane.showMessageDialog(null, "Please Fill out all fields", "Blank fields",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                window.setVisible(false);
                new AccountSetupGUI(userNameTextBox.getText().toString(),
                        ((JTextField) passwordTextBox).getText().toString(), lastNameTextBox.getText().toString(),
                        firstNameTextBox.getText().toString(), addressTextBox.getText().toString(),
                        phoneNumberTextBox.getText().toString());
            }

        }
    }

}