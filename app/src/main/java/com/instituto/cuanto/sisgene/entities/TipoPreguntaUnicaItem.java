package com.instituto.cuanto.sisgene.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Gustavo on 27/10/2015.
 */
public class TipoPreguntaUnicaItem {
    HashMap<Integer,String> alternativas;
    private String respuesta;
    private String title;
    private int pos;

    public HashMap<Integer, String> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(HashMap<Integer, String> alternativas) {
        this.alternativas = alternativas;
    }

    public String[] getAlternativasAsArray(){
        String[] result = new String[alternativas.size()];
        Iterator it = alternativas.keySet().iterator();
        int i = 0;
        while(it.hasNext()){
            Integer key = (int) it.next();
            result[i] = (String)alternativas.get(key);
            i++;
        }
        return result;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
