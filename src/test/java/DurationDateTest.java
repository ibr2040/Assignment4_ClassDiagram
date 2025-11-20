import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DurationDateTest {

    @Test
    void testValidDurationDateCreation() {
        DurationDate.Date start = new DurationDate.Date(2024, 3, 1, 10);
        DurationDate.Date end   = new DurationDate.Date(2024, 3, 10, 12);

        DurationDate d = new DurationDate(start, end);

        assertEquals(9, d.TimeLeft());
    }

    @Test
    void testInvalidDurationDateCreation() {
        DurationDate.Date start = new DurationDate.Date(2024, 5, 20, 10);
        DurationDate.Date end   = new DurationDate.Date(2024, 5, 15, 12);

        assertThrows(InvalidValueException.class, () -> {
            new DurationDate(start, end); // end < start
        });
    }

    @Test
    void testDateLeapYear() {
        DurationDate.Date d = new DurationDate.Date(2024, 3, 1, 0);
        assertEquals(31 + 29 + 1, d.getDays()); // 2024 — leap year
    }

    @Test
    void testDateNonLeapYear() {
        DurationDate.Date d = new DurationDate.Date(2023, 3, 1, 0);
        assertEquals(31 + 28 + 1, d.getDays());
    }

    @Test
    void testInvalidDateFormat() {
        assertThrows(InvalidFormatException.class, () -> {
            new DurationDate.Date(2024, 13, 5, 10); // недопустимый месяц
        });

        assertThrows(InvalidFormatException.class, () -> {
            new DurationDate.Date(2024, 2, 35, 5); // недопустимый день
        });

        assertThrows(InvalidFormatException.class, () -> {
            new DurationDate.Date(2024, 1, 10, 25); // недопустимый час
        });
    }
}

