public class HighInterestSavingsAccount extends BankAccount {
    private double interestRate;

    public HighInterestSavingsAccount(double initialBalance) {
        super("High Interest Savings Account", initialBalance);
        this.interestRate = 0.05;
    }

    public void applyInterest() {
        super.deposit(getBalance() * interestRate);
    }
}
