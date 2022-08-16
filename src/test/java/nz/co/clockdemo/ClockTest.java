package nz.co.clockdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the clock function.
 */
public class ClockTest 
{

	/**
	 * Verify that the input cannot be null.
	 */
	@Test 
	public void testNotNull() {
        Clockface clockface = new Clockface();
        IllegalArgumentException thrown = 
        assertThrows(IllegalArgumentException.class, () -> clockface.clocke(null));
        assertTrue(thrown.getMessage().contains("time parameter must not be null"));
	}
	
	/**
	 * Verify that an exception is thrown if the input string is not 4 digits in length.
	 */
	@Test 
	public void testInputLength() {
        Clockface clockface = new Clockface();
        verifyStringLength(clockface, "000");
        verifyStringLength(clockface, "00000");
	}
	
	private void verifyStringLength(Clockface clockface, String time) {
        IllegalArgumentException thrown = 
        assertThrows(IllegalArgumentException.class, () -> clockface.clocke(time));
        assertTrue(thrown.getMessage().contains("invalid time string, should be formatted as <hhmm>"));		
	}
	
	/**
	 * Verify that and exception is thrown if the provided string is not a valid 24 hour time.
	 */
	@Test
	public void testValidTime() {
        Clockface clockface = new Clockface();
        verifyStringFormat(clockface, "abcd");
        verifyStringFormat(clockface, "123A");
        verifyStringFormat(clockface, "2400");
	}
	
	private void verifyStringFormat(Clockface clockface, String time) {
        IllegalArgumentException thrown = 
        assertThrows(IllegalArgumentException.class, () -> clockface.clocke(time));
        assertTrue(thrown.getMessage().contains("invalid time string, should be a valid 24 hour time"));		
	}
	
	
	/**
	 * Verify calculated angles.
	 */
    @Test
    public void testCorrectTime()
    {
        Clockface clockface = new Clockface();
        assertEquals(clockface.clocke("1230"), 165);
        assertEquals(clockface.clocke("0245"), 187.5);
        assertEquals(clockface.clocke("1445"), 187.5);
        assertEquals(clockface.clocke("0554"), 147);
        assertEquals(clockface.clocke("1754"), 147);
        assertEquals(clockface.clocke("1710"), 265);
        assertEquals(clockface.clocke("0000"), 0);
        assertEquals(clockface.clocke("1200"), 0);
    }
}
