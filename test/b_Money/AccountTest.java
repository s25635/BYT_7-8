package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
    /**
     * Test to check addition and removal of timed payments.
     */
	@Test
	public void testAddRemoveTimedPayment() {
		assertFalse(testAccount.timedPaymentExists("1"));
        testAccount.addTimedPayment("1", 3, 6, new Money(500, SEK), SweBank, "Alice");
        assertTrue(testAccount.timedPaymentExists("1"));
        testAccount.removeTimedPayment("1");
        assertFalse(testAccount.timedPaymentExists("1"));
	}
	
	 /**
     * Test to validate the functionality of timed payments.
     * @throws AccountDoesNotExistException If the account does not exist.
     */
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		
		testAccount.addTimedPayment("1", 3, 6, new Money(500, SEK), SweBank, "Alice");
		for (int i = 0; i < 30; i++) {
            testAccount.tick();
        }
        assertEquals(1003000, SweBank.getBalance("Alice").intValue());
	}

	 /**
     * Test to check account withdrawal functionality.
     */
    @Test
    public void testAddWithdraw() {
        testAccount.withdraw(new Money(5000000, SEK)); // Withdraw money from the account
        assertEquals(5000000, testAccount.getBalance().getAmount().intValue()); // Check if withdrawal worked
    }
	
    /**
     * Test to verify the retrieval of the account balance.
     */
    @Test
    public void testGetBalance() {
        Money expectedBalance = new Money(10000000, SEK);
        assertEquals(expectedBalance.getAmount(), testAccount.getBalance().getAmount()); // Check balance
    }
}
