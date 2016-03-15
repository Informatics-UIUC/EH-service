package org.ncsa.evolutionhighway.resources;

import javax.ws.rs.Path;


public class SingleSpeciesResource {

    private final String _speciesId;
    private final String _chrId;
    private final String _genomeId;

    public SingleSpeciesResource(String speciesId, String chrId, String genomeId) {
        _speciesId = speciesId;
        _chrId = chrId;
        _genomeId = genomeId;
    }

    @Path("synblocks")
    public SynBlocksResource getSpecies() {
        return new SynBlocksResource(_speciesId, _chrId, _genomeId);
    }
}
