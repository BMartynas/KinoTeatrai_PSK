package cinemaApp.usecases;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;
import javax.transaction.Transactional;


import lombok.Getter;
import lombok.Setter;
import cinemaApp.mybatis.dao.WunionMapper;
import cinemaApp.mybatis.dao.CinemaMapper;
import cinemaApp.mybatis.model.Wunion;
import cinemaApp.mybatis.model.Cinema;

@Model
public class CinemasForWunionMyBatis {
    @Inject
    private WunionMapper wunionMapper;;

    @Inject
    private CinemaMapper cinemaMapper;

    @Getter @Setter
    private Wunion wunion;

    @Getter @Setter
    private Cinema cinemaToCreate = new Cinema();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer wunionId = Integer.parseInt(requestParameters.get("wunionId"));
        this.wunion = wunionMapper.selectByPrimaryKey(wunionId);
    }

    @Transactional
    public String createCinema() {
        cinemaToCreate.setWunionId(this.wunion.getId());
        cinemaMapper.insert(cinemaToCreate);
        return "cinemasMyBatis?faces-redirect=true&wunionId=" + this.wunion.getId();
    }
}
