package projectCOE891.Mutation;

import org.joda.time.Days;
import org.junit.Test;
import static org.junit.Assert.*;

public class DaysMutationTest {

    @Test
    public void testNegated() {
        Days days = Days.days(5);
        Days result = days.negated();
        assertEquals(Days.days(-5), result);
    }

    @Test
    public void testPlus() {
        Days d1 = Days.days(3);
        Days d2 = Days.days(2);
        Days result = d1.plus(d2);
        assertEquals(Days.days(5), result);
    }
}
