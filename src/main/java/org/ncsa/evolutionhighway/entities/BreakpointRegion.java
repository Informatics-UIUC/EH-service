// package org.ncsa.evolutionhighway.entities;
//
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
//
// import javax.xml.bind.annotation.XmlRootElement;
//
// //TODO: This class is used in resources/ComputeBreakpointClassification.java but
// //      is no longer needed at the moment since the processing was moved to the client
// @XmlRootElement
// public class BreakpointRegion extends AncestralRegion {
//
// 	public Boolean IsGap;
// 	public Float Score;
// 	public Map<String, List<BreakpointRegion>> Overlaps;
//
// 	public BreakpointRegion() { }
//
// 	public BreakpointRegion(Long start, Long end) {
// 		super(start, end);
//
// 		IsGap = null;
// 		Score = null;
// 		Overlaps = new HashMap<String, List<BreakpointRegion>>();
// 	}
//
// 	@Override
// 	public String toString() {
// 		return String.format("{start: %,d  end: %,d  isGap: %s  score: %f}", Start, End, IsGap, Score);
// 	}
// }
