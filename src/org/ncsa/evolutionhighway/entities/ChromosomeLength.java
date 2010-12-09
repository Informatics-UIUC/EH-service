package org.ncsa.evolutionhighway.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChromosomeLength {
    
    public Long Length;
    
    public ChromosomeLength() {}
    
    public ChromosomeLength(Long length) {
        Length = length;
    }

}
