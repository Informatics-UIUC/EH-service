package org.ncsa.evolutionhighway.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SpeciesChrLengths {

    public String Chromosome;
    public Long Length;

    public SpeciesChrLengths() {}

    public SpeciesChrLengths(String chromosome, Long length) {
        Chromosome = chromosome;
        Length = length;
    }
}
