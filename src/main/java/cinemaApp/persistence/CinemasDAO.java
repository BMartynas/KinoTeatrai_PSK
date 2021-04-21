package cinemaApp.persistence;

import cinemaApp.entities.Cinema;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class CinemasDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Cinema> loadAll() {
        return em.createNamedQuery("Cinema.findAll", Cinema.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Cinema cinema) {
        this.em.persist(cinema);
    }

    public Cinema findOne(int id) {
        return em.find(Cinema.class, id);
    }
    public Cinema update(Cinema cinema){
        return em.merge(cinema);
    }
}
