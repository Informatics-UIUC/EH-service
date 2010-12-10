package org.ncsa.evolutionhighway.resources;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ncsa.evolutionhighway.entities.SpeciesChrLengths;

@Path("/speciesChromosomeLengths")
@Produces({ MediaType.APPLICATION_JSON })
public class ChromosomeLengthsResource {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EHService");
//    
//    private Long getLengthFromDB(String speciesId, String chrId) {
//        EntityManager em = emf.createEntityManager();
//        try {
//            return Long.valueOf(em.createNamedQuery("Consensus.getLengths", Integer.class)
//                .setParameter(1, speciesId)
//                .setParameter(2, chrId + "%")
//                .getSingleResult());
//        }
//        finally {
//            em.close();
//        }
//    }
//   
    @GET
    public List<SpeciesChrLengths> getAllLengthsFromDB() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Object[]> list = em.createNamedQuery("Consensus.getAllLengths", Object[].class).getResultList();
            List<SpeciesChrLengths> lstLengths = new ArrayList<SpeciesChrLengths>(list.size());
            for (Object[] o : list) {
                lstLengths.add(new SpeciesChrLengths((String)o[0], (String)o[1], Long.valueOf((Integer)o[2])));
            }
            return lstLengths;
        }
        finally {
            em.close();
        }
    }
  
//    
//    public ChromosomeLength getChromosomeLengths(@PathParam("speciesId") String speciesId, @PathParam("chrId") String chrId) {
//        return null; //new ChromosomeLength(getLengthFromDB(speciesId, chrId));
//    }
}
