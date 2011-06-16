package org.ncsa.evolutionhighway.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: ChromosomeSize
 *
 */

@SuppressWarnings("serial")
@IdClass(ChromosomeSizePK.class)
@Entity
@Table(name="CHROMOSOME_SIZE")
@NamedNativeQuery(name = "ChromosomeSize.getSpeciesChrSize", query = "SELECT s.CHR, s.SIZE FROM CHROMOSOME_SIZE s WHERE s.GEN = SUBSTRING_INDEX(?, ':', 2)")
public class ChromosomeSize implements Serializable {

    @Id
    @Column(name="GEN")
    String gen;

    @Id
    @Column(name="CHR")
    String chr;

    @Column(name="SIZE", insertable=false, updatable=false)
    private Long length;

    public ChromosomeSize() { }

    public String getGen() {
        return this.gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getChr() {
        return this.chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    public Long getLength() {
        return this.length;
    }

    public void setLength(Long length) {
        this.length = length;
    }
}
