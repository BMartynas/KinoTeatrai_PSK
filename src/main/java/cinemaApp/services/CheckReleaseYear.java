package cinemaApp.services;

import javax.enterprise.context.ApplicationScoped;
import java.util.Calendar;

@ApplicationScoped
public class CheckReleaseYear {
    public boolean releasedThisYear(int releaseYear) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return releaseYear == currentYear;
    }
}
