package cinemaApp.rest;
import cinemaApp.entities.Wunion;
import cinemaApp.persistence.CinemasDAO;
import cinemaApp.entities.Cinema;
import cinemaApp.persistence.WunionsDAO;
import cinemaApp.rest.contracts.CinemaDto;

import lombok.*;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/cinemas")
public class CinemasController {
    @Inject
    @Setter @Getter
    private CinemasDAO cinemasDAO;
    @Inject
    @Setter @Getter
    private WunionsDAO wunionsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Cinema cinema = cinemasDAO.findOne(id);
        if (cinema == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CinemaDto cinemaDto = new CinemaDto();
        cinemaDto.setName(cinema.getName());
        cinemaDto.setWunionName(cinema.getWunion().getName());

        return Response.ok(cinemaDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer cinemaId, String newData) {
        try {
            Cinema existingCinema = cinemasDAO.findOne(cinemaId);
            if (existingCinema == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingCinema.setName(newData);
            cinemasDAO.update(existingCinema);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @Path("/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(@PathParam("id") final Integer wunionId,final String name) {
        try {
            Cinema newCinema = new Cinema();
            Wunion existingWunion = wunionsDAO.findOne(wunionId);
            if (existingWunion == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            newCinema.setName(name);
            newCinema.setWunion(existingWunion);

            cinemasDAO.persist(newCinema);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

}
