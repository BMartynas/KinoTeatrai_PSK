package cinemaApp.usecases;
import cinemaApp.interceptors.LoggedInvocation;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import cinemaApp.entities.Cinema;
import cinemaApp.persistence.CinemasDAO;

@Model
public class Cinemas implements Serializable {

    @Inject
    private CinemasDAO cinemasDAO;

    private Cinema cinemaToCreate = new Cinema();

    private List<Cinema> allCinemas;
    @PostConstruct
    public void init(){
        loadCinemas();
    }

    public void loadCinemas() {
        this.allCinemas = cinemasDAO.loadAll();
    }

    public List<Cinema> getAllCinemas() {
        return allCinemas;
    }

    @Transactional
    @LoggedInvocation
    public String createCinema() {
        this.cinemasDAO.persist(cinemaToCreate);
        return "index?faces-redirect=true";
    }

    public Cinema getCinemaToCreate() {
        return cinemaToCreate;
    }

    public void setCinemaToCreate(Cinema cinemaToCreate) {
        this.cinemaToCreate = cinemaToCreate;
    }
}
