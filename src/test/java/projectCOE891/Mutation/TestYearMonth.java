package projectCOE891.Mutation;

import static org.junit.Assert.*;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.YearMonth;


@RunWith(Theories.class)
public class TestYearMonth {

    @DataPoints
    public static int[] months = {1, 6, 12, -1, 13, -3, 0, 24};

    @Theory
    public void testWithMonthOfYear(int month) {
    	YearMonth yearMonth = new YearMonth(2022, 5);

    	if (month >= 1 && month <= 12) {
	        YearMonth updatedYearMonth = yearMonth.withMonthOfYear(month);
	        assertEquals(month, updatedYearMonth.getMonthOfYear());
        } else {
            // Handle invalid month case (should throw an exception)
        	assertThrows(IllegalFieldValueException.class, ()->{yearMonth.withMonthOfYear(month);});
        }
    }

    @Theory
    public void testPlusMonths(int months) {
        YearMonth yearMonth = new YearMonth(2022, 5);
        YearMonth updatedYearMonth = yearMonth.plusMonths(months);
        int expectedMonth = (yearMonth.getMonthOfYear() + months - 1) % 12 + 1;
        int expectedYear = yearMonth.getYear() + (yearMonth.getMonthOfYear() + months - 1) / 12;
        assertEquals(expectedYear, updatedYearMonth.getYear());
        assertEquals(expectedMonth, updatedYearMonth.getMonthOfYear());
    }

}
