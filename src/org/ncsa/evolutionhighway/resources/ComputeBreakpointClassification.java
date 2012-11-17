package org.ncsa.evolutionhighway.resources;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ncsa.evolutionhighway.entities.BreakpointRegion;

@Path("/compute/breakpoint/classification")
@Produces({ MediaType.APPLICATION_JSON })
public class ComputeBreakpointClassification {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EHService");

    @GET
    @Path("{refGens}/{refChrs}/{compGens}")
    public List<BreakpointRegion> computeBreakpointClasses(
    		@PathParam("refGens") String refGens,
    		@PathParam("refChrs") String refChrs,
    		@PathParam("compGens") String compGens) {

    	System.out.println("refGens: " + refGens);
    	System.out.println("refChrs: " + refChrs);
    	System.out.println("compGens: " + compGens);

    	List<BreakpointRegion> result = new ArrayList<BreakpointRegion>();
    	result.add(new BreakpointRegion(0L, 100L));

    	return result;
    }

}
