package cinemaApp.decorators;

import cinemaApp.services.IPromoCodeGenerator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class AdvancedPromoCodeGenerator implements IPromoCodeGenerator {
    @Inject @Delegate @Any IPromoCodeGenerator promoCodeGenerator;

    public String generatePromoCode() {
        String generatedCode = promoCodeGenerator.generatePromoCode();
        if(generatedCode.startsWith("dovana")) {
            generatedCode = generatedCode + "METAMS";
        }
        return generatedCode;
    }
}
