package org.ncsa.evolutionhighway.resources;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ncsa.evolutionhighway.entities.ChromosomeLength;

@Path("/lengths/{speciesId}/{chrId}")
@Produces({ MediaType.APPLICATION_JSON })
public class ChromosomeLengthsResource {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EHService");
    
    private Long getLengthFromDB(String speciesId, String chrId) {
        EntityManager em = emf.createEntityManager();
        try {
            return Long.valueOf(em.createNamedQuery("Consensus.getLengths", Integer.class)
                .setParameter(1, speciesId)
                .setParameter(2, chrId + "%")
                .getSingleResult());
        }
        finally {
            em.close();
        }
    }

    @GET
    public ChromosomeLength getChromosomeLength(@PathParam("speciesId") String speciesId, @PathParam("chrId") String chrId) {
        return new ChromosomeLength(getLengthFromDB(speciesId, chrId));
    }
}
