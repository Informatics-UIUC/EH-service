package org.ncsa.evolutionhighway.entities;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Chromosome {
    public String Name;
    public Long Length;

    @XmlElement(nillable=false)
    public List<ComparativeSpecies> ComparativeSpecies;

    public Chromosome() {}

    public Chromosome(String name, Long length) {
        Name = name;
        Length = length;
    }
}
