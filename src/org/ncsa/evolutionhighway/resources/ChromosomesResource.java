package org.ncsa.evolutionhighway.resources;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ncsa.evolutionhighway.entities.Chromosome;

@Produces({ MediaType.APPLICATION_JSON })
public class ChromosomesResource {

    private final String _genomeId;

    public ChromosomesResource(String genomeId) {
        _genomeId = genomeId;
    }

    private List<Object[]> getChromosomesFromDB() {
        EntityManager em = GenomesResource.emf.createEntityManager();
        try {
            return em.createNamedQuery("Consensus.getChromosomes", Object[].class)
                .setParameter(1, _genomeId)
                .getResultList();
        }
        finally {
            em.close();
        }
    }

    @GET
    public List<Chromosome> getChromosomes() {
        List<Chromosome> chromosomes = new ArrayList<Chromosome>();
        for (Object[] o : getChromosomesFromDB())
            chromosomes.add(new Chromosome((String)o[0], (Long)o[1]));

        return chromosomes;
    }

    @Path("{chrId}")
    public ChromosomeResource getChromosome(@PathParam("chrId") String chrId) {
        return new ChromosomeResource(chrId, _genomeId);
    }
}
