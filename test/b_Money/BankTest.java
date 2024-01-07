package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

    /**
     * Test to verify the retrieval of bank names.
     */
    @Test
    public void testGetName() {
        assertEquals("SweBank", SweBank.getName());
        assertEquals("Nordea", Nordea.getName());
        assertEquals("DanskeBank", DanskeBank.getName());
    }

    /**
     * Test to validate the retrieval of bank currencies.
     */
    @Test
    public void testGetCurrency() {
        assertEquals(SEK, SweBank.getCurrency());
        assertEquals(SEK, Nordea.getCurrency());
        assertEquals(DKK, DanskeBank.getCurrency());
    }

    /**
     * Test to check account opening functionality.
     * @throws AccountExistsException If the account already exists.
     * @throws AccountDoesNotExistException If the account does not exist.
     */
    @Test
    public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
    	SweBank.openAccount("Alice");
    }

    /**
     * Test to verify deposit functionality into an account.
     * @throws AccountDoesNotExistException If the account does not exist.
     */
    @Test
    public void testDeposit() throws AccountDoesNotExistException {
        SweBank.deposit("Bob", new Money(100, SEK));
        assertEquals(100, SweBank.getBalance("Bob").intValue());
    }

    /**
     * Test to ensure withdrawal functionality from an account.
     * @throws AccountDoesNotExistException If the account does not exist.
     */
	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(1000, SEK));
        SweBank.withdraw("Bob", new Money(600, SEK));
        assertEquals(400, SweBank.getBalance("Bob").intValue());
	}
	
    /**
     * Test to validate the retrieval of account balances.
     * @throws AccountDoesNotExistException If the account does not exist.
     */
    @Test
    public void testGetBalance() throws AccountDoesNotExistException {
        assertEquals(0, SweBank.getBalance("Ulrika").intValue());
    }
	
    /**
     * Test to ensure successful transfer between accounts in different banks.
     * @throws AccountDoesNotExistException If the account does not exist.
     */
    @Test
    public void testTransfer() throws AccountDoesNotExistException {
    	SweBank.deposit("Bob", new Money(1000, SEK));
        SweBank.transfer("Bob", DanskeBank, "Gertrud", new Money(300, SEK));
        assertEquals(700, SweBank.getBalance("Bob").intValue());
        assertEquals(225, DanskeBank.getBalance("Gertrud").intValue());
    }
	
    /**
     * Test to check the functionality of timed payments.
     * @throws AccountDoesNotExistException If the account does not exist.
     */
    @Test
    public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(300, SEK));
		SweBank.deposit("Bob", new Money(600, SEK));
        SweBank.addTimedPayment("Bob", "1", 3, 0, new Money(250, SEK), SweBank, "Ulrika");
        SweBank.tick();
        assertEquals(550, SweBank.getBalance("Ulrika").intValue());
        assertEquals(350, SweBank.getBalance("Bob").intValue());
    }
}
