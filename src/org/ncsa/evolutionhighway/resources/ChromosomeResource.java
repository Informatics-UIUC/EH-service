package org.ncsa.evolutionhighway.resources;

import javax.persistence.EntityManager;
import javax.ws.rs.Path;


public class ChromosomeResource {
    
    private final String _chrId;
    private final String _genomeId;
    private final EntityManager _em;

    public ChromosomeResource(String chrId, String genomeId, EntityManager em) {
        _chrId = chrId;
        _genomeId = genomeId;
        _em = em;
    }

    @Path("species")
    public SpeciesResource getSpecies() {
        return new SpeciesResource(_chrId, _genomeId, _em);
    }
}
