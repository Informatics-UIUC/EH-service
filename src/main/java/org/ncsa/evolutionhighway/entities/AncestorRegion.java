package org.ncsa.evolutionhighway.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AncestorRegion extends AncestralRegion {
    public Long ModStart;
    public Long ModEnd;
    public String Label;
    public String Chromosome;
    public Integer Sign;

    public AncestorRegion() {}

    public AncestorRegion(Long start, Long end, String label, Integer sign, Long modStart, Long modEnd, String chromosome) {
        super(start, end);

        ModStart = modStart;
        ModEnd = modEnd;
        Label = label;
        Sign = sign;
        Chromosome = chromosome;
    }

    @Override
    public String toString() {
    	return String.format("{start: %,d  end: %,d  label: %s  sign: %d  chr: %s}", Start, End, Label, Sign, Chromosome);
    }
}
