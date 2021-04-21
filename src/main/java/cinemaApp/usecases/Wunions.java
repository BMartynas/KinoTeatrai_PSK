package cinemaApp.usecases;
import lombok.Getter;
import lombok.Setter;
import cinemaApp.entities.Wunion;
import cinemaApp.persistence.WunionsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Wunions {
    @Inject
    private WunionsDAO wunionsDAO;

    @Getter @Setter
    private Wunion wunionToCreate = new Wunion();

    @Getter
    private List<Wunion> allWunions;

    @PostConstruct
    public void init(){
        loadAllWunions();
    }

    @Transactional
    public String createWunion(){
        this.wunionsDAO.persist(wunionToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllWunions(){
        this.allWunions = wunionsDAO.loadAll();
    }
}
