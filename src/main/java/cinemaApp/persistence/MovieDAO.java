package cinemaApp.persistence;

import cinemaApp.entities.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class MovieDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Movie> loadAll() {
        return em.createNamedQuery("Movie.findAll", Movie.class).getResultList();
    }
    public List<Movie> findByName(String name) {
        //return em.createNamedQuery("Movie.findByName", Movie.class).getResultList();
        return em.createQuery(
                "SELECT u FROM Movie u "+
                        " WHERE u.name = :mname")
                .setParameter("mname", name).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Movie movie){
        this.em.merge(movie);
    }

    public Movie findOne(Integer id) {
        return em.find(Movie.class, id);
    }
}
