import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ExpenseManager extends JFrame {
    private static final String FILE_NAME = "Expenses.dat";
    private JLabel remainingBalanceLabel;
    private JLabel savingsLabel;
    private JLabel allowanceLabel;

    private List<Expense> expenses;
    private DefaultTableModel expenseTableModel;
    private JTable expenseTable;
    private Accounts currentUser; // Added field for the current user

    public ExpenseManager(Accounts user) {
        this.currentUser = user;
        expenses = loadExpenses();
        expenseTableModel = new DefaultTableModel(new Object[] { "Date", "Category", "Item", "Price" }, 0);
        expenseTable = new JTable(expenseTableModel);

        remainingBalanceLabel = new JLabel();
        remainingBalanceLabel.setBounds(20, 370, 400, 30);

        savingsLabel = new JLabel();
        savingsLabel.setBounds(20, 400, 400, 30);

        allowanceLabel = new JLabel();
        allowanceLabel.setBounds(20, 360, 400, 30);
        // Create GUI components
        JButton addButton = new JButton("Add Expense");
        JButton updateButton = new JButton("Update Expense");
        JButton removeButton = new JButton("Remove Expense");
        JButton reportButton = new JButton("Generate Report");
        JButton exitButton = new JButton("Exit");

        // Set button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateExpense();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeExpense();
            }
        });

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitApplication();
            }
        });

        // Create layout
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(reportButton);
        buttonPanel.add(exitButton);

        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(expenseTable), BorderLayout.CENTER);

        setTitle("Expense Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // Update the table with loaded expenses
        updateExpenseTable();
    }

    private void addExpense() {
        JPanel addExpensePanel = new JPanel(new GridLayout(4, 2));

        JTextField dateField = new JTextField();
        JComboBox<String> categoryComboBox = new JComboBox<>(
                new String[] { "Food", "House", "Bills", "Transportation", "Others" });
        JTextField itemField = new JTextField();
        JTextField priceField = new JTextField();

        addExpensePanel.add(new JLabel("Date:"));
        addExpensePanel.add(dateField);
        addExpensePanel.add(new JLabel("Category:"));
        addExpensePanel.add(categoryComboBox);
        addExpensePanel.add(new JLabel("Item:"));
        addExpensePanel.add(itemField);
        addExpensePanel.add(new JLabel("Price:"));
        addExpensePanel.add(priceField);

        int result = JOptionPane.showConfirmDialog(this, addExpensePanel, "Add Expense", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(); // Use the current date

                String category = Objects.requireNonNull(categoryComboBox.getSelectedItem()).toString();
                String item = itemField.getText();
                double price = Double.parseDouble(priceField.getText());

                Expense expense = new Expense(date, category, item, price);
                expenses.add(expense);
                updateExpenseTable();
                saveExpenses();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid data.");
            }
        }
    }

    private void updateExpense() {
        int selectedIndex = expenseTable.getSelectedRow();
        if (selectedIndex >= 0) {
            JPanel updateExpensePanel = new JPanel(new GridLayout(4, 2));

            JTextField dateField = new JTextField();
            JComboBox<String> categoryComboBox = new JComboBox<>(
                    new String[] { "Food", "House", "Bills", "Transportation", "Others" });
            JTextField itemField = new JTextField();
            JTextField priceField = new JTextField();

            Expense selectedExpense = expenses.get(selectedIndex);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateField.setText(dateFormat.format(selectedExpense.getDate()));
            categoryComboBox.setSelectedItem(selectedExpense.getCategory());
            itemField.setText(selectedExpense.getItem());
            priceField.setText(String.valueOf(selectedExpense.getPrice()));

            updateExpensePanel.add(new JLabel("Date:"));
            updateExpensePanel.add(dateField);
            updateExpensePanel.add(new JLabel("Category:"));
            updateExpensePanel.add(categoryComboBox);
            updateExpensePanel.add(new JLabel("Item:"));
            updateExpensePanel.add(itemField);
            updateExpensePanel.add(new JLabel("Price:"));
            updateExpensePanel.add(priceField);

            int result = JOptionPane.showConfirmDialog(this, updateExpensePanel, "Update Expense",
                    JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    // Date is not updated for the sake of simplicity
                    String category = Objects.requireNonNull(categoryComboBox.getSelectedItem()).toString();
                    String item = itemField.getText();
                    double price = Double.parseDouble(priceField.getText());

                    selectedExpense.setCategory(category);
                    selectedExpense.setItem(item);
                    selectedExpense.setPrice(price);

                    updateExpenseTable();
                    saveExpenses();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid data.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No expense selected. Please select an expense to update.");
        }
    }

    private void removeExpense() {
        int selectedIndex = expenseTable.getSelectedRow();
        if (selectedIndex >= 0) {
            expenses.remove(selectedIndex);
            updateExpenseTable();
            saveExpenses();
        } else {
            JOptionPane.showMessageDialog(this, "No expense selected. Please select an expense to remove.");
        }
    }

    private void generateReport() {
        StringBuilder report = new StringBuilder("Expense Report:\n");
        double totalExpense = 0;

        for (Expense expense : expenses) {
            report.append("Date: ").append(new SimpleDateFormat("yyyy-MM-dd").format(expense.getDate())).append(", ");
            report.append("Category: ").append(expense.getCategory()).append(", ");
            report.append("Item: ").append(expense.getItem()).append(", ");
            report.append("Price: ").append(expense.getPrice()).append("\n");

            // Calculate total expenses
            totalExpense += expense.getPrice();
        }

        // Calculate remaining balance and savings
        double remainingBalance = currentUser.getAllowance() - totalExpense;
        double savings = currentUser.getAllowance() - currentUser.getExpenditures();

        // Display remaining balance, savings, and allowance in the report
        report.append("\nTotal Expense: ").append(totalExpense);
        report.append("\nRemaining Balance: ").append(remainingBalance);
        report.append("\nSavings: ").append(savings);
        report.append("\nAllowance: ").append(currentUser.getAllowance()); // Add this line

        // Display report
        JTextArea reportArea = new JTextArea(report.toString());
        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "Expense Report", JOptionPane.PLAIN_MESSAGE);

        // Save report to a text file
        try (PrintWriter writer = new PrintWriter(new FileWriter("ExpenseReport.txt"))) {
            writer.println(report.toString());
            JOptionPane.showMessageDialog(this, "Report generated and saved to ExpenseReport.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "An error occurred while saving the report.");
        }
    }

    private void updateExpenseTable() {
        expenseTableModel.setRowCount(0);
        double totalExpense = 0;

        for (Expense expense : expenses) {
            Object[] rowData = {
                    new SimpleDateFormat("yyyy-MM-dd").format(expense.getDate()),
                    expense.getCategory(),
                    expense.getItem(),
                    expense.getPrice()
            };
            expenseTableModel.addRow(rowData);

            // Calculate total expenses
            totalExpense += expense.getPrice();
        }

        // Calculate remaining balance and savings
        double remainingBalance = currentUser.getAllowance() - totalExpense;
        double savings = currentUser.getAllowance() - currentUser.getExpenditures();

        // Update the labels
        remainingBalanceLabel.setText("Remaining Balance: " + remainingBalance);
        savingsLabel.setText("Savings: " + savings);
    }

    private List<Expense> loadExpenses() {
        List<Expense> loadedExpenses = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            loadedExpenses = (List<Expense>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous expenses found. Starting with an empty list.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedExpenses;
    }

    private void saveExpenses() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(expenses);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while saving expenses.");
        }
    }

    private void exitApplication() {
        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            saveExpenses(); // Save expenses before exiting
            System.exit(0);
        }
    }

    private static class Expense implements Serializable {
        private Date date;
        private String category;
        private String item;
        private double price;

        public Expense(Date date, String category, String item, double price) {
            this.date = date;
            this.category = category;
            this.item = item;
            this.price = price;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
