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

import org.ncsa.evolutionhighway.entities.Genome;

@Path("/genomes/")
@Produces({ MediaType.APPLICATION_JSON })
public class GenomesResource {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EHService");
    
    private List<String> getGenomesFromDB() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("Consensus.getGenomes", String.class).getResultList();
        }
        finally {
            em.close();
        }
    }

    @GET
    public List<Genome> getGenomes() {
        List<Genome> genomes = new ArrayList<Genome>();
        for (String refGen : getGenomesFromDB())
            genomes.add(new Genome(refGen));
        
        return genomes;
    }

    @Path("{genomeId}")
    public GenomeResource getGenome(@PathParam("genomeId") String genomeId) {
        return new GenomeResource(genomeId);
    }
}
