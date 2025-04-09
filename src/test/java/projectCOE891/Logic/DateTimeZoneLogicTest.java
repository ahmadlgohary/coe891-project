package projectCOE891.Logic;

import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.tz.FixedDateTimeZone;
import org.junit.Test;

import static org.junit.Assert.*;

public class DateTimeZoneLogicTest {

    @Test
    public void testIsFixed_withFixedDateTimeZone() {
        DateTimeZone zone = new FixedDateTimeZone("ID", "ID", 3600000, 3600000);
        assertTrue(zone.isFixed());  // P = true
    }

    @Test
    public void testIsFixed_withNonFixedDateTimeZone() {
        DateTimeZone nonFixedZone = new NonFixedDateTimeZone("Custom/NonFixed");
        assertFalse(nonFixedZone.isFixed());  // P = false
    }

    @Test
    public void testGetOffset_withNullInstant() {
        DateTimeZone zone = DateTimeZone.UTC;
        int offset = zone.getOffset((Instant) null);  // P = true
        assertEquals(0, offset); // UTC offset should be 0
    }

    @Test
    public void testGetOffset_withInstant() {
        DateTimeZone zone = DateTimeZone.UTC;
        Instant instant = new Instant(0); // Jan 1, 1970 UTC
        int offset = zone.getOffset(instant);  // P = false
        assertEquals(0, offset); // UTC offset should still be 0
    }

    // Inner class to simulate a non-fixed time zone
    static class NonFixedDateTimeZone extends DateTimeZone {
        protected NonFixedDateTimeZone(String id) {
            super(id);
        }

        @Override
        public String getNameKey(long instant) {
            return "Custom";
        }

        @Override
        public int getOffset(long instant) {
            return 0;
        }

        @Override
        public int getStandardOffset(long instant) {
            return 0;
        }

        @Override
        public boolean isFixed() {
            return false;  // This is what we want to test
        }

        @Override
        public long nextTransition(long instant) {
            return instant;
        }

        @Override
        public long previousTransition(long instant) {
            return instant;
        }

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof NonFixedDateTimeZone);
        }

        @Override
        public int hashCode() {
            return getID().hashCode();
        }
    }
}
