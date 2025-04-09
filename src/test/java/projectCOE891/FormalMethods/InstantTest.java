package projectCOE891.FormalMethods;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.joda.time.Instant;

@RunWith(Theories.class)
public class InstantTest {

    private Instant epochInstant;
    private Instant currentTimeInstant;

    @DataPoints
    public static final long[] MILLIS_VALUES = {
        0L, 1L, 1000L, 60000L, 3600000L, 86400000L, 
        System.currentTimeMillis(), Long.MAX_VALUE/2, Long.MAX_VALUE
    };

    @Before
    public void setUp() {
        epochInstant = new Instant(0L);
        currentTimeInstant = new Instant(System.currentTimeMillis());
    }

    @Test
    public void testNowMonotonicity() {
        Instant first = Instant.now();

        try { Thread.sleep(5); } catch (InterruptedException e) { }
        Instant second = Instant.now();

        try { Thread.sleep(5); } catch (InterruptedException e) { }
        Instant third = Instant.now();

        assertTrue(second.getMillis() >= first.getMillis());
        assertTrue(third.getMillis() >= second.getMillis());
    }

    @Test
    public void testNowProximityToSystemTime() {
        long systemTime = System.currentTimeMillis();
        Instant now = Instant.now();
        long diff = Math.abs(now.getMillis() - systemTime);

        assertTrue(diff < 100);
    }

    @Test
    public void testNowNonNullGuarantee() {
        Instant now1 = Instant.now();
        assertNotNull(now1);

        Instant now2 = Instant.now();
        assertNotNull(now2);
    }

    @Theory
    public void testGetMillisConsistency(long millis) {
        if (millis < 0) return;

        Instant instant = new Instant(millis);
        assertEquals(millis, instant.getMillis());

        long firstCall = instant.getMillis();
        long secondCall = instant.getMillis();
        long thirdCall = instant.getMillis();

        assertEquals(firstCall, secondCall);
        assertEquals(firstCall, thirdCall);
    }

    @Test
    public void testGetMillisWithZero() {
        assertEquals(0L, epochInstant.getMillis());
    }

    @Test
    public void testGetMillisWithLargeValue() {
        long largeValue = Long.MAX_VALUE / 2;
        Instant largeInstant = new Instant(largeValue);
        assertEquals(largeValue, largeInstant.getMillis());
    }

    @Test
    public void testGetMillisWithNegativeValue() {
        try {
            long negativeValue = -86400000L;
            Instant negativeInstant = new Instant(negativeValue);
            assertEquals(negativeValue, negativeInstant.getMillis());
        } catch (IllegalArgumentException e) {
        }
    }
}
