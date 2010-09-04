package org.ncsa.evolutionhighway.resources;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ncsa.evolutionhighway.entities.AncestorRegion;

@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class SynBlocksResource {

    private final String _speciesId;
    private final String _chrId;
    private final String _genomeId;
    private final EntityManager _em;
    
    public SynBlocksResource(String speciesId, String chrId, String genomeId, EntityManager em) {
        _speciesId = speciesId;
        _chrId = chrId;
        _genomeId = genomeId;
        _em = em;
    }

    private List<Object[]> getSynBlocksFromDB() {
        try {
             return _em.createNamedQuery("Consensus.getSynBlocks", Object[].class)
                .setParameter("genomeId", _genomeId)
                .setParameter("chrId", _chrId)
                .setParameter("speciesId", _speciesId)
                .getResultList();
        }
        finally {
            _em.close();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AncestorRegion> getSynBlocks() {
        List<AncestorRegion> ancestorRegions = new ArrayList<AncestorRegion>();
        for (Object[] synBlock : getSynBlocksFromDB())
            ancestorRegions.add(new AncestorRegion((Long)synBlock[0], (Long)synBlock[1], (String)synBlock[2]));

        return ancestorRegions;
    }
}
