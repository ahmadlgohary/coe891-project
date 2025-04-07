package projectCOE891.ISP;

import static org.junit.Assert.*;

import org.junit.Test;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;


public class TestLocalDate{

	/*------------------------- plusWeeks() Tests --------------------------------*/
	/*
	 * Functionality-based approach ISP for the Constructor
 	 * |Partition|B1	  |B2	   |B3  |
 	 * |---------|--------|--------|----|
 	 * |Weeks	 |Positive|Negative|Zero|	 * 
	 * 
	 */
    @Test
    public void testPlusWeeks_positiveNumber() {
        LocalDate original = new LocalDate(2023, 4, 5);
        LocalDate expected = new LocalDate(2023, 4, 19);
        LocalDate result = original.plusWeeks(2);

        assertEquals(expected, result);
    }

    @Test
    public void testPlusWeeks_zeroWeeks() {
        LocalDate original = new LocalDate(2023, 4, 5);
        LocalDate result = original.plusWeeks(0);
        assertEquals(original, result);
    }

    @Test
    public void testPlusWeeks_negativeNumber() {
        LocalDate original = new LocalDate(2023, 4, 5);
        LocalDate expected = new LocalDate(2023, 3, 22);
        LocalDate result = original.plusWeeks(-2);

        assertEquals(expected, result);
    }

    @Test
    public void testPlusWeeks_acrossMonthBoundary() {
        LocalDate original = new LocalDate(2023, 1, 25);
        LocalDate expected = new LocalDate(2023, 2, 8); 
        LocalDate result = original.plusWeeks(2);

        assertEquals(expected, result);
    }

    @Test
    public void testPlusWeeks_leapYearForward() {
        LocalDate original = new LocalDate(2024, 2, 20);
        LocalDate expected = new LocalDate(2024, 3, 5); 
        LocalDate result = original.plusWeeks(2);

        assertEquals(expected, result);
    }

    @Test
    public void testPlusWeeks_leapYearBackward() {
        LocalDate original = new LocalDate(2024, 3, 5);
        LocalDate expected = new LocalDate(2024, 2, 20);
        LocalDate result = original.plusWeeks(-2);

        assertEquals(expected, result);
    }

	/*------------------------- withDayOfMonth() Tests --------------------------------*/
	/*
	 * Functionality-based approach ISP for the Constructor
 	 * |Partition|B1	  |B2	   |B3  |
 	 * |---------|--------|--------|----|
 	 * |Day		 |Positive|Negative|Zero|	 * 
	 * 
	 */

    @Test
    public void testWithDayOfMonth_Valid() {
        LocalDate date = new LocalDate(2024, 4, 10);
        LocalDate result = date.withDayOfMonth(1);
        assertEquals(new LocalDate(2024, 4, 1), result);
    }

    @Test
    public void testWithDayOfMonth_Invalid() {
        LocalDate date = new LocalDate(2024, 2, 1);
        assertThrows(IllegalArgumentException.class, () -> {
            date.withDayOfMonth(30);
        });
    }

    @Test
    public void testWithDayOfMonth_LastDayOfMonth() {
        LocalDate date = new LocalDate(2024, 1, 15);
        LocalDate result = date.withDayOfMonth(31);
        assertEquals(new LocalDate(2024, 1, 31), result);
    }

    @Test
    public void testWithDayOfMonth_FirstDayOfMonth() {
        LocalDate date = new LocalDate(2024, 5, 20);
        LocalDate result = date.withDayOfMonth(1);
        assertEquals(new LocalDate(2024, 5, 1), result);
    }

    @Test
    public void testWithDayOfMonth_LeapYear() {
        LocalDate date = new LocalDate(2024, 2, 10);
        LocalDate result = date.withDayOfMonth(29);
        assertEquals(new LocalDate(2024, 2, 29), result);
    }

    @Test
    public void testWithDayOfMonth_NonLeapYear() {
        LocalDate date = new LocalDate(2023, 2, 10);
        assertThrows(IllegalArgumentException.class, () -> {
            date.withDayOfMonth(29);
        });
    }
} 
