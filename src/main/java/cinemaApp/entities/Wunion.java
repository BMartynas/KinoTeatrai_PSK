package cinemaApp.entities;

import javax.persistence.*;
import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@NamedQueries({
        @NamedQuery(name = "Wunion.findAll", query = "select u from Wunion as u")
})
@Table(name = "WUNION")
@Getter @Setter
public class Wunion {

    public Wunion() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "wunion", fetch = FetchType.EAGER)
    private Set<Cinema> cinemas = new HashSet<>();;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wunion wunion = (Wunion) o;
        return Objects.equals(name, wunion.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
