package org.ncsa.evolutionhighway.resources;

import javax.persistence.EntityManager;
import javax.ws.rs.Path;


public class GenomeResource {

    private final String _genomeId;
    private final EntityManager _em;
    
    public GenomeResource(String genomeId, EntityManager em) {
        _genomeId = genomeId;
        _em = em;
    }
    
    @Path("chromosomes")
    public ChromosomesResource getChromosomes() {
        return new ChromosomesResource(_genomeId, _em);
    }
}
