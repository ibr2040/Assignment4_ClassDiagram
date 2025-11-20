public class DurationDate {
private Date dateOfEstablishment;
private Date dateOfExpiration;
public int TimeLeft(){
    return dateOfExpiration.getDays() - dateOfEstablishment.getDays();
}
    public DurationDate(Date dateOfEstablishment, Date dateOfExpiration){
        this.dateOfEstablishment = dateOfEstablishment;
        this.dateOfExpiration = dateOfExpiration;
        if(!(TimeLeft()>0)){throw new InvalidValueException("The duration exceeds the maximum number of days");}
    }
    public static class Date {
        private int year;
        private int month;
        private int day;
        private int hour;

        Date(int year, int month, int day, int hour) {

            // корректная проверка
            if (month < 1 || month > 12 ||
                    day < 1 || day > 31 ||
                    hour < 0 || hour > 23)
            {
                throw new InvalidFormatException("Invalid date format");
            }
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
        }
        private static final int[] daysInMonth = {
                31, 28, 31, 30, 31, 30,
                31, 31, 30, 31, 30, 31
        };

        private boolean isLeapYear() {
            return (year % 400 == 0) ||
                    (year % 4 == 0 && year % 100 != 0);
        }

        public int getDays() {
            int days = day;

            for (int i = 0; i < month - 1; i++) {
                days += daysInMonth[i];
            }
            if (isLeapYear() && month > 2) {
                days += 1;
            }
            return days;
        }
    }
    }
