package org.ncsa.evolutionhighway.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HeterochromatinRegion extends AncestralRegion {

    public HeterochromatinRegion() {
    }

    public HeterochromatinRegion(Long start, Long end) {
        super(start, end);
    }

}
