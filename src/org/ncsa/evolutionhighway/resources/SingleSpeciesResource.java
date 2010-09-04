package org.ncsa.evolutionhighway.resources;

import javax.persistence.EntityManager;
import javax.ws.rs.Path;


public class SingleSpeciesResource {

    private final String _speciesId;
    private final String _chrId;
    private final String _genomeId;
    private final EntityManager _em;

    public SingleSpeciesResource(String speciesId, String chrId, String genomeId, EntityManager em) {
        _speciesId = speciesId;
        _chrId = chrId;
        _genomeId = genomeId;
        _em = em;
    }

    @Path("synblocks")
    public SynBlocksResource getSpecies() {
        return new SynBlocksResource(_speciesId, _chrId, _genomeId, _em);
    }
}
