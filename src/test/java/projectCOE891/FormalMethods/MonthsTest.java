package projectCOE891.FormalMethods;

import org.joda.time.Months;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Theories.class)
public class MonthsTest {

    private Months oneMonth;
    private Months twoMonths;
    private Months twelveMonths;

    @DataPoints
    public static final int[] MONTH_VALUES = {0, 1, 2, 3, 6, 11, 12, 24, 36, Integer.MAX_VALUE / 12};

    @Before
    public void setUp() {
        oneMonth = Months.months(1);
        twoMonths = Months.months(2);
        twelveMonths = Months.months(12);
    }

    @Test
    public void testIsLessThan_antisymmetry() {
        for (int i : MONTH_VALUES) {
            for (int j : MONTH_VALUES) {
                Months a = Months.months(i);
                Months b = Months.months(j);
                if (a.isLessThan(b)) {
                    assertFalse(b.isLessThan(a));
                }
            }
        }
    }

    @Test
    public void testIsLessThan_transitivity() {
        for (int i : MONTH_VALUES) {
            for (int j : MONTH_VALUES) {
                for (int k : MONTH_VALUES) {
                    Months a = Months.months(i);
                    Months b = Months.months(j);
                    Months c = Months.months(k);
                    if (a.isLessThan(b) && b.isLessThan(c)) {
                        assertTrue(a.isLessThan(c));
                    }
                }
            }
        }
    }

    @Test
    public void testIsLessThan_irreflexivity() {
        for (int value : MONTH_VALUES) {
            Months m = Months.months(value);
            assertFalse(m.isLessThan(Months.months(value)));
        }
    }

    @Theory
    public void testGetMonths_consistency(int value) {
        if (value < 0) return;
        Months m = Months.months(value);
        assertEquals(value, m.getMonths());
        assertEquals(m.getMonths(), m.getMonths());
    }

    @Test
    public void testGetMonths_zero() {
        assertEquals(0, Months.months(0).getMonths());
    }

    @Test
    public void testGetMonths_largeValue() {
        int large = Integer.MAX_VALUE / 12;
        assertEquals(large, Months.months(large).getMonths());
    }
}
