package cinemaApp.logic;

import javax.enterprise.inject.Alternative;

@Alternative
public class UnionEnglish implements IUnionLanguage {

    @Override
    public String printUnions() {
        return "Unions:";
    }
}
