package cinemaApp.usecases;
import cinemaApp.mybatis.dao.WunionMapper;
import cinemaApp.mybatis.dao.CinemaMapper;
import cinemaApp.mybatis.model.Wunion;
import cinemaApp.mybatis.model.Cinema;
import cinemaApp.mybatis.model.Movie;
import cinemaApp.mybatis.dao.MovieMapper;
import cinemaApp.persistence.CinemasDAO;
import cinemaApp.persistence.MovieDAO;
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
public class MoviesInfoMyBatis {
    @Inject
    private WunionMapper wunionMapper;;

    @Inject
    private MovieMapper movieMapper;

    @Getter @Setter
    private Movie chosenMovie;
    @Getter @Setter
    private Wunion wunion;

    private Cinema cinemaToCreate = new Cinema();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer movieId = Integer.parseInt(requestParameters.get("movieId"));
        this.chosenMovie = movieMapper.selectByPrimaryKey(movieId);
        Integer wid = Integer.parseInt(requestParameters.get("wid"));
        this.wunion = wunionMapper.selectByPrimaryKey(wid);
    }
}
