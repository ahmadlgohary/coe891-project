/*
 * This Test Class does ISP Testing on the DateTime Constructor and plusDays()
 * Methods inside the DateTime Class
 * Ahmad El-Gohary
 */

package projectCOE891;

import static org.junit.Assert.*;

import org.joda.time.DateTime;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;


@RunWith(Theories.class)
public class TestDateTime {
	
	
	/*------------------------- Constructor Tests --------------------------------*/
	/*
	 * Functionality-based approach ISP for the Constructor
 	 * |Partition|B1	  |B2	   |B3  |
 	 * |---------|--------|--------|----|
 	 * |Year	 |Positive|Negative|Zero|
 	 * |Month	 |0 <=	  |1 -> 12 |12< |
 	 * |Day		 |0 <=	  |1 -> 31 |31< |
 	 * |Hours	 |0 <	  |0 -> 23 |23< |
	 * |Minutes	 |0 <	  |0 -> 59 |59< |
	 * |Seconds	 |0 <	  |0 -> 59 |59< |
	 * 
	 * 
	 * This is a functionality based approach of the valid values for the 
	 * contructor's inputs, since months have different number of days and 
	 */
	
	public static boolean isDateValid(int year, int month, int day, int hour, int minute, int second){
		// if any input is negative return false
		// not checking years because BC years
		if(month < 0 || day < 0 || hour < 0 || minute < 0 || second < 0 )
			return false; // can't have negative dates
		
		// check if upper limits
		if(month > 12 || day > 31 || hour > 23 || minute > 59 || second > 59 )
			return false; // can't have negative times that do not exist on a clock
		
		// check if days are actually valid
		if(month == 4|| month == 6|| month == 9|| month == 11) {
			if(day > 30) {
				return false; // 20 days have September April June and November
			}
		}
		if (month == 2) {
			// check leap year
			if((year % 4 == 0 || year % 100 != 0) || (year % 400 == 0)){
				if(day > 29) {
					return false;
				}
			}else if (day > 28) {
				return false;
			}
		}
		return true;
	}
	
    @DataPoints("years")
    public static Integer[] years = {2024, -1, 0, (Integer) null}; // Positive, Negative, Zero, null

    @DataPoints("months")
    public static Integer[] months = {0, 6, 13, (Integer) null};

    @DataPoints("days")
    public static Integer[] days = {0, 15, 28, 29, 32, (Integer) null};

    @DataPoints("hours")
    public static Integer[] hours = {-1, 12, 24, (Integer) null};

    @DataPoints("minutes")
    public static Integer[] minutes = {-1, 30, 60, (Integer) null};

    @DataPoints("seconds")
    public static Integer[] seconds = {-1, 45, 60, (Integer) null};

    @Theory
    public void testDateTimeConstructorISP(
            @FromDataPoints("years") int year,
            @FromDataPoints("months") int month,
            @FromDataPoints("days") int day,
            @FromDataPoints("hours") int hour,
            @FromDataPoints("minutes") int minute,
            @FromDataPoints("seconds") int second) {

        try {
            DateTime dt = new DateTime(year, month, day, hour, minute, second);
            // If it reaches here, it was a valid DateTime object
            assertNotNull(dt);
            
            // make sure the date is a valid actual date BC years are negative and AD years are positive 
            assertTrue(isDateValid(year, month, day, hour, minute, second));
        } catch (IllegalArgumentException e) {
            // Expected for invalid input combinations
        	assertTrue(true);
        } catch (NullPointerException e) {
        	// for null inputs
        	assertTrue(true);
        } catch (Exception e) {
        	System.out.println("m");
        	System.out.println(e.getMessage());
        	fail("Unexpected exception: " + e.getMessage());
        }
    }
    
    
    
    
    
    
/*------------------------- plusDays() Tests --------------------------------*/
	/* ISP For plusDays()
	 * |Partition|B1	  |B2	   |B3  |
	 * |Days	 |Positive|Negative|Zero|
	 * */
    
        
    @Test
    public void testPlusDays_positiveNumber() {
        DateTime original = new DateTime(2023, 4, 5, 0, 0);
        DateTime expected = new DateTime(2023, 4, 10, 0, 0);
        DateTime result = original.plusDays(5);

        assertEquals(expected, result);
    }

    @Test
    public void testPlusDays_zeroDays() {
        DateTime original = new DateTime(2023, 4, 5, 0, 0);
        DateTime result = original.plusDays(0);

        assertEquals(original, result);
    }

    @Test
    public void testPlusDays_negativeNumber() {
        DateTime original = new DateTime(2023, 4, 5, 0, 0);
        DateTime expected = new DateTime(2023, 4, 1, 0, 0);
        DateTime result = original.plusDays(-4);

        assertEquals(expected, result);
    }

    @Test
    public void testPlusDays_acrossMonthBoundary() {
        DateTime original = new DateTime(2023, 1, 30, 0, 0);
        DateTime expected = new DateTime(2023, 2, 4, 0, 0);
        DateTime result = original.plusDays(5);

        assertEquals(expected, result);
    }

    @Test(expected=NullPointerException.class)
    public void testPlusDays_Null() {
    	@SuppressWarnings("null")
		int daysToAdd = (Integer) null;
    	DateTime original = new DateTime(2023, 1, 30, 0, 0);
    	original.plusDays(daysToAdd);	
    }
    
    @Test
    public void testPlusDays_leapYearPositive() {
    	DateTime original = new DateTime(2024, 2, 27, 0, 0);
    	DateTime expected = new DateTime(2024, 3, 3, 0, 0);
    	DateTime result = original.plusDays(5);
    	assertEquals(expected, result);
    }
    
    @Test
    public void testPlusDays_leapYearNegative() {
    	DateTime original = new DateTime(2024, 3, 3, 0, 0);
    	DateTime expected = new DateTime(2024, 2, 27, 0, 0);
    	DateTime result = original.plusDays(-5);
    	assertEquals(expected, result);
    }
}
