package org.ncsa.evolutionhighway.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CentromereRegion extends AncestralRegion {

    public CentromereRegion() {
    }

    public CentromereRegion(Long start, Long end) {
        super(start, end);
    }

}
