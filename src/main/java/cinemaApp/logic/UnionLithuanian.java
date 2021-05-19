package cinemaApp.logic;

import javax.enterprise.inject.Alternative;

@Alternative
public class UnionLithuanian implements IUnionLanguage {

    @Override
    public String printUnions() {
        return "Profesinės sąjungos:";
    }
}
