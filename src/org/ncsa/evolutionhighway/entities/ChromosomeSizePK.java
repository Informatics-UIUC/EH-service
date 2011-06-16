package org.ncsa.evolutionhighway.entities;

import java.io.Serializable;


@SuppressWarnings("serial")
public class ChromosomeSizePK implements Serializable {

    private String gen;
    private String chr;

    public ChromosomeSizePK() { }

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

    @Override
    public int hashCode() {
        return String.format("%s.%s", gen, chr).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof ChromosomeSizePK)) return false;
        ChromosomeSizePK pk = (ChromosomeSizePK) obj;

        return
            pk.gen.equals(this.gen) &&
            pk.chr.equals(this.chr);
    }

}
