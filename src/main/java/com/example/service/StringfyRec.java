package com.example.service;

import it.mynext.iaf.nettrs.Rec;

/**
 * Classe che mette a disposizione un singolo metodo per avere la rappresentazione a stringa di un Rec.
 */
public class StringfyRec {

    /**
     * Metodo utile per ottenere la rappresentazione a stringa di un Rec.
     *
     * @param rec Rec da convertire in stringa.
     * @return Rappresentazione a stringa di un Rec.
     */
    public static String stringOf(Rec rec) {
        String s = "";
        s += "NAME: " + rec.getName() + "\n";
        s += "FIELDS\n";
        for (int i = 0; i < rec.getFldCount(); i++) {
            s += " > name: " + rec.getFldName(i) + " | type: " + rec.getFldType(i) + " | size: " + rec.getFldSize(i) + "\n";
        }
        s += "RECS\n";
        for (int i = 0; i < rec.getCount(); i++) {
            for (int j = 0; j < rec.getRecData(i).FLDREC.length; j++)
                s += " > " + rec.getFldName(j % rec.getFldCount()) + ": " + rec.getRecData(i).FLDREC[j].VALUE + "\n";
        }
        return s;
    }
}
