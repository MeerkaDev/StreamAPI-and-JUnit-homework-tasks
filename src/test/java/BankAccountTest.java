import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    // Привмеры
    @Test
    void shouldNotBeBlockedWhenCreated() {
        BankAccount account = new BankAccount("a", "b");
        assertFalse(account.isBlocked());
    }

    @Test
    public void shouldReturnZeroAmountAfterActivation() {
        BankAccount account = new BankAccount("a", "b");
        account.activate("KZT");
        assertEquals(0, account.getAmount());
        assertEquals("KZT", account.getCurrency());
    }

    // Мои тесты
    @Test
    public void shouldBeBlockedAfterBlockIsCalled() {
        BankAccount account = new BankAccount("a", "b");
        account.block();
        assertTrue(account.isBlocked());
    }

    @Test
    public void shouldReturnFirstNameThenSecondName() {
        BankAccount account = new BankAccount("a", "b");
        assertArrayEquals(new String[]{"a", "b"}, account.getFullName());
    }

    @Test
    public void shouldReturnNullAmountWhenNotActive() {
        BankAccount account = new BankAccount("a", "b");

        String expectedMessage = "Счёт не активирован.";

        Executable executable = () -> account.getAmount();

        IllegalStateException exception = assertThrows(IllegalStateException.class, executable);

        assertEquals(expectedMessage, exception.getMessage());
        assertNull(account.getCurrency());
    }

    // Добавил
    @Test
    public void shouldReduceAmountWhenWithdrawEnoughAmount() {
        BankAccount account = new BankAccount("a", "b");
        account.activate("KZT");
        account.setAmount(2500);

        int expectedNewAmount = 2000;
        account.withdraw(500);

        assertEquals(expectedNewAmount, account.getAmount());
    }

    @Test
    public void shouldReturnExceptionWhenWithdrawNotActive() {
        BankAccount account = new BankAccount("a", "b");

        String expectedMessage = "Счёт не активирован.";

        Executable executable = () -> account.withdraw(500);

        IllegalStateException exception = assertThrows(IllegalStateException.class, executable);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenWithdrawIsBlocked() {
        BankAccount account = new BankAccount("a", "b");
        account.activate("KZT");
        account.block();

        String expectedMessage = "Счёт заблокирован.";

        Executable executable = () -> account.withdraw(500);

        IllegalStateException exception = assertThrows(IllegalStateException.class, executable);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenWithdrawHigherAmount() {
        BankAccount account = new BankAccount("a", "b");
        account.activate("KZT");
        account.setAmount(2500);

        String expectedMessage = "Нельзя снять больше, чем есть на счете.";

        Executable executable = () -> account.withdraw(3500);

        IllegalStateException exception = assertThrows(IllegalStateException.class, executable);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void shouldTransferAmount() {
        BankAccount account1 = new BankAccount("a", "b");
        account1.activate("KZT");
        account1.setAmount(2500);
        BankAccount account2 = new BankAccount("a", "b");
        account2.activate("KZT");
        account2.setAmount(2000);

        int expectedAmountAccount1 = 2000;
        int expectedAmountAccount2 = 2500;

        account1.transfer(account2, 500);

        assertEquals(expectedAmountAccount1, account1.getAmount());
        assertEquals(expectedAmountAccount2, account2.getAmount());
    }

    @Test
    public void shouldReturnExceptionWhenTransferToBlocked() {
        BankAccount account1 = new BankAccount("a", "b");
        account1.activate("KZT");
        account1.setAmount(2500);
        BankAccount account2 = new BankAccount("a", "b");
        account2.activate("KZT");
        account2.setAmount(2000);
        account2.block();

        String expectedMessage = "Счёт получателя заблокирован.";

        Executable executable = () -> account1.transfer(account2,500);

        IllegalStateException exception = assertThrows(IllegalStateException.class, executable);

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenTransferToNotActive() {
        BankAccount account1 = new BankAccount("a", "b");
        account1.activate("KZT");
        account1.setAmount(2500);
        BankAccount account2 = new BankAccount("a", "b");

        String expectedMessage = "Счёт получателя не активирован.";

        Executable executable = () -> account1.transfer(account2,500);

        IllegalStateException exception = assertThrows(IllegalStateException.class, executable);

        assertEquals(expectedMessage, exception.getMessage());
    }
}