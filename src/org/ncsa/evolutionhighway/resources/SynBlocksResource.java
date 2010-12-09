package org.ncsa.evolutionhighway.resources;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ncsa.evolutionhighway.entities.AncestorRegion;

@Produces({ MediaType.APPLICATION_JSON })
public class SynBlocksResource {

    private final String _speciesId;
    private final String _chrId;
    private final String _genomeId;
    
    public SynBlocksResource(String speciesId, String chrId, String genomeId) {
        _speciesId = speciesId;
        _chrId = chrId;
        _genomeId = genomeId;
    }

    private List<Object[]> getSynBlocksFromDB() {
        EntityManager em = GenomesResource.emf.createEntityManager();
        try {
             return em.createNamedQuery("Consensus.getSynBlocks", Object[].class)
                .setParameter("genomeId", _genomeId)
                .setParameter("chrId", _chrId)
                .setParameter("speciesId", _speciesId)
                .getResultList();
        }
        finally {
            em.close();
        }
    }
    
    @GET
    public List<AncestorRegion> getSynBlocks() {
        List<AncestorRegion> ancestorRegions = new ArrayList<AncestorRegion>();
        for (Object[] synBlock : getSynBlocksFromDB())
            ancestorRegions.add(new AncestorRegion((Long)synBlock[0], (Long)synBlock[1], (String)synBlock[2], (Integer)synBlock[3], (Long)synBlock[4], (Long)synBlock[5]));

        return ancestorRegions;
    }
}
