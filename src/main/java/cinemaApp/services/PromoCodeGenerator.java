package cinemaApp.services;
import org.apache.deltaspike.core.api.future.Futureable;
import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.Random;
@ApplicationScoped
public class PromoCodeGenerator implements IPromoCodeGenerator {
    Random randomizer = new Random();
    List<String> promoCodes = new ArrayList<String>();

    public String generatePromoCode() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        promoCodes.add("greitai30");
        promoCodes.add("zaiboSmugis50");
        promoCodes.add("kaledos40");
        promoCodes.add("nedvejok10");
        promoCodes.add("jokiosBaimes5");
        promoCodes.add("dovana60");
        promoCodes.add("dovana42");
        promoCodes.add("dovana25");
        promoCodes.add("dovana19");
        promoCodes.add("dovana11");

        String randomCode = promoCodes.get(randomizer.nextInt(promoCodes.size()));
        return randomCode;
    }
}
