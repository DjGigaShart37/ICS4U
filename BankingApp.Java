import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankingAppGUI extends JFrame {
    private BankAccount selectedAccount;

    private JButton depositButton;
    private JButton withdrawButton;
    private JButton applyInterestButton;
    private JButton backButton;
    private JLabel balanceLabel;

    public BankingAppGUI(BankAccount account) {
        this.selectedAccount = account;

        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        applyInterestButton = new JButton("Apply Interest");
        backButton = new JButton("Back");
        balanceLabel = new JLabel("Balance: $" + selectedAccount.getBalance());

        setLayout(new GridLayout(5, 1));
        add(depositButton);
        add(withdrawButton);
        add(applyInterestButton);
        add(backButton);
        add(balanceLabel);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter deposit amount:");
                double amount = Double.parseDouble(input);
                selectedAccount.deposit(amount);
                updateBalanceLabel();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter withdrawal amount:");
                double amount = Double.parseDouble(input);
                selectedAccount.withdraw(amount);
                updateBalanceLabel();
            }
        });
        applyInterestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedAccount instanceof HighInterestSavingsAccount) {
                    ((HighInterestSavingsAccount) selectedAccount).applyInterest();
                    updateBalanceLabel();
                } else {
                    JOptionPane.showMessageDialog(null, "This account type does not support applying interest.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                showAccountSelection();
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + selectedAccount.getBalance());
    }
    private void showAccountSelection() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BankAccount highInterestSavingsAccount = new HighInterestSavingsAccount(1000);
                BankAccount studentCheckingAccount = new StudentCheckingAccount(500);
                BankAccount generalCheckingAccount = new GeneralCheckingAccount(1000);

                String[] options = {"High Interest Savings Account", "Student Checking Account", "General Checking Account"};
                int choice = JOptionPane.showOptionDialog(null, "Select Account Type", "Account Type",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                switch (choice) {
                    case 0:
                        new BankingAppGUI(highInterestSavingsAccount);
                        break;
                    case 1:
                        new BankingAppGUI(studentCheckingAccount);
                        break;
                    case 2:
                        new BankingAppGUI(generalCheckingAccount);
                        break;
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BankAccount highInterestSavingsAccount = new HighInterestSavingsAccount(1000);
                BankAccount studentCheckingAccount = new StudentCheckingAccount(500);
                BankAccount generalCheckingAccount = new GeneralCheckingAccount(1000);

                String[] options = {"High Interest Savings Account", "Student Checking Account", "General Checking Account"};
                int choice = JOptionPane.showOptionDialog(null, "Select Account Type", "Account Type",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                switch (choice) {
                    case 0:
                        new BankingAppGUI(highInterestSavingsAccount);
                        break;
                    case 1:
                        new BankingAppGUI(studentCheckingAccount);
                        break;
                    case 2:
                        new BankingAppGUI(generalCheckingAccount);
                        break;
                }
            }
        });
    }
}

