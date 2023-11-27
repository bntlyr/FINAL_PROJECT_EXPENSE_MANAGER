import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AccountSetupGUI implements ActionListener {
    Window window;
    JButton submitButton;
    JTextField allowanceTextBox;
    JTextField expenditureTextBox;

    AccountSetupGUI(String userName, String password, String lastName, String firstName,
            String address, String phoneNumber) {
        // Title label
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Account Setup");
        titleLabel.setBounds(145, 10, 400, 50);
        titleLabel.setFont(new Font("Serif Sans", Font.BOLD, 30));

        // Allowance label
        JLabel allowanceLabel = new JLabel();
        allowanceLabel.setText("ALLOWANCE (INCOME)");
        allowanceLabel.setBounds(20, 90, 400, 50);
        allowanceLabel.setFont(new Font("Serif Sans", Font.PLAIN, 16));

        allowanceTextBox = new JTextField();
        allowanceTextBox.setFont(new Font("Serif Sans", Font.PLAIN, 15));
        allowanceTextBox.setPreferredSize(new Dimension(310, 30));
        allowanceTextBox.setBounds(20, 130, 460, 30);

        // Allowance label
        JLabel expenditureLabel = new JLabel();
        expenditureLabel.setText("BUDGET FOR EXPENSES");
        expenditureLabel.setBounds(20, 170, 400, 50);
        expenditureLabel.setFont(new Font("Serif Sans", Font.PLAIN, 16));

        expenditureTextBox = new JTextField();
        expenditureTextBox.setFont(new Font("Serif Sans", Font.PLAIN, 15));
        expenditureTextBox.setPreferredSize(new Dimension(310, 30));
        expenditureTextBox.setBounds(20, 210, 460, 30);

        // submit button
        submitButton = new JButton();
        submitButton.setText("NEXT");
        submitButton.setBounds(180, 300, 150, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == submitButton) {
                    if (allowanceTextBox.getText().length() == 0 || expenditureTextBox.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "Please Fill out all fields", "Blank fields",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        AccountsOperations userAccounts = new AccountsOperations();

                        System.out.println("Credentials Saved...");
                        double allowance = Double.parseDouble(allowanceTextBox.getText());
                        double expenditures = Double.parseDouble(expenditureTextBox.getText());

                        userAccounts.addAccounts(userName, password, lastName, firstName, address, phoneNumber,
                                allowance, expenditures, 0);
                        System.out.println("Account Setup Completed...");

                        // Retrieve the user account after setup
                        Accounts currentUser = userAccounts.validateAccounts(userName, password);

                        if (currentUser != null) {
                            // Close the setup window
                            window.setVisible(false);

                            // Create ExpenseManager with the user account
                            new ExpenseManager(currentUser);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error retrieving user account.", "Account Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        // grid Panel
        JPanel gridPanel = new JPanel();
        // gridPanel.setBackground(Color.DARK_GRAY);
        gridPanel.setLayout(null);
        gridPanel.setBounds(45, 50, 500, 550);
        gridPanel.add(titleLabel);
        gridPanel.add(allowanceLabel);
        gridPanel.add(allowanceTextBox);
        gridPanel.add(expenditureLabel);
        gridPanel.add(expenditureTextBox);
        gridPanel.add(submitButton);
        // window
        window = new Window();
        window.setSize(600, 500);
        window.setTitle("Account Setup");
        window.add(gridPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}