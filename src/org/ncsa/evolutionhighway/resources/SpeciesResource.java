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

@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class SpeciesResource {
    
    private final String _chrId;
    private final String _genomeId;
    private final EntityManager _em;

    public SpeciesResource(String chrId, String genomeId, EntityManager em) {
        _chrId = chrId;
        _genomeId = genomeId;
        _em = em;
    }

    private List<String> getSpeciesFromDB() {
        try {
            return _em.createNamedQuery("Consensus.getSpecies", String.class)
                .setParameter("genomeId", _genomeId)
                .setParameter("chrId", _chrId)
                .getResultList();
        }
        finally {
            _em.close();
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
        return new SingleSpeciesResource(speciesId, _chrId, _genomeId, _em);
    }
}
