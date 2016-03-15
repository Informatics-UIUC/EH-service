package org.ncsa.evolutionhighway.entities;

import java.io.Serializable;


@SuppressWarnings("serial")
public class FeatureDensityPK implements Serializable {

    private String featureName;
    private String refChr;
    private Long refEnd;
    private String refGen;
    private Long refStart;

    public FeatureDensityPK() { }

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

    @Override
    public int hashCode() {
        return String.format("%s.%s.%s.%d.%d", featureName, refGen, refChr, refStart, refEnd).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof FeatureDensityPK)) return false;
        FeatureDensityPK pk = (FeatureDensityPK) obj;

        return
            pk.refGen.equals(this.refGen) &&
            pk.refChr.equals(this.refChr) &&
            pk.featureName.equals(this.featureName) &&
            pk.refStart == this.refStart &&
            pk.refEnd == this.refEnd;
    }

}
