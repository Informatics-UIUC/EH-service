package org.ncsa.evolutionhighway.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Chromosome {
    public String Name;
    public List<ComparativeSpecies> ComparativeSpecies;
    
    public Chromosome() {}
    
    public Chromosome(String name) {
        Name = name;
    }
}
