package cinemaApp.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@NamedQueries({
        @NamedQuery(name = "Cinema.findAll", query = "select DISTINCT c from Cinema as c"),
})
@Table(name = "CINEMA")
@Getter @Setter
public class Cinema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name="WUNION_ID")
    private Wunion wunion;

    @ManyToMany(mappedBy = "cinemaList",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Movie> movieList = new HashSet<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Cinema() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return Objects.equals(id, cinema.id) &&
                Objects.equals(name, cinema.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
