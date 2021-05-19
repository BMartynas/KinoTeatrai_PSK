package cinemaApp.usecases;
import cinemaApp.entities.Cinema;
import cinemaApp.entities.Wunion;
import cinemaApp.persistence.CinemasDAO;
import cinemaApp.persistence.WunionsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@Model
@Getter @Setter
public class CinemaUpdate implements Serializable {
    @Inject
    private CinemasDAO cinemasDAO;

    private Cinema chosenCinema;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if(requestParameters.get("cinemaId")!=null){
        Integer cinemaId = Integer.parseInt(requestParameters.get("cinemaId"));
        this.chosenCinema = cinemasDAO.findOne(cinemaId);}
    }


    @Transactional
    public String updateCinema() {
        try{
            Thread.sleep(7000);
            cinemasDAO.update(this.chosenCinema);
        } catch (OptimisticLockException e) {
            return "/cinema_details.xhtml?faces-redirect=true&id="+this.chosenCinema.getId()+"&wid="+this.chosenCinema.getWunion().getId() + "&error=optimistic-lock-exception";
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
        return "cinema_details.xhtml?faces-redirect=true&id="+this.chosenCinema.getId()+"&wid="+this.chosenCinema.getWunion().getId() + "&error=no-error";
    }
}
