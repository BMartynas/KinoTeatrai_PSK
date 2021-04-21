package cinemaApp.entities;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@NamedQueries({
        @NamedQuery(name = "Movie.findAll", query = "select m from Movie as m"),
        @NamedQuery(name = "Movie.findByName", query = "SELECT u FROM Movie u WHERE u.name = :name"),
})
@Table(name = "MOVIE")
@Getter @Setter
public class Movie  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "YEAR")
    private Integer year;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "MOVIE_CINEMA", joinColumns = {
            @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "CINEMA_ID", referencedColumnName = "ID")
            })
    //@ManyToMany(mappedBy = "movieList",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Cinema> cinemaList = new HashSet<>();
}
