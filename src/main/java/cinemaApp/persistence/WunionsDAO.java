package cinemaApp.persistence;

import cinemaApp.entities.Wunion;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class WunionsDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Wunion> loadAll() {
        return em.createNamedQuery("Wunion.findAll", Wunion.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Wunion wunion){
        this.em.persist(wunion);
    }

    public Wunion findOne(Integer id) {
        return em.find(Wunion.class, id);
    }
}
