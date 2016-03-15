// package org.ncsa.evolutionhighway.resources;
//
// import java.io.FileReader;
// import java.io.Reader;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Map.Entry;
//
// import javax.persistence.EntityManagerFactory;
// import javax.persistence.Persistence;
// import javax.ws.rs.GET;
// import javax.ws.rs.Path;
// import javax.ws.rs.PathParam;
// import javax.ws.rs.Produces;
// import javax.ws.rs.core.MediaType;
//
// import org.apache.commons.lang3.time.StopWatch;
// import org.ncsa.evolutionhighway.entities.AncestorRegion;
// import org.ncsa.evolutionhighway.entities.AncestralRegion;
// import org.ncsa.evolutionhighway.entities.BreakpointRegion;
//
// import com.googlecode.jcsv.CSVStrategy;
// import com.googlecode.jcsv.reader.CSVEntryParser;
// import com.googlecode.jcsv.reader.CSVReader;
// import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;
//
// //TODO: This is no longer needed here - has been implemented on the client
// // it is kept here just in case we ever need to move it server-side
//
// @Path("/compute/breakpoint/classification")
// @Produces({ MediaType.APPLICATION_JSON })
// public class ComputeBreakpointClassification {
//
//     public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EHService");
//
//     public class AncestorRegionEntryParser implements CSVEntryParser<AncestorRegion> {
//         public AncestorRegion parseEntry(String... data) {
//         	String refChr = data[1];
// 			long start = Long.parseLong(data[2]);
// 			long end = Long.parseLong(data[3]);
//
// 			return new AncestorRegion(start, end, refChr, null, null, null, null);
//         }
//     }
//
//     @GET
//     @Path("{refGens}/{refChrs}/{compGens}")
//     public List<BreakpointRegion> computeBreakpointClasses(
//     		@PathParam("refGens") String sRefGens,
//     		@PathParam("refChrs") String sRefChrs,
//     		@PathParam("compGens") String sCompGens) {
//
//     	String[] refGens = sRefGens.split(",");
//     	String[] refChrs = sRefChrs.split(",");
//     	String[] compGens = sCompGens.split(",");
//
//     	Map<String, Map<String, List<BreakpointRegion>>> breakpointMap = new HashMap<String, Map<String, List<BreakpointRegion>>>();
//
//     	// Step 1 - compute breakpoints
//     	for (String refGen : refGens) {
//     		for (String refChr : refChrs) {
//     			Map<String, List<BreakpointRegion>> compGenBrkptMap = new HashMap<String, List<BreakpointRegion>>();
//     			String key = String.format("%s,%s", refGen, refChr);
//     			breakpointMap.put(key, compGenBrkptMap);
//
//     			for (String compGen : compGens) {
//
//     				stopWatch.suspend();
//     				SynBlocksResource synBlocksRes = new SynBlocksResource(compGen, refChr, refGen);
//     				List<AncestorRegion> synBlocks = synBlocksRes.getSynBlocks();
//     				stopWatch.resume();
//
// //    				//System.out.println("Loading: " + refGen + "," + refChr + "," + compGen);
// //    				List<AncestorRegion> synBlocks = new ArrayList<AncestorRegion>();
// //    				try {
// //    					String fname = String.format("/Users/capitanu/Downloads/EH/EBA_PIG_500/EBA_input_files/%s_sorted.txt", compGen);
// //    					Reader reader = new FileReader(fname);
// //    					CSVStrategy strategy = new CSVStrategy('\t', '"', '#', false, true);
// //    					CSVReader<AncestorRegion> csvReader = new CSVReaderBuilder<AncestorRegion>(reader).entryParser(new AncestorRegionEntryParser()).strategy(strategy).build();
// //    					AncestorRegion data;
// //    					while ((data = csvReader.readNext()) != null) {
// //    						if (data.Label.equals(refChr))
// //    							synBlocks.add(data);
// //    					}
// //    				}
// //    				catch (Exception e) {
// //    					throw new RuntimeException(e);
// //    				}
//
//     				List<BreakpointRegion> breakPoints = getBreakpoints(synBlocks);
//     				compGenBrkptMap.put(compGen, breakPoints);
//     			}
//     		}
//     	}
//
//     	// Step 2 - compute breakpoint overlap
//     	for (Map.Entry<String, Map<String, List<BreakpointRegion>>> entry : breakpointMap.entrySet()) {
//     		computeAllOverlaps(entry);
//     	}
//
//     	// Step 3 - compute breakpoint decision
//     	for (Map.Entry<String, Map<String, List<BreakpointRegion>>> breakpointMapEntry : breakpointMap.entrySet()) {
// 	    	for (Map.Entry<String, List<BreakpointRegion>> entry : breakpointMapEntry.getValue().entrySet()) {
// 	    		for (BreakpointRegion breakpoint : entry.getValue()) {
// 	    			breakpoint.IsGap = isGap(breakpoint);
// 	    		}
// 	    	}
//     	}
//
//     	// Step 4 - compute breakpoint score
//     	for (Map.Entry<String, Map<String, List<BreakpointRegion>>> breakpointMapEntry : breakpointMap.entrySet()) {
// 	    	for (Map.Entry<String, List<BreakpointRegion>> entry : breakpointMapEntry.getValue().entrySet()) {
// 	    		for (BreakpointRegion breakpoint : entry.getValue()) {
// 	    			breakpoint.Score = computeScore(breakpoint, compGens.length-1);  // -1 because i'm not considering self-overlaps
// 	    			//System.out.println(breakpoint);
// 	    		}
// 	    	}
//     	}
//
//     	return null;
//     }
//
// 	private Float computeScore(BreakpointRegion breakpoint, int n) {
// 		float score = 0;
//
// 		List<BreakpointRegion> overlaps = new ArrayList<BreakpointRegion>();
// 		for (List<BreakpointRegion> breakpoints : breakpoint.Overlaps.values())
// 			for (BreakpointRegion overlap : breakpoints)
// 				overlaps.add(overlap);
//
// 		if (breakpoint.IsGap) {
// 			int nOverlaps = overlaps.size();
// 			score = -1;
//
// 		} else {
// 			for (BreakpointRegion overlap : overlaps)
// 				score += overlap.IsGap ? 0.5/n : 1/n;
// 		}
//
// 		return score;
// 	}
//
// 	private boolean isGap(BreakpointRegion breakpoint) {
// 		List<BreakpointRegion> overlaps = new ArrayList<BreakpointRegion>();
//
// 		// Rule 1 - the breakpoint must not overlap more than 1 breakpoint for each compGen
// 		for (Map.Entry<String, List<BreakpointRegion>> entry : breakpoint.Overlaps.entrySet()) {
// 			if (entry.getValue().isEmpty()) continue;
//
// 			if (entry.getValue().size() > 1)
// 				return true;
//
// 			overlaps.add(entry.getValue().get(0));
// 		}
//
// 		// Rule 2 - all breakpoint overlaps for the other compGens must themselves pairwise overlap
// 		for (BreakpointRegion breakpoint1 : overlaps) {
// 			for (BreakpointRegion breakpoint2 : overlaps) {
// 				if (getOverlap(breakpoint1, breakpoint2) == null)
// 					return true;
// 			}
// 		}
//
// 		return false;
// 	}
//
// 	private void computeAllOverlaps(Map.Entry<String, Map<String, List<BreakpointRegion>>> genChrBreakpointMap) {
// 		String genChr = genChrBreakpointMap.getKey();
// 		Map<String, List<BreakpointRegion>> compGenBreakpointMap = genChrBreakpointMap.getValue();
//
// 		//System.out.println("Computing breakpoint overlaps for: " + genChr);
//
// 		for (Map.Entry<String, List<BreakpointRegion>> entry1 : compGenBreakpointMap.entrySet()) {
// 			for (Map.Entry<String, List<BreakpointRegion>> entry2 : compGenBreakpointMap.entrySet()) {
// 				if (entry1.getKey().compareTo(entry2.getKey()) < 0) {
// 					computeOverlap(entry1, entry2);
// 				}
// 			}
// 		}
// 	}
//
// 	private void computeOverlap(Entry<String, List<BreakpointRegion>> entry1, Entry<String, List<BreakpointRegion>> entry2) {
// 		String compGen1 = entry1.getKey();
// 		String compGen2 = entry2.getKey();
//
// 		//System.out.println("Computing overlap between " + compGen1 + " and " + compGen2);
//
// 		List<BreakpointRegion> breakpoints1 = entry1.getValue();
// 		List<BreakpointRegion> breakpoints2 = entry2.getValue();
//
// 		for (BreakpointRegion breakpoint1 : breakpoints1) {
// 			List<BreakpointRegion> overlaps1 = breakpoint1.Overlaps.get(compGen2);
// 			if (overlaps1 == null) {
// 				overlaps1 = new ArrayList<BreakpointRegion>();
// 				breakpoint1.Overlaps.put(compGen2, overlaps1);
// 			}
//
// 			for (BreakpointRegion breakpoint2 : breakpoints2) {
// 				List<BreakpointRegion> overlaps2 = breakpoint2.Overlaps.get(compGen1);
// 				if (overlaps2 == null) {
// 					overlaps2 = new ArrayList<BreakpointRegion>();
// 					breakpoint2.Overlaps.put(compGen1, overlaps2);
// 				}
//
// 				AncestralRegion overlap = getOverlap(breakpoint1, breakpoint2);
// 				if (overlap != null) {
// 					overlaps1.add(breakpoint2);
// 					overlaps2.add(breakpoint1);
// 				}
// 			}
// 		}
// 	}
//
// 	private AncestralRegion getOverlap(BreakpointRegion breakpoint1, BreakpointRegion breakpoint2) {
// 		long start = Math.max(breakpoint1.Start, breakpoint2.Start);
// 		long end = Math.min(breakpoint1.End, breakpoint2.End);
//
// 		return start < end ? new AncestralRegion(start, end) : null;
// 	}
//
// 	private List<BreakpointRegion> getBreakpoints(List<AncestorRegion> synBlocks) {
// 		List<BreakpointRegion> breakpoints = new ArrayList<BreakpointRegion>();
// 		for (int i = 0; i < synBlocks.size()-1; i++) {
// 			AncestorRegion synBlock1 = synBlocks.get(i);
// 			AncestorRegion synBlock2 = synBlocks.get(i+1);
// 			if (synBlock2.Start > synBlock1.End)
// 				breakpoints.add(new BreakpointRegion(synBlock1.End, synBlock2.Start));
// 		}
//
// 		return breakpoints;
// 	}
//
// 	static StopWatch stopWatch = new StopWatch();
//
// 	public static void main(String[] args) throws Exception {
// 		ComputeBreakpointClassification cbc = new ComputeBreakpointClassification();
//
// 		stopWatch.start();
// 		List<BreakpointRegion> regions = cbc.computeBreakpointClasses("Pig:500Kb", "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,X", "Cattle,Dog,Horse,Human,Macaque,Orangutan,Rat");
// 		stopWatch.stop();
// 		System.out.println("Elapsed time: " + stopWatch);
//
// 		if (regions == null) return;
//
// 		for (BreakpointRegion region : regions) {
// 			System.out.println(region);
// 		}
// 	}
//
// }
