package org.ncsa.evolutionhighway.resources;

import javax.ws.rs.Path;

public class GenomeResource {

    private final String _genomeId;
    
    public GenomeResource(String genomeId) {
        _genomeId = genomeId;
    }
    
    @Path("chromosomes")
    public ChromosomesResource getChromosomes() {
        return new ChromosomesResource(_genomeId);
    }
}
