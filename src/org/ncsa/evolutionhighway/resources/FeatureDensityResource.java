package org.ncsa.evolutionhighway.resources;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ncsa.evolutionhighway.entities.DensityObject;
import org.ncsa.evolutionhighway.entities.Feature;

@Path("/features/density/")
@Produces({ MediaType.APPLICATION_JSON })
public class FeatureDensityResource {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EHService");

    public List<String> getAvailableFeaturesFromDB() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("FeatureDensity.getAvailableFeatures", String.class).getResultList();
        }
        finally {
            em.close();
        }
    }

    private List<Object[]> getDensityDataForFeatureFromDB(String featureName, String refGen, String refChr) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("FeatureDensity.getFeatureData", Object[].class)
                .setParameter("featureName", featureName)
                .setParameter("refGen", refGen)
                .setParameter("refChr", refChr)
                .getResultList();
        }
        finally {
            em.close();
        }
    }

    @GET
    public List<Feature> getAvailableFeatures() {
        List<Feature> features = new ArrayList<Feature>();
        for (String refGen : getAvailableFeaturesFromDB())
            features.add(new Feature(refGen));

        return features;
    }

    @GET
    @Path("{featureName}/{refGen}/{refChr}")
    public List<DensityObject> getDensityDataForFeature(@PathParam("featureName") String featureName, @PathParam("refGen") String refGen, @PathParam("refChr") String refChr) {
        List<DensityObject> densityData = new ArrayList<DensityObject>();
        for (Object[] densityObj : getDensityDataForFeatureFromDB(featureName, refGen, refChr))
            densityData.add(new DensityObject((Long)densityObj[0], (Long)densityObj[1], (Float)densityObj[2], (String)densityObj[3]));

        return densityData;
    }
}
