package cinemaApp.usecases;
import cinemaApp.entities.Cinema;
import cinemaApp.entities.Wunion;
import cinemaApp.persistence.CinemasDAO;
import cinemaApp.persistence.WunionsDAO;
import lombok.Getter;
import lombok.Setter;
import cinemaApp.entities.Movie;
import cinemaApp.persistence.MovieDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

@Model
public class MoviesInfo {
    @Inject
    private CinemasDAO cinemasDAO;

    @Inject
    private MovieDAO movieDAO;
    @Inject
    private WunionsDAO wunionsDAO;
    @Getter
    @Setter
    private Movie chosenMovie;
    @Getter @Setter
    private Wunion wunion;
    private Cinema cinemaToCreate = new Cinema();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer movieId = Integer.parseInt(requestParameters.get("movieId"));
        this.chosenMovie = movieDAO.findOne(movieId);
        Integer wid = Integer.parseInt(requestParameters.get("wid"));
        this.wunion = wunionsDAO.findOne(wid);
    }
}
