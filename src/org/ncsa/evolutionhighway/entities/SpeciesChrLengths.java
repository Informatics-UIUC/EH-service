package org.ncsa.evolutionhighway.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SpeciesChrLengths {
    
    public String Species;
    public String Chromosome;
    public Long Length;
    
    public SpeciesChrLengths() {}
    
    public SpeciesChrLengths(String species, String chromosome, Long length) {
        Species = species;
        Chromosome = chromosome;
        Length = length;
    }
}
