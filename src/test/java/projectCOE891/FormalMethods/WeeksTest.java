package projectCOE891.FormalMethods;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.joda.time.Weeks;

@RunWith(Theories.class)
public class WeeksTest {

    private Weeks zeroWeeks;
    private Weeks oneWeek;
    private Weeks twoWeeks;

    @DataPoints
    public static final int[] WEEK_VALUES = {0, 1, 2, 4, 52, Integer.MAX_VALUE / 52};

    @Before
    public void setUp() {
        zeroWeeks = Weeks.weeks(0);
        oneWeek = Weeks.weeks(1);
        twoWeeks = Weeks.weeks(2);
    }

    @Theory
    public void testGetWeeksConsistency(int weeks) {
        if (weeks < 0) return;

        Weeks w = Weeks.weeks(weeks);
        assertEquals(weeks, w.getWeeks());

        int firstCall = w.getWeeks();
        int secondCall = w.getWeeks();
        assertEquals(firstCall, secondCall);
    }

    @Test
    public void testPlusCommutativity() {
        for (int i = 0; i < WEEK_VALUES.length; i++) {
            for (int j = 0; j < WEEK_VALUES.length; j++) {
                if (WEEK_VALUES[i] > Integer.MAX_VALUE - WEEK_VALUES[j]) continue;

                Weeks a = Weeks.weeks(WEEK_VALUES[i]);
                Weeks b = Weeks.weeks(WEEK_VALUES[j]);

                Weeks sum1 = a.plus(b);
                Weeks sum2 = b.plus(a);

                assertEquals(sum1.getWeeks(), sum2.getWeeks());
            }
        }
    }

    @Test
    public void testPlusIdentity() {
        for (int value : WEEK_VALUES) {
            Weeks w = Weeks.weeks(value);
            Weeks sum = w.plus(Weeks.ZERO);

            assertEquals(value, sum.getWeeks());
        }
    }
}
