package org.ncsa.evolutionhighway.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The persistent class for the CONSENSUS database table.
 * 
 */

@SuppressWarnings("serial")
@IdClass(ConsensusPK.class)
@Entity
@Table(name="CONSENSUS")
@XmlRootElement
@XmlType(propOrder = { "refGen", "refChr", "species", "speciesChr", "startBp", "endBp" })
@NamedQueries({
        @NamedQuery(name = "Consensus.getGenomes", query = "SELECT DISTINCT c.refGen FROM Consensus c ORDER BY c.refGen"),
        @NamedQuery(name = "Consensus.getChromosomes", query = "SELECT DISTINCT c.refChr FROM Consensus c WHERE c.refGen = :genomeId"),
        @NamedQuery(name = "Consensus.getSpecies", query = "SELECT DISTINCT c.species FROM Consensus c WHERE c.refGen = :genomeId AND c.refChr = :chrId ORDER BY c.species"),
        @NamedQuery(name = "Consensus.getSynBlocks", query = "SELECT c.startBp, c.endBp, c.speciesChr FROM Consensus c WHERE c.refGen = :genomeId AND c.refChr = :chrId AND c.species = :speciesId ORDER BY c.startBp"),
})
public class Consensus implements Serializable {

    @Id 
    @Column(name="REF_GEN")
    String refGen;
    
    @Id 
    @Column(name="REF_CHR")
    String refChr;
    
    @Id 
    @Column(name="SPECIES")
    String species;
    
    @Id 
    @Column(name="START_BP")
    long startBp;
    
    @Id 
    @Column(name="END_BP")
    long endBp;
 
	@Column(name="SPECIES_CHR", insertable=false, updatable=false)
	private String speciesChr;


    public Consensus() {
    }
    
    public String getRefGen() {
        return this.refGen;
    }

    public void setRefGen(String refGen) {
        this.refGen = refGen;
    }

    public String getRefChr() {
        return this.refChr;
    }

    public void setRefChr(String refChr) {
        this.refChr = refChr;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public long getStartBp() {
        return this.startBp;
    }

    public void setStartBp(long startBp) {
        this.startBp = startBp;
    }

    public long getEndBp() {
        return this.endBp;
    }

    public void setEndBp(long endBp) {
        this.endBp = endBp;
    }

	public String getSpeciesChr() {
		return this.speciesChr;
	}

	public void setSpeciesChr(String speciesChr) {
		this.speciesChr = speciesChr;
	}
}