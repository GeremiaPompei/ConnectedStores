package com.example.model;

import it.mynext.iaf.nettrs.Rec;

/**
 * Classe utile per creare oggetti Rec capaci di essere serializzati e deserializzati in json tramite metodi get e set.
 */
public class RecEntity extends Rec {
    public int getCurrec() {
        return currec;
    }

    public int getFldcount() {
        return fldcount;
    }

    public int getReccount() {
        return reccount;
    }

    public FLD[] getFld() {
        return fld;
    }

    public REC[] getRec() {
        return rec;
    }

    public void setCurrec(int currec) {
        this.currec = currec;
    }

    public void setFldcount(int fldcount) {
        this.fldcount = fldcount;
    }

    public void setReccount(int reccount) {
        this.reccount = reccount;
    }

    public void setFld(FLD[] fld) {
        this.fld = fld;
    }

    public void setRec(REC[] rec) {
        this.rec = rec;
    }
}
