public class BankAccount {

    private boolean isBlocked = false;
    private Integer amount;
    private String currency;

    private final String firstName;
    private final String secondName;

    public BankAccount(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public void block() {
        this.isBlocked = true;
    }

    public void activate(String currency) {
        this.amount = 0;
        this.currency = currency;
    }

    public Integer getAmount() {
        if (amount == null) {
            throw new IllegalStateException("Счёт не активирован.");
        }
        return this.amount;
    }

    public String getCurrency() {
        return currency;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public String[] getFullName() {
        return new String[]{firstName, secondName};
    }

    public void withdraw(int amount) {
        if (this.amount == null) {
            throw new IllegalStateException("Счёт не активирован.");
        }
        if (isBlocked) {
            throw new IllegalStateException("Счёт заблокирован.");
        }
        if (amount > this.amount) {
            throw new IllegalStateException("Нельзя снять больше, чем есть на счете.");
        }
        this.amount -= amount;
    }

    public void transfer(BankAccount otherBankAccount, int transferAmount) {
        if (otherBankAccount.amount == null) {
            throw new IllegalStateException("Счёт получателя не активирован.");
        }
        if (otherBankAccount.isBlocked) {
            throw new IllegalStateException("Счёт получателя заблокирован.");
        }

        withdraw(transferAmount);
        otherBankAccount.setAmount(otherBankAccount.getAmount() + transferAmount);
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}