package cinemaApp.usecases;
import cinemaApp.interceptors.LoggedInvocation;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;
import javax.transaction.Transactional;

import lombok.Getter;
import lombok.Setter;
import cinemaApp.entities.Wunion;
import cinemaApp.entities.Cinema;
import cinemaApp.persistence.WunionsDAO;
import cinemaApp.persistence.CinemasDAO;

@Model
public class CinemasForWunion implements Serializable {
    @Inject
    private WunionsDAO wunionsDAO;

    @Inject
    private CinemasDAO cinemasDAO;

    @Getter @Setter
    private Wunion wunion;

    @Getter @Setter
    private Cinema cinemaToCreate = new Cinema();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer wunionId = Integer.parseInt(requestParameters.get("wunionId"));
        this.wunion = wunionsDAO.findOne(wunionId);
    }

    @Transactional
    @LoggedInvocation
    public String createCinema() {
        cinemaToCreate.setWunion(this.wunion);
        cinemasDAO.persist(cinemaToCreate);
        return "cinemas?faces-redirect=true&wunionId=" + this.wunion.getId();
    }
}
