package projectCOE891.FormalMethods;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.Partial;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.GregorianChronology;

@RunWith(Theories.class)
public class PartialTest {

    private Partial datePartial;
    private Partial timePartial;
    private Chronology isoChronology;
    private Chronology gregorianChronology;

    @DataPoints
    public static final int[] VALID_YEARS = {1, 1970, 2000, 2023, 2100};

    @DataPoints
    public static final int[] VALID_MONTHS = {1, 2, 6, 12};

    @Before
    public void setUp() {
        isoChronology = ISOChronology.getInstance();
        gregorianChronology = GregorianChronology.getInstance();

        DateTimeFieldType[] dateFields = new DateTimeFieldType[] {
            DateTimeFieldType.year(),
            DateTimeFieldType.monthOfYear(),
            DateTimeFieldType.dayOfMonth()
        };
        int[] dateValues = new int[] {2023, 4, 15};
        datePartial = new Partial(dateFields, dateValues, isoChronology);

        DateTimeFieldType[] timeFields = new DateTimeFieldType[] {
            DateTimeFieldType.hourOfDay(),
            DateTimeFieldType.minuteOfHour(),
            DateTimeFieldType.secondOfMinute()
        };
        int[] timeValues = new int[] {14, 30, 0};
        timePartial = new Partial(timeFields, timeValues, gregorianChronology);
    }

    @Test
    public void testGetChronologyConstructorConsistency() {
        assertSame(isoChronology, datePartial.getChronology());
        assertSame(gregorianChronology, timePartial.getChronology());
    }

    @Test
    public void testGetChronologyWithNullChronology() {
        DateTimeFieldType[] fields = new DateTimeFieldType[] { DateTimeFieldType.year() };
        int[] values = new int[] { 2023 };

        Partial partial = new Partial(fields, values, null);
        assertNotNull(partial.getChronology());
    }

    @Test
    public void testGetChronologyImmutability() {
        Chronology c1 = datePartial.getChronology();
        Chronology c2 = datePartial.getChronology();
        Chronology c3 = datePartial.getChronology();

        assertSame(c1, c2);
        assertSame(c1, c3);
    }

    @Theory
    public void testGetValueConstructorConsistency(int year) {
        if (year < 0) return;

        Partial partial = new Partial(new DateTimeFieldType[] {
            DateTimeFieldType.year(), DateTimeFieldType.monthOfYear(), DateTimeFieldType.dayOfMonth()
        }, new int[] {year, 6, 15}, isoChronology);

        assertEquals(year, partial.getValue(0));
    }

    @Test
    public void testGetValueImmutability() {
        int v1 = datePartial.getValue(0);
        int v2 = datePartial.getValue(0);
        int v3 = datePartial.getValue(0);

        assertEquals(v1, v2);
        assertEquals(v1, v3);
    }

    @Test
    public void testGetValueIndexBounds() {
        for (int i = 0; i < 3; i++) {
            try {
                int value = datePartial.getValue(i);
            } catch (Exception e) {
                fail("getValue() should not throw exception for valid index " + i);
            }
        }

        try {
            datePartial.getValue(-1);
            fail("getValue() should throw exception for negative index");
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            datePartial.getValue(10);
            fail("getValue() should throw exception for index larger than field count");
        } catch (IndexOutOfBoundsException e) {
        }
    }
}
