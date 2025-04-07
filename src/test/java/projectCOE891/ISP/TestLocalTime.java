package projectCOE891.ISP;
import static org.junit.Assert.*;

import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalTime;


import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class TestLocalTime{

    @DataPoints
    public static int[] hours = {0, 12, 23, -1};  // Various hours for testing

    @Theory
    public void testWithHourOfDay(int hour) {
    	if (hour >= 0 && hour <= 23) {
	        LocalTime time = new LocalTime(10, 30);
	        LocalTime updatedTime = time.withHourOfDay(hour);
	        assertEquals(hour, updatedTime.getHourOfDay());
    	} else {
    		try {	
	    		LocalTime time = new LocalTime(10, 30);
	    		LocalTime updatedTime = time.withHourOfDay(hour);
    		}catch(IllegalFieldValueException e) {
    			assertTrue(true);
    		}
        }
    }
    
    
    @DataPoints
    public static LocalTime[] times = {
        new LocalTime(10, 30),
        new LocalTime(9, 30),
        new LocalTime(12, 0)
    };

    @Theory
    public void testIsBefore(LocalTime time1, LocalTime time2) {
    	if (time1.getHourOfDay() > time2.getHourOfDay()) {
    		assertTrue(time2.isBefore(time1));	
    	}
    	else if (time1.getHourOfDay() == time2.getHourOfDay() && time1.getMinuteOfHour() > time2.getMinuteOfHour()) {
			assertTrue(time2.isBefore(time1));
		}
    	else {
			assertTrue(!time2.isBefore(time1));	
    	}
    }
}
