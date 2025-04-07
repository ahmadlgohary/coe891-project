package projectCOE891.Mutation;

import static org.junit.Assert.*;

import org.joda.time.Duration;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;


@RunWith(Theories.class)
public class TestDuration{

    @DataPoints
    public static Duration[] durationTimes = {
            new Duration(3600000),    
            new Duration(-1524),    
            new Duration(0),    
            new Duration(1000),    
            new Duration(-82524),    
            new Duration(Integer.MAX_VALUE),   
            new Duration(Integer.MIN_VALUE)
        };

    @Theory
    public void testNegated(Duration durationTime) {
        Duration updatedDuration = durationTime.negated();
        assertEquals(durationTime.getMillis()*(-1) , updatedDuration.getMillis());
    }

    @Theory
    public void testIsLongerThan(Duration duration1, Duration duration2) {
        boolean result = duration1.isLongerThan(duration2);
        assertEquals(duration1.getMillis() > duration2.getMillis(), result);
    }
}