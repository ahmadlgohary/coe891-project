package projectCOE891.Logic;

import org.joda.time.LocalDate;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.ISOChronology;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChronologyLogicTest {

    private final GregorianChronology chrono = GregorianChronology.getInstanceUTC();

    // --- isLeapYear tests ---

    @Test
    public void testIsLeapYear_true_divBy4Not100() {
        assertTrue(new LocalDate(2020, 1, 1, chrono).year().isLeap());
    }

    @Test
    public void testIsLeapYear_false_divBy100Not400() {
        assertFalse(new LocalDate(1900, 1, 1, chrono).year().isLeap());
    }

    @Test
    public void testIsLeapYear_true_divBy400() {
        assertTrue(new LocalDate(2000, 1, 1, chrono).year().isLeap());
    }

    @Test
    public void testIsLeapYear_false_notDivBy4() {
        assertFalse(new LocalDate(2023, 1, 1, chrono).year().isLeap());
    }


    // --- getDateTimeMillis tests ---

    @Test
    public void testGetDateTimeMillis_valid() {
        long millis = chrono.getDateTimeMillis(2024, 2, 29, 3600000); // valid leap day
        assertTrue(millis > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDateTimeMillis_invalidMonth() {
        chrono.getDateTimeMillis(2024, 13, 15, 3600000);  // invalid month
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDateTimeMillis_invalidDay() {
        chrono.getDateTimeMillis(2024, 2, 30, 3600000);  // Feb 30 is invalid
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDateTimeMillis_negativeMillis() {
        chrono.getDateTimeMillis(2024, 2, 15, -1);  // negative millis of day
    }
}
