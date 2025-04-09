package projectCOE891.Logic;

import org.joda.time.LocalDateTime;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocalDateTimeLogicTest {

    @Test
    public void testWithHourOfDay_validHour() {
        LocalDateTime ldt = new LocalDateTime(2023, 4, 1, 10, 0);
        LocalDateTime updated = ldt.withHourOfDay(13);  // P = true
        assertEquals(13, updated.getHourOfDay());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithHourOfDay_negativeHour() {
        LocalDateTime ldt = new LocalDateTime(2023, 4, 1, 10, 0);
        ldt.withHourOfDay(-1);  // P = false
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithHourOfDay_hourTooHigh() {
        LocalDateTime ldt = new LocalDateTime(2023, 4, 1, 10, 0);
        ldt.withHourOfDay(24);  // P = false
    }

    @Test
    public void testIsBefore_whenTrue() {
        LocalDateTime earlier = new LocalDateTime(2023, 1, 1, 10, 0);
        LocalDateTime later = new LocalDateTime(2023, 1, 1, 12, 0);
        assertTrue(earlier.isBefore(later));  // P = true
    }

    @Test
    public void testIsBefore_whenFalse_equal() {
        LocalDateTime a = new LocalDateTime(2023, 1, 1, 12, 0);
        LocalDateTime b = new LocalDateTime(2023, 1, 1, 12, 0);
        assertFalse(a.isBefore(b));  // P = false
    }

    @Test
    public void testIsBefore_whenFalse_later() {
        LocalDateTime a = new LocalDateTime(2023, 1, 1, 14, 0);
        LocalDateTime b = new LocalDateTime(2023, 1, 1, 12, 0);
        assertFalse(a.isBefore(b));  // P = false
    }
}
