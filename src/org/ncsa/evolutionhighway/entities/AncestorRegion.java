package org.ncsa.evolutionhighway.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AncestorRegion {
    public Long Start;
    public Long End;
    public Long ModStart;
    public Long ModEnd;
    public String Label;
    public Integer Sign;
    
    public AncestorRegion() {}
    
    public AncestorRegion(Long start, Long end, String label, Integer sign, Long modStart, Long modEnd) {
        Start = start;
        End = end;
        ModStart = modStart;
        ModEnd = modEnd;
        Label = label;
        Sign = sign;
    }
}
