package projectCOE891.FormalMethods;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.joda.time.Seconds;

@RunWith(Theories.class)
public class SecondsTest {

    private Seconds zeroSeconds;
    private Seconds oneSecond;
    private Seconds sixtySeconds;
    private Seconds hourSeconds;

    @DataPoints
    public static final int[] SECOND_VALUES = {0, 1, 2, 30, 59, 60, 61, 3600, 86399, 86400, Integer.MAX_VALUE / 86400};

    @Before
    public void setUp() {
        zeroSeconds = Seconds.seconds(0);
        oneSecond = Seconds.seconds(1);
        sixtySeconds = Seconds.seconds(60);
        hourSeconds = Seconds.seconds(3600);
    }

    @Theory
    public void testGetSecondsConsistency(int seconds) {
        if (seconds < 0) return;

        Seconds s = Seconds.seconds(seconds);
        assertEquals(seconds, s.getSeconds());

        int firstCall = s.getSeconds();
        int secondCall = s.getSeconds();
        assertEquals(firstCall, secondCall);
    }

    @Test
    public void testGetSecondsWithZero() {
        assertEquals(0, zeroSeconds.getSeconds());
    }

    @Test
    public void testGetSecondsWithLargeValue() {
        int largeValue = Integer.MAX_VALUE / 86400;
        Seconds largeSeconds = Seconds.seconds(largeValue);
        assertEquals(largeValue, largeSeconds.getSeconds());
    }

    @Test
    public void testMinusBasicCorrectness() {
        for (int i = 0; i < SECOND_VALUES.length; i++) {
            for (int j = 0; j < SECOND_VALUES.length; j++) {
                if (SECOND_VALUES[j] > SECOND_VALUES[i]) continue;

                Seconds a = Seconds.seconds(SECOND_VALUES[i]);
                Seconds b = Seconds.seconds(SECOND_VALUES[j]);

                Seconds diff = a.minus(b);
                assertEquals(SECOND_VALUES[i] - SECOND_VALUES[j], diff.getSeconds());
            }
        }
    }

    @Test
    public void testMinusSelfSubtraction() {
        for (int value : SECOND_VALUES) {
            Seconds s = Seconds.seconds(value);
            Seconds diff = s.minus(s);
            assertEquals(0, diff.getSeconds());
        }
    }

    @Test
    public void testMinusWithZero() {
        for (int value : SECOND_VALUES) {
            Seconds s = Seconds.seconds(value);
            Seconds diff = s.minus(zeroSeconds);
            assertEquals(value, diff.getSeconds());
        }
    }

    @Test
    public void testMinusNegativeResultHandling() {
        try {
            Seconds smaller = Seconds.seconds(10);
            Seconds larger = Seconds.seconds(20);

            Seconds diff = smaller.minus(larger);
            assertEquals(-10, diff.getSeconds());
        } catch (IllegalArgumentException | ArithmeticException e) {
        }
    }

    @Test
    public void testMinusAdditionRelationship() {
        try {
            for (int i = 0; i < SECOND_VALUES.length; i++) {
                for (int j = 0; j < SECOND_VALUES.length; j++) {
                    if (SECOND_VALUES[j] > SECOND_VALUES[i]) continue;

                    Seconds a = Seconds.seconds(SECOND_VALUES[i]);
                    Seconds b = Seconds.seconds(SECOND_VALUES[j]);

                    Seconds diff = a.minus(b);
                    Seconds restored = diff.plus(b);

                    assertEquals(a.getSeconds(), restored.getSeconds());
                }
            }
        } catch (NoSuchMethodError e) {
        }
    }
}
