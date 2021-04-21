package cinemaApp.usecases;

import cinemaApp.entities.Cinema;
import cinemaApp.entities.Movie;
import cinemaApp.entities.Wunion;
import cinemaApp.persistence.CinemasDAO;
import cinemaApp.persistence.MovieDAO;
import cinemaApp.persistence.WunionsDAO;
import cinemaApp.services.CheckReleaseYear;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.*;

@Model
@Getter @Setter
public class CinemaDetails {
    @Inject
    private CinemasDAO cinemasDAO;
    @Inject
    private WunionsDAO wunionsDAO;
    @Inject
    private MovieDAO movieDAO;
    @Inject
    private CheckReleaseYear checkReleaseYear;

    private Cinema chosenCinema;
    private Wunion wunion;

    private Movie movieToCreate = new Movie();

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if(requestParameters.get("id")!=null){
        int id = Integer.parseInt(requestParameters.get("id"));
        chosenCinema = cinemasDAO.findOne(id);
        Map<String, String> wrequestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int wid = Integer.parseInt(wrequestParameters.get("wid"));
        wunion = wunionsDAO.findOne(wid);}
    }

    @Transactional
    public String createMovie() {
        Set<Cinema> cinemaList = new HashSet<>();
        cinemaList.add(this.chosenCinema);
        movieToCreate.setCinemaList(cinemaList);

        Set<Movie> movieList = this.chosenCinema.getMovieList();
        movieList.add(movieToCreate);
        this.chosenCinema.setMovieList(movieList);

        if(checkReleaseYear.releasedThisYear(movieToCreate.getYear())) {
            String transformedName = movieToCreate.getName().toUpperCase();
            movieToCreate.setName(transformedName);
        }
        movieDAO.persist(movieToCreate);
        return "cinema_details.xhtml?faces-redirect=true&id="+this.chosenCinema.getId()+"&wid="+this.chosenCinema.getWunion().getId();
    }

}
