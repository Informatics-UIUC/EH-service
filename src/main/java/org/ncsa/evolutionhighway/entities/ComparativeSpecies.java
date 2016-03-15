package org.ncsa.evolutionhighway.entities;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class ComparativeSpecies {
    public String SpeciesName;

    @XmlElement(nillable=false)
    public List<AncestorRegion> AncestorRegions;

    public ComparativeSpecies() {}

    public ComparativeSpecies(String speciesName) {
        SpeciesName = speciesName;
    }
}
