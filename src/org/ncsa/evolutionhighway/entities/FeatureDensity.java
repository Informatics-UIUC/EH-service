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
 * The persistent class for the FEATURE_DENSITY database table.
 *
 */
@IdClass(FeatureDensityPK.class)
@Entity
@Table(name="FEATURE_DENSITY")
@XmlRootElement
@XmlType(propOrder = { "featureName", "refGen", "refChr", "refStart", "refEnd", "score", "compGen" })
@NamedQueries({
        @NamedQuery(name = "FeatureDensity.getAvailableFeatures", query = "SELECT DISTINCT fd.featureName FROM FeatureDensity fd ORDER BY fd.featureName"),
        @NamedQuery(name = "FeatureDensity.getFeatureData", query = "SELECT fd.refStart, fd.refEnd, fd.score, fd.compGen FROM FeatureDensity fd WHERE fd.featureName = :featureName AND fd.refGen = :refGen AND fd.refChr = :refChr ORDER BY fd.refStart")
})
public class FeatureDensity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="COMP_GEN")
	private String compGen;

	@Id
	@Column(name="FEATURE_NAME")
	private String featureName;

	@Id
	@Column(name="REF_CHR")
	private String refChr;

	@Id
	@Column(name="REF_END")
	private Long refEnd;

	@Id
	@Column(name="REF_GEN")
	private String refGen;

	@Id
	@Column(name="REF_START")
	private Long refStart;

	@Column(name="SCORE")
	private float score;

    public FeatureDensity() {
    }

	public String getCompGen() {
		return this.compGen;
	}

	public void setCompGen(String compGen) {
		this.compGen = compGen;
	}

	public String getFeatureName() {
		return this.featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getRefChr() {
		return this.refChr;
	}

	public void setRefChr(String refChr) {
		this.refChr = refChr;
	}

	public Long getRefEnd() {
		return this.refEnd;
	}

	public void setRefEnd(Long refEnd) {
		this.refEnd = refEnd;
	}

	public String getRefGen() {
		return this.refGen;
	}

	public void setRefGen(String refGen) {
		this.refGen = refGen;
	}

	public Long getRefStart() {
		return this.refStart;
	}

	public void setRefStart(Long refStart) {
		this.refStart = refStart;
	}

	public float getScore() {
		return this.score;
	}

	public void setScore(float score) {
		this.score = score;
	}

}