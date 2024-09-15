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
}