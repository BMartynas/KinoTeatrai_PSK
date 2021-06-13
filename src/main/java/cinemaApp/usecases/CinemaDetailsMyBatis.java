package cinemaApp.usecases;


import cinemaApp.mybatis.dao.WunionMapper;
import cinemaApp.mybatis.dao.CinemaMapper;
import cinemaApp.mybatis.model.Wunion;
import cinemaApp.mybatis.model.Cinema;
import cinemaApp.mybatis.model.Movie;
import cinemaApp.mybatis.dao.MovieMapper;
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
public class CinemaDetailsMyBatis {
    @Inject
    private WunionMapper wunionMapper;;
    @Inject
    private CinemaMapper cinemaMapper;
    @Inject
    private MovieMapper movieMapper;
    @Inject
    private CheckReleaseYear checkReleaseYear;
    @Getter @Setter
    private Wunion wunion;
    @Getter @Setter
    private Cinema chosenCinema;
    @Getter @Setter
    private Movie movieToCreate = new Movie();

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if(requestParameters.get("id")!=null){
            int id = Integer.parseInt(requestParameters.get("id"));
            chosenCinema = cinemaMapper.selectByPrimaryKey(id);
            Map<String, String> wrequestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            int wid = Integer.parseInt(wrequestParameters.get("wid"));
            wunion = wunionMapper.selectByPrimaryKey(wid);
        }
    }

    @Transactional
    public String createMovie() {
        List<Cinema> cinemaList = new ArrayList();
        cinemaList.add(this.chosenCinema);
        movieToCreate.setCinemaList(cinemaList);

        List<Movie> movieList = this.chosenCinema.getMovieList();
        movieList.add(movieToCreate);
        this.chosenCinema.setMovieList(movieList);

        if(checkReleaseYear.releasedThisYear(movieToCreate.getYear())) {
            String transformedName = movieToCreate.getName().toUpperCase();
            movieToCreate.setName(transformedName);
        }
        movieMapper.insert(movieToCreate);

        return "cinema_detailsMyBatis.xhtml?faces-redirect=true&id="+this.chosenCinema.getId()+"&wid="+this.chosenCinema.getWunionId();
    }


}
