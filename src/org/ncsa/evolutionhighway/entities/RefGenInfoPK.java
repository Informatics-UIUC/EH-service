package org.ncsa.evolutionhighway.entities;

import java.io.Serializable;

public class RefGenInfoPK implements Serializable {
    private String refGen;
    private String refChr;
    private Long startBp;
    private Long endBp;

    public RefGenInfoPK() {}

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

    @Override
    public int hashCode() {
        return String.format("%s.%s.%d.%d", refGen, refChr, startBp, endBp).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof RefGenInfoPK)) return false;
        RefGenInfoPK pk = (RefGenInfoPK) obj;

        return
            pk.refGen.equals(this.refGen) &&
            pk.refChr.equals(this.refChr) &&
            pk.startBp == this.startBp &&
            pk.endBp == this.endBp;
    }
}
