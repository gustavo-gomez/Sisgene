package com.instituto.cuanto.sisgene.entities;

import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by USUARIO on 08/11/2015.
 */
public class TipoPreguntaMatrizSimpleItem {
    ArrayList<String> respuestas;
    ArrayList<String> vertical;
    ArrayList<String> horizontal;
    Boolean hasView;
    String title;
    public TableLayout tbLayout;

    public TipoPreguntaMatrizSimpleItem(){
        hasView = false;
        respuestas = new ArrayList<>();
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

    public ArrayList<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<String> respuestas) {
        this.respuestas = respuestas;
    }

    public ArrayList<String> getVertical() {
        return vertical;
    }

    public void setVertical(ArrayList<String> vertical) {
        this.vertical = vertical;
        for(int i=0;i<vertical.size();i++){
            respuestas.add(i,"");
        }
    }

    public ArrayList<String> getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(ArrayList<String> horizontal) {
        this.horizontal = horizontal;
    }

}
