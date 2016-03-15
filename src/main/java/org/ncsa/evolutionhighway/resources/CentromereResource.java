package org.ncsa.evolutionhighway.resources;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ncsa.evolutionhighway.entities.CentromereRegion;
import org.ncsa.evolutionhighway.EMF;


@Produces({ MediaType.APPLICATION_JSON })
public class CentromereResource {

    private final String _chrId;
    private final String _genomeId;

    public CentromereResource(String chrId, String genomeId) {
        _chrId = chrId;
        _genomeId = genomeId;
    }

    private List<Object[]> getCentromereFromDB() {
        EntityManager em = EMF.createEntityManager();
        try {
            return em.createNamedQuery("RefGenInfo.getCentromereRegions", Object[].class)
                .setParameter("genomeId", _genomeId)
                .setParameter("chrId", _chrId)
                .getResultList();
        }
        finally {
            em.close();
        }
    }

    @GET
    public List<CentromereRegion> getCentromereRegions() {
        List<CentromereRegion> centromereRegions = new ArrayList<CentromereRegion>();
        for (Object[] o : getCentromereFromDB())
            centromereRegions.add(new CentromereRegion((Long)o[0], (Long)o[1]));

        return centromereRegions;
    }
}
