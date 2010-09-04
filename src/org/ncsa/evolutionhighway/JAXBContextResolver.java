package org.ncsa.evolutionhighway;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

import org.ncsa.evolutionhighway.entities.AncestorRegion;
import org.ncsa.evolutionhighway.entities.Chromosome;
import org.ncsa.evolutionhighway.entities.ComparativeSpecies;
import org.ncsa.evolutionhighway.entities.Genome;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;

@Provider
public class JAXBContextResolver implements ContextResolver<JAXBContext> {

    private final JAXBContext context;
    private final Class<?>[] types = {
            Genome.class, Chromosome.class, ComparativeSpecies.class, AncestorRegion.class
    };

    public JAXBContextResolver() throws Exception {
        context = new JSONJAXBContext(JSONConfiguration.natural().build(), types);
    }

    public JAXBContext getContext(Class<?> objectType) {
        for (Class<?> c : types) {
            if (c.equals(objectType)) {
                return context;
            }
        }
        
        return null;
    }
}