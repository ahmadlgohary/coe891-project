package projectCOE891.Mutation;

import org.joda.time.Hours;
import org.junit.Test;
import static org.junit.Assert.*;

public class HoursMutationTest {

    @Test
    public void testMinus() {
        Hours base = Hours.hours(5);
        Hours result = base.minus(Hours.hours(2));
        assertEquals(Hours.hours(3), result);
    }

    @Test
    public void testDividedBy() {
        Hours base = Hours.hours(6);
        Hours result = base.dividedBy(2);
        assertEquals(Hours.hours(3), result);
    }
}
