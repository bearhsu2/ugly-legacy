package idv.kuma;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Greeting {

    public String greet() {
        LocalDate now = LocalDate.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return "happy holiday";
        }
        return "good day";
    }

}
