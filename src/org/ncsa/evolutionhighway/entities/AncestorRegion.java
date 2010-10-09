package org.ncsa.evolutionhighway.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AncestorRegion {
    public Long Start;
    public Long End;
    public String Label;
    public Integer Sign;
    
    public AncestorRegion() {}
    
    public AncestorRegion(Long start, Long end, String label, Integer sign) {
        Start = start;
        End = end;
        Label = label;
        Sign = sign;
    }
}
