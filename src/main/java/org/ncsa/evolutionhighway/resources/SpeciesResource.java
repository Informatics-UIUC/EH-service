package org.ncsa.evolutionhighway.resources;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ncsa.evolutionhighway.entities.ComparativeSpecies;
import org.ncsa.evolutionhighway.EMF;


@Produces({ MediaType.APPLICATION_JSON })
public class SpeciesResource {

    private final String _chrId;
    private final String _genomeId;

    public SpeciesResource(String chrId, String genomeId) {
        _chrId = chrId;
        _genomeId = genomeId;
    }

    private List<String> getSpeciesFromDB() {
        EntityManager em = EMF.createEntityManager();
        try {
            return em.createNamedQuery("Consensus.getSpecies", String.class)
                .setParameter("genomeId", _genomeId)
                .setParameter("chrId", _chrId)
                .getResultList();
        }
        finally {
            em.close();
        }
    }

    @GET
    public List<ComparativeSpecies> getSpecies() {
        List<ComparativeSpecies> species = new ArrayList<ComparativeSpecies>();
        for (String speciesName : getSpeciesFromDB())
            species.add(new ComparativeSpecies(speciesName));

        return species;
    }

    @Path("{speciesId}")
    public SingleSpeciesResource getChromosome(@PathParam("speciesId") String speciesId) {
        return new SingleSpeciesResource(speciesId, _chrId, _genomeId);
    }
}
