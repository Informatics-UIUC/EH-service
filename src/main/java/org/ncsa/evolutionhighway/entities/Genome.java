package org.ncsa.evolutionhighway.entities;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Genome {
    public String Name;
    
    @XmlElement(nillable=false)
    public List<Chromosome> Chromosomes;

    public Genome() {}

    public Genome(String name) {
        Name = name;
    }
}
