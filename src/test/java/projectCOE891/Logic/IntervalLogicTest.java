package projectCOE891.Logic;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.junit.Test;

import static org.junit.Assert.*;

public class IntervalLogicTest {
	@Test
	public void testContains_nullInstant() {
	    Interval interval = new Interval(
	        new DateTime(2023, 1, 1, 0, 0, DateTimeZone.UTC),
	        new DateTime(2023, 1, 2, 0, 0, DateTimeZone.UTC));

	    boolean result = interval.contains((Instant) null);  // P1 = false
	    assertFalse("Expected contains(null) to return false", result);
	}





    @Test
    public void testContains_inside() {
        Interval interval = new Interval(
            new DateTime(2023, 1, 1, 0, 0, DateTimeZone.UTC),
            new DateTime(2023, 1, 2, 0, 0, DateTimeZone.UTC));
        Instant instant = new Instant(new DateTime(2023, 1, 1, 12, 0, DateTimeZone.UTC).getMillis());
        assertTrue(interval.contains(instant));  // P1 = true, P2 = true
    }

    @Test
    public void testContains_outside() {
        Interval interval = new Interval(
            new DateTime(2023, 1, 1, 0, 0, DateTimeZone.UTC),
            new DateTime(2023, 1, 2, 0, 0, DateTimeZone.UTC));
        Instant instant = new Instant(new DateTime(2023, 1, 2, 1, 0, DateTimeZone.UTC).getMillis());
        assertFalse(interval.contains(instant));  // P1 = true, P2 = false
    }


    @Test
    public void testOverlaps_true() {
        Interval a = new Interval(
                new DateTime(2023, 1, 1, 0, 0),
                new DateTime(2023, 1, 2, 0, 0));
        Interval b = new Interval(
                new DateTime(2023, 1, 1, 12, 0),
                new DateTime(2023, 1, 2, 12, 0));
        assertTrue(a.overlaps(b));  // P1 = T, P2 = T
    }

    @Test
    public void testOverlaps_false_bothClausesFalse() {
        Interval a = new Interval(
            new DateTime(2023, 1, 1, 0, 0, DateTimeZone.UTC),
            new DateTime(2023, 1, 1, 1, 0, DateTimeZone.UTC));
        Interval b = new Interval(
            new DateTime(2023, 1, 2, 0, 0, DateTimeZone.UTC),
            new DateTime(2023, 1, 2, 1, 0, DateTimeZone.UTC));
        assertFalse(a.overlaps(b));  // P1 = F, P2 = F
    }


    @Test
    public void testOverlaps_false_clause1False() {
        Interval a = new Interval(
            new DateTime(2023, 1, 1, 0, 0, DateTimeZone.UTC),
            new DateTime(2023, 1, 1, 1, 0, DateTimeZone.UTC));
        Interval b = new Interval(
            new DateTime(2023, 1, 1, 2, 0, DateTimeZone.UTC),
            new DateTime(2023, 1, 1, 3, 0, DateTimeZone.UTC));
        assertFalse(a.overlaps(b));  // P1 = false, P2 = true
    }

    @Test
    public void testOverlaps_false_clause2False() {
        Interval a = new Interval(
            new DateTime(2023, 1, 2, 0, 0, DateTimeZone.UTC),
            new DateTime(2023, 1, 2, 1, 0, DateTimeZone.UTC));
        Interval b = new Interval(
            new DateTime(2023, 1, 1, 0, 0, DateTimeZone.UTC),
            new DateTime(2023, 1, 1, 2, 0, DateTimeZone.UTC));
        assertFalse(a.overlaps(b));  // P1 = T, P2 = F
    }

}
