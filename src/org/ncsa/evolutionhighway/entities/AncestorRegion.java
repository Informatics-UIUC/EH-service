package org.ncsa.evolutionhighway.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AncestorRegion {
    public long Start;
    public long End;
    public String Label;
    
    public AncestorRegion() {}
    
    public AncestorRegion(long start, long end, String label) {
        Start = start;
        End = end;
        Label = label;
    }
}
