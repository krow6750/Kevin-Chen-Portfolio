/**
 * Name: Kevin Chen
 * Assignment: Midterm synthesis
 * Date: 3/3/23
 * Notes: JUnit testing for DateTime class
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateTimeTest {

    @Test
    public void testDateTimeConstructorAndGetters() {
        DateTime dateTime = new DateTime(14, 30, 25, 2, 2023, "Wednesday");
        assertEquals(14, dateTime.getHour());
        assertEquals(30, dateTime.getMinute());
        assertEquals(25, dateTime.getDay());
        assertEquals(2, dateTime.getMonth());
        assertEquals(2023, dateTime.getYear());
        assertEquals("Wednesday", dateTime.getDayName());
    }

    @Test
    public void testDateTimeSetters() {
        DateTime dateTime = new DateTime();
        dateTime.setHour(10);
        dateTime.setMinute(45);
        dateTime.setDay(10);
        dateTime.setMonth(6);
        dateTime.setYear(2022);
        dateTime.setDayName("Friday");

        assertEquals(10, dateTime.getHour());
        assertEquals(45, dateTime.getMinute());
        assertEquals(10, dateTime.getDay());
        assertEquals(6, dateTime.getMonth());
        assertEquals(2022, dateTime.getYear());
        assertEquals("Friday", dateTime.getDayName());
    }

    @Test
    public void testInvalidInputParameters() {
        assertThrows(IllegalArgumentException.class, () -> new DateTime(25, 0, 1, 1, 2023, "Monday"));
        assertThrows(IllegalArgumentException.class, () -> new DateTime(23, 60, 1, 1, 2023, "Monday"));
        assertThrows(IllegalArgumentException.class, () -> new DateTime(23, 0, 32, 1, 2023, "Monday"));
        assertThrows(IllegalArgumentException.class, () -> new DateTime(23, 0, 1, 13, 2023, "Monday"));
        assertThrows(IllegalArgumentException.class, () -> new DateTime(23, 0, 1, 1, -1, "Monday"));
        assertThrows(IllegalArgumentException.class, () -> new DateTime(23, 0, 1, 1, 2023, null));
        
        DateTime dateTime = new DateTime();
        assertThrows(IllegalArgumentException.class, () -> dateTime.setHour(24));
        assertThrows(IllegalArgumentException.class, () -> dateTime.setMinute(-1));
        assertThrows(IllegalArgumentException.class, () -> dateTime.setDay(0));
        assertThrows(IllegalArgumentException.class, () -> dateTime.setMonth(13));
        assertThrows(IllegalArgumentException.class, () -> dateTime.setYear(-1));
        assertThrows(IllegalArgumentException.class, () -> dateTime.setDayName(null));
    }
}
