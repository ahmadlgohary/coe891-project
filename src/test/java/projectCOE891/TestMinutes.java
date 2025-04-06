/*
 * This Test Class does ISP Testing on the ToStandardSeconds() and minutesBetween()
 * Methods inside the Minutes Class
 * Ahmad El-Gohary
 */


package projectCOE891;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.junit.Test;
import org.junit.experimental.theories.Theories;
import org.junit.runner.RunWith;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theory;



@RunWith(Theories.class)
public class TestMinutes {
	/*------------------------- minutesBetween() Tests --------------------------------*/
	/*
	 * Functionality-based approach ISP for the Constructor
	 *| Partition       | B1                 |B2                  |B3                 |
	 *|-----------------|--------------------|--------------------|-------------------|
	 *| Start & End Type| Both  LocalTime    |start == end        |Mixed types	  	  |
	 *| Time difference | Positive difference|Zero difference     |Negative difference|
	 *| Null input      | No nulls           |start or end is null|			-		  |
	 * 
	 */
	
	
	
	@Test
    public void testMinutesBetween_LocalTime_Positive() {
        LocalTime start = new LocalTime(10, 15);
        LocalTime end = new LocalTime(11, 0);

        Minutes minutes = Minutes.minutesBetween(start, end);
        assertEquals(45, minutes.getMinutes());
    }

    @Test
    public void testMinutesBetween_LocalTime_Zero() {
        LocalTime time = new LocalTime(12, 0);
        Minutes minutes = Minutes.minutesBetween(time, time);

        assertEquals(0, minutes.getMinutes());
    }

    @Test
    public void testMinutesBetween_LocalTime_Negative() {
        LocalTime start = new LocalTime(14, 30);
        LocalTime end = new LocalTime(13, 0);

        Minutes minutes = Minutes.minutesBetween(start, end);
        assertEquals(-90, minutes.getMinutes());
    }

    @Test
    public void testMinutesBetween_MixedPartials() {
        LocalDate start = new LocalDate(2024, 4, 6);
        LocalDate end = new LocalDate(2024, 4, 7);

        int minutesInADay = 1440; // 24 hours * 60 minutes
        Minutes minutes = Minutes.minutesBetween(start, end);
        System.out.println(minutes.getMinutes());
        assertEquals(minutesInADay, minutes.getMinutes()); 
    }

    @Test
    public void testMinutesBetween_LocalDateTime() {
        LocalDateTime start = new LocalDateTime(2024, 4, 6, 10, 0);
        LocalDateTime end = new LocalDateTime(2024, 4, 6, 11, 30);

        Minutes minutes = Minutes.minutesBetween(start, end);
        assertEquals(90, minutes.getMinutes());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testMinutesBetween_NullStart() {
        LocalTime end = new LocalTime(12, 0);
        Minutes.minutesBetween(null, end);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testMinutesBetween_NullEnd() {
        LocalTime start = new LocalTime(12, 0);
        Minutes.minutesBetween(start, null);
     }
    
    
    
	/*------------------------- ToStandardSeconds() Tests --------------------------------*/
	/*
	 * Functionality-based approach ISP for the Constructor
	 *| Partition | B1   |B2        |B3       |
	 *|-----------|------|----------|---------|
	 *| Minutes   | Zero | Positive |Negative |
	 * 
	 */
    
    @DataPoints
    public static int[] minutesValues = {0, 10, 60, -5, -30};

    @Theory
    public void testToStandardSeconds_Theory(int minutes) {
    	int expectedSeconds = minutes * 60;
        Minutes minutesObject = Minutes.minutes(minutes);
        Seconds expectedSecondsObject = Seconds.seconds( expectedSeconds);
        assertEquals(expectedSecondsObject, minutesObject.toStandardSeconds());
    }
}



