package projectCOE891.ISP;

import static org.junit.Assert.*;

import org.junit.Test;
import org.joda.time.Period;
import org.joda.time.PeriodType;

public class TestPeriod {
	/*
	 * | Partition        | B1       		 | B2            | B3        | B4          |
	 * |------------------|------------------|---------------|-----------|-------------|
	 * | Input String 	  | Valid ISO format | Invalid format|Null input |Empty string |
	 */
	
	@Test
    public void testParse_ValidISOFull() {
        Period period = Period.parse("P2Y3M4DT5H6M7S");
        assertEquals(2, period.getYears());
        assertEquals(3, period.getMonths());
        assertEquals(4, period.getDays());
        assertEquals(5, period.getHours());
        assertEquals(6, period.getMinutes());
        assertEquals(7, period.getSeconds());
    }

    @Test
    public void testParse_OnlyDays() {
        Period period = Period.parse("P5D");
        assertEquals(5, period.getDays());
        assertEquals(0, period.getYears());
    }

    @Test
    public void testParse_ZeroPeriod() {
        Period period = Period.parse("PT0S");
        assertEquals(Period.ZERO, period);
    }

    @Test
    public void testParse_WeeksOnly() {
        Period period = Period.parse("P1W");
        assertEquals(1, period.getWeeks());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testParse_EmptyString() {
        Period.parse("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testParse_InvalidFormat() {
        Period.parse("2 years 3 months");
    }

    @Test(expected=NullPointerException.class)
    public void testParse_NullString() {
        Period.parse(null);
    }
    
    // --------------------------
	/*
	 * | Partition        | B1       		 | B2            | B3        | B4               |
	 * |------------------|------------------|---------------|-----------|------------------|
	 * | Millis Time 	  | >0				 | <0			 | = 0		 |with other fields |
	 */
    
    
    @Test
    public void testGetMillis_Positive() {
        Period period = new Period().withMillis(123);
        assertEquals(123, period.getMillis());
    }

    @Test
    public void testGetMillis_Zero() {
        Period period = new Period();
        assertEquals(0, period.getMillis());
    }

    @Test
    public void testGetMillis_Negative() {
        Period period = new Period().withMillis(-456);
        assertEquals(-456, period.getMillis());
    }

    @Test
    public void testGetMillis_UnsupportedPeriodType() {
        Period period = new Period(0, PeriodType.yearMonthDay()); // no millis
        assertEquals(0, period.getMillis());
    }

    @Test
    public void testGetMillis_WithOtherFields() {
        Period period = new Period().withDays(10).withSeconds(1).withMillis(500);
        assertEquals(500, period.getMillis());
    }
}