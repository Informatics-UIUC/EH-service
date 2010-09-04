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

@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class ChromosomesResource {
    
    private final String _genomeId;
    private final EntityManager _em;

    public ChromosomesResource(String genomeId, EntityManager em) {
        _genomeId = genomeId;
        _em = em;
    }

    private List<String> getChromosomesFromDB() {
        try {
            return _em.createNamedQuery("Consensus.getChromosomes", String.class)
                .setParameter("genomeId", _genomeId)
                .getResultList();
        }
        finally {
            _em.close();
        }
    }
    
    @GET
    public List<Chromosome> getChromosomes() {
        List<Chromosome> chromosomes = new ArrayList<Chromosome>();
        for (String refChr : getChromosomesFromDB())
            chromosomes.add(new Chromosome(refChr));
        
        return chromosomes;
    }
    
    @Path("{chrId}")
    public ChromosomeResource getChromosome(@PathParam("chrId") String chrId) {
        return new ChromosomeResource(chrId, _genomeId, _em);
    }
}
