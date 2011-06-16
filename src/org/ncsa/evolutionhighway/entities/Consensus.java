package org.ncsa.evolutionhighway.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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
@XmlType(propOrder = { "refGen", "refChr", "species", "speciesChr", "label", "startBp", "endBp", "sign", "modStart", "modEnd" })
@NamedQueries({
        @NamedQuery(name = "Consensus.getGenomes", query = "SELECT DISTINCT c.refGen FROM Consensus c ORDER BY c.refGen"),
        @NamedQuery(name = "Consensus.getSpecies", query = "SELECT DISTINCT c.species FROM Consensus c WHERE c.refGen = :genomeId AND c.refChr = :chrId ORDER BY c.species"),
        @NamedQuery(name = "Consensus.getSynBlocks", query = "SELECT c.startBp, c.endBp, c.label, c.sign, c.modStart, c.modEnd, c.speciesChr FROM Consensus c WHERE c.refGen = :genomeId AND c.refChr = :chrId AND c.species = :speciesId ORDER BY c.startBp"),
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Consensus.getChromosomes", query = "SELECT CHR, SIZE FROM (SELECT DISTINCT c.REF_GEN AS GEN, c.REF_CHR AS CHR FROM CONSENSUS c WHERE c.REF_GEN = ?) dc JOIN CHROMOSOME_SIZE s USING (GEN, CHR)"),
})
public class Consensus implements Serializable {

    @Id
    @Column(name="REF_GEN")
    String refGen;

    @Id
    @Column(name="REF_CHR")
    String refChr;

    @Id
    @Column(name="COMP_GEN")
    String species;

    @Column(name="COMP_CHR", insertable=false, updatable=false)
    private String speciesChr;

    @Column(name="LABEL", insertable=false, updatable=false)
    private String label;

    @Id
    @Column(name="START_BP")
    Long startBp;

    @Id
    @Column(name="END_BP")
    Long endBp;

    @Column(name="SIGN", insertable=false, updatable=false)
    private Integer sign;

    @Column(name="MODIFIED_ORDER_START", insertable=false, updatable=false)
    private Long modStart;

    @Column(name="MODIFIED_ORDER_END", insertable=false, updatable=false)
    private Long modEnd;

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

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Long getStartBp() {
        return this.startBp;
    }

    public void setStartBp(Long startBp) {
        this.startBp = startBp;
    }

    public Long getEndBp() {
        return this.endBp;
    }

    public void setEndBp(Long endBp) {
        this.endBp = endBp;
    }

	public String getSpeciesChr() {
		return this.speciesChr;
	}

	public void setSpeciesChr(String speciesChr) {
		this.speciesChr = speciesChr;
	}

	public Integer getSign() {
	    return this.sign;
	}

	public void setSign(Integer sign) {
	    this.sign = sign;
	}

	public Long getModStart() {
	    return this.modStart;
	}

	public void setModStart(Long modStart) {
	    this.modStart = modStart;
	}

	public Long getModEnd() {
	    return this.modEnd;
	}

	public void setModEnd(Long modEnd) {
	    this.modEnd = modEnd;
	}
}