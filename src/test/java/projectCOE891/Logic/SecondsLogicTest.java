package projectCOE891.Logic;

import org.joda.time.Seconds;
import org.junit.Test;

import static org.junit.Assert.*;

public class SecondsLogicTest {

    // --- isGreaterThan tests ---

    @Test
    public void testIsGreaterThan_true() {
        Seconds five = Seconds.seconds(5);
        Seconds three = Seconds.seconds(3);
        assertTrue(five.isGreaterThan(three));
    }

    @Test
    public void testIsGreaterThan_false_equal() {
        Seconds five = Seconds.seconds(5);
        Seconds alsoFive = Seconds.seconds(5);
        assertFalse(five.isGreaterThan(alsoFive));
    }

    @Test
    public void testIsGreaterThan_false_less() {
        Seconds two = Seconds.seconds(2);
        Seconds three = Seconds.seconds(3);
        assertFalse(two.isGreaterThan(three));
    }

    // --- plus tests ---

    @Test
    public void testPlus_positiveSeconds() {
        Seconds two = Seconds.seconds(2);
        Seconds three = Seconds.seconds(3);
        assertEquals(Seconds.seconds(5), two.plus(three));
    }

    @Test
    public void testPlus_negativeSeconds() {
        Seconds ten = Seconds.seconds(10);
        Seconds minusThree = Seconds.seconds(-3);
        assertEquals(Seconds.seconds(7), ten.plus(minusThree));
    }

    @Test
    public void testPlus_zeroSeconds() {
        Seconds seven = Seconds.seconds(7);
        assertEquals(seven, seven.plus(Seconds.ZERO));
    }
}
