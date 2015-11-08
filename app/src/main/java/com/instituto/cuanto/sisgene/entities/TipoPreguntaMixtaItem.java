package com.instituto.cuanto.sisgene.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Gustavo on 27/10/2015.
 */
public class TipoPreguntaMixtaItem {
    ArrayList<Integer> respuestas;
    HashMap<Integer,String> alternativas;
    String title;

    public HashMap<Integer, String> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(HashMap<Integer, String> alternativas) {
        this.alternativas = alternativas;
    }

    public ArrayList<Integer> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<Integer> respuestas) {
        this.respuestas = respuestas;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<MixtaAlternativa> getAlternativasAsArrayList() {
        ArrayList<MixtaAlternativa> ma = new ArrayList<>();
        Iterator it = alternativas.keySet().iterator();
        while(it.hasNext()){
            Integer key = (int) it.next();
            MixtaAlternativa temp = new MixtaAlternativa();
            temp.setTitle((String) alternativas.get(key));
            temp.setValue(false);
            ma.add(temp);
        }
        return ma;
    }
}
