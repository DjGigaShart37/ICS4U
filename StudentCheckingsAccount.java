import javax.swing.*;

class StudentCheckingAccount extends BankAccount {
    private double overdraftLimit;

    public StudentCheckingAccount(double initialBalance) {
        super("Student Checking Account", initialBalance);
        this.overdraftLimit = 100;
    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() - amount >= -overdraftLimit) {
            super.withdraw(amount);
        } else {
            JOptionPane.showMessageDialog(null, "Exceeded overdraft limit!");
        }
    }
}
