package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

    /**
     * Test to verify the retrieval of currency names.
     */
	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
        assertEquals("DKK", DKK.getName());
        assertEquals("EUR", EUR.getName());
	}
	
    /**
     * Test to validate the retrieval of currency exchange rates.
     */
	@Test
	public void testGetRate() {
		assertEquals(Double.valueOf(0.15), SEK.getRate());
        assertEquals(Double.valueOf(0.20), DKK.getRate());
        assertEquals(Double.valueOf(1.5), EUR.getRate());
	}
	
	 /**
     * Test to ensure the modification of currency exchange rates.
     */
    @Test
    public void testSetRate() {
        SEK.setRate(0.25);
        assertEquals(Double.valueOf(0.25), SEK.getRate());
    }
	
    /**
     * Test to check the global value conversion of an amount in a currency.
     */
    @Test
    public void testGlobalValue() {
        assertEquals(Integer.valueOf(300), SEK.universalValue(2000));
        assertEquals(Integer.valueOf(900), EUR.universalValue(600));
    }
	
    /**
     * Test to validate the conversion of an amount from one currency to another.
     */
	@Test
    public void testValueInThisCurrency() {
        assertEquals(Integer.valueOf(266), SEK.valueInThisCurrency(200, DKK)); 
        assertEquals(Integer.valueOf(3), EUR.valueInThisCurrency(30, SEK));
	}

}
