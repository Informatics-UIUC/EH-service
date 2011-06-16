package org.ncsa.evolutionhighway.resources;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ncsa.evolutionhighway.entities.SpeciesChrLengths;

@Path("/species/{speciesId}/chromosomesLength")
@Produces({ MediaType.APPLICATION_JSON })
public class ChromosomeLengthsResource {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EHService");

    @GET
    public List<SpeciesChrLengths> getAllLengthsFromDB(@PathParam("speciesId") String speciesId) {
        EntityManager em = emf.createEntityManager();
        try {
            List<Object[]> list = em.createNamedQuery("ChromosomeSize.getSpeciesChrSize", Object[].class)
                .setParameter(1, speciesId)
                .getResultList();
            List<SpeciesChrLengths> lstLengths = new ArrayList<SpeciesChrLengths>(list.size());
            for (Object[] o : list) {
                lstLengths.add(new SpeciesChrLengths((String)o[0], (Long)o[1]));
            }
            return lstLengths;
        }
        finally {
            em.close();
        }
    }
}
