package org.ncsa.evolutionhighway.resources;

import javax.ws.rs.Path;


public class ChromosomeResource {
    
    private final String _chrId;
    private final String _genomeId;

    public ChromosomeResource(String chrId, String genomeId) {
        _chrId = chrId;
        _genomeId = genomeId;
    }

    @Path("species")
    public SpeciesResource getSpecies() {
        return new SpeciesResource(_chrId, _genomeId);
    }
}
