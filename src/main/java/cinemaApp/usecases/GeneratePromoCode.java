package cinemaApp.usecases;
import cinemaApp.services.PromoCodeGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SessionScoped
@Named
public class GeneratePromoCode implements Serializable {
    @Inject
    PromoCodeGenerator promoCodeGenerator;

    private CompletableFuture<String> promoCodeGenerationTask = null;

    public String generateMoviePromoCode() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        promoCodeGenerationTask = CompletableFuture.supplyAsync(() -> promoCodeGenerator.generatePromoCode());

        return "/movieInfo?faces-redirect=true&movieId="+ requestParameters.get("movieId") +"&wid=" + requestParameters.get("wid");
    }

    public boolean isPromoCodeGenerationRunning() {
        return promoCodeGenerationTask != null && !promoCodeGenerationTask.isDone();
    }

    public String getPromoCodeGenerationStatus() throws ExecutionException, InterruptedException {
        if (promoCodeGenerationTask == null) {
            return null;
        } else if (isPromoCodeGenerationRunning()) {
            return "Promo code generation in progress";
        }
        return "Movie's promo code: " + promoCodeGenerationTask.get();
    }
}
