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
public class CinemaUpdateMyBatis {
    @Inject
    private CinemaMapper cinemaMapper;
    @Getter @Setter
    private Cinema chosenCinema;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if(requestParameters.get("cinemaId")!=null){
            Integer cinemaId = Integer.parseInt(requestParameters.get("cinemaId"));
            this.chosenCinema = cinemaMapper.selectByPrimaryKey(cinemaId); }
    }

    @Transactional
    public String updateCinema() {
        cinemaMapper.updateByPrimaryKey(this.chosenCinema);
        return "cinema_detailsMyBatis.xhtml?faces-redirect=true&id="+this.chosenCinema.getId()+"&wid="+this.chosenCinema.getWunionId();
    }
}
