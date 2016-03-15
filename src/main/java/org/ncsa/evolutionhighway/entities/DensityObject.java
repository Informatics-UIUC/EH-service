package org.ncsa.evolutionhighway.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DensityObject {
    public Long RefStart;
    public Long RefEnd;
    public float Score;
    public String CompGen;

    public DensityObject() {}

    public DensityObject(Long refStart, Long refEnd, float score, String compGen) {
        RefStart = refStart;
        RefEnd = refEnd;
        Score = score;
        CompGen = compGen;
    }
}
