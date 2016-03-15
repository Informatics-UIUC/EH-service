package org.ncsa.evolutionhighway.resources;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ncsa.evolutionhighway.entities.HeterochromatinRegion;
import org.ncsa.evolutionhighway.EMF;


@Produces({ MediaType.APPLICATION_JSON })
public class HeterochromatinResource {
    private final String _chrId;
    private final String _genomeId;

    public HeterochromatinResource(String chrId, String genomeId) {
        _chrId = chrId;
        _genomeId = genomeId;
    }

    private List<Object[]> getHeterochromatinFromDB() {
        EntityManager em = EMF.createEntityManager();
        try {
            return em.createNamedQuery("RefGenInfo.getHeterochromatinRegions", Object[].class)
                .setParameter("genomeId", _genomeId)
                .setParameter("chrId", _chrId)
                .getResultList();
        }
        finally {
            em.close();
        }
    }

    @GET
    public List<HeterochromatinRegion> getCentromereRegions() {
        List<HeterochromatinRegion> heterochromatinRegions = new ArrayList<HeterochromatinRegion>();
        for (Object[] o : getHeterochromatinFromDB())
            heterochromatinRegions.add(new HeterochromatinRegion((Long)o[0], (Long)o[1]));

        return heterochromatinRegions;
    }
}
