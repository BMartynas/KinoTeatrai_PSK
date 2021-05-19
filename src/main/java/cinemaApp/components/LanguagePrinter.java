package cinemaApp.components;

import cinemaApp.logic.IUnionLanguage;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class LanguagePrinter {
    @Inject
    private IUnionLanguage unionLanguage;

    public String printUnions() {
        return unionLanguage.printUnions();
    }

}
