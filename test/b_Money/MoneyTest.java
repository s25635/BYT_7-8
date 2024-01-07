package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	 /**
     * Test to verify the retrieval of the amount in a Money object.
     */
    @Test
    public void testGetAmount() {
        assertEquals(Integer.valueOf(10000), SEK100.getAmount());
    }

    /**
     * Test to validate the retrieval of the currency in a Money object.
     */
    @Test
    public void testGetCurrency() {
        assertEquals(SEK, SEK100.getCurrency());
    }

    /**
     * Test to ensure correct string representation of Money object.
     */
    @Test
    public void testToString() {
        assertEquals("100,0 SEK", SEK100.toString());
    }

    /**
     * Test to check the global value of a Money object.
     */
    @Test
    public void testGlobalValue() {
        assertEquals(Integer.valueOf(1500), SEK100.universalValue());
    }

    /**
     * Test to verify equality between two Money objects.
     */
    @Test
    public void testEqualsMoney() {
        assertTrue(SEK100.equals(SEK100));
        assertFalse(EUR10.equals(SEK100));
    }

    /**
     * Test to check the addition of two Money objects.
     */
    @Test
    public void testAdd() {
        Money addedMoney = SEK100.add(EUR10);
        assertEquals(Integer.valueOf(20000), addedMoney.getAmount());
        assertEquals(SEK, addedMoney.getCurrency());
    }

    /**
     * Test to verify the subtraction of two Money objects.
     */
    @Test
    public void testSub() {
        Money subtractedMoney = SEK100.sub(EUR10);
        assertEquals(Integer.valueOf(0), subtractedMoney.getAmount());
        assertEquals(SEK, subtractedMoney.getCurrency());
    }

    /**
     * Test to check if a Money object has a zero amount.
     */
    @Test
    public void testIsZero() {
        assertTrue(SEK0.isZero());
        assertFalse(SEK100.isZero());
    }

    /**
     * Test to verify negation of a Money object.
     */
    @Test
    public void testNegate() {
        assertEquals(Integer.valueOf(-10000), SEK100.negate().getAmount());
    }

    /**
     * Test to ensure correct comparison between Money objects.
     */
    @Test
    public void testCompareTo() {
        assertTrue(SEK100.compareTo(SEK200) < 0);
        assertTrue(SEK200.compareTo(SEK100) > 0);
        assertTrue(SEK100.compareTo(SEK100) == 0);
    }
}
