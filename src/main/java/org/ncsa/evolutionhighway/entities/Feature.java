package org.ncsa.evolutionhighway.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Feature {
    public String Name;

    public Feature() {}

    public Feature(String name) {
        Name = name;
    }
}
