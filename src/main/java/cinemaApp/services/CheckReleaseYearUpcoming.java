package cinemaApp.services;

import javax.enterprise.inject.Specializes;
import java.util.Calendar;

@Specializes
public class CheckReleaseYearUpcoming extends CheckReleaseYear {
    public boolean releasedThisYear(int releaseYear) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return releaseYear == currentYear || releaseYear == currentYear + 1;
    }
}
