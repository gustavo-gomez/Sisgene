package com.instituto.cuanto.sisgene.entities;

import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by USUARIO on 08/11/2015.
 */
public class TipoPreguntaMatrizItem {
    HashMap<Integer,Integer> respuestas;
    ArrayList<String> vertical;
    ArrayList<String> horizontal;
    Boolean hasView;
    String title;
    public TableLayout tbLayout;

    public TipoPreguntaMatrizItem(){
        hasView = false;
    }

    public Boolean getHasView() {
        return hasView;
    }

    public void setHasView(Boolean hasView) {
        this.hasView = hasView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashMap<Integer, Integer> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(HashMap<Integer, Integer> respuestas) {
        this.respuestas = respuestas;
    }

    public ArrayList<String> getVertical() {
        return vertical;
    }

    public void setVertical(ArrayList<String> vertical) {
        this.vertical = vertical;
    }

    public ArrayList<String> getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(ArrayList<String> horizontal) {
        this.horizontal = horizontal;
    }

}
