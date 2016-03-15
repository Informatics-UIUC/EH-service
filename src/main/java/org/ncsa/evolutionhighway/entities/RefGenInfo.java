package org.ncsa.evolutionhighway.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: RefGenInfo
 *
 */
@SuppressWarnings("serial")
@IdClass(RefGenInfoPK.class)
@Entity
@Table(name="REF_GEN_INFO")
@NamedQueries({
        @NamedQuery(name = "RefGenInfo.getCentromereRegions", query = "SELECT i.startBp, i.endBp FROM RefGenInfo i WHERE i.refGen = :genomeId AND i.refChr = :chrId AND i.type = 0 ORDER BY i.startBp"),
        @NamedQuery(name = "RefGenInfo.getHeterochromatinRegions", query = "SELECT i.startBp, i.endBp FROM RefGenInfo i WHERE i.refGen = :genomeId AND i.refChr = :chrId AND i.type = 1 ORDER BY i.startBp"),
})
public class RefGenInfo implements Serializable {

    @Id
    @Column(name="REF_GEN")
    String refGen;

    @Id
    @Column(name="REF_CHR")
    String refChr;

    @Id
    @Column(name="START_BP")
    Long startBp;

    @Id
    @Column(name="END_BP")
    Long endBp;

    @Column(name="TYPE", insertable=false, updatable=false)
    private Integer type;

	public RefGenInfo() {
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

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
