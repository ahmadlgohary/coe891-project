package projectCOE891.Mutation;

import org.joda.time.MutableDateTime;
import org.junit.Test;
import static org.junit.Assert.*;

public class MutableDateTimeMutationTest {

    @Test
    public void testSetMillis() {
        MutableDateTime dt = new MutableDateTime();
        dt.setMillis(1000000L);
        assertEquals(1000000L, dt.getMillis());
    }

    @Test
    public void testAddDays() {
        MutableDateTime dt = new MutableDateTime(2023, 1, 1, 0, 0, 0, 0); // Full constructor
        dt.addDays(2);
        assertEquals(3, dt.getDayOfMonth());
    }

}
