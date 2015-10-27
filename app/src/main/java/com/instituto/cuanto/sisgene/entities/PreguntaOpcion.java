package com.instituto.cuanto.sisgene.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Gustavo on 27/10/2015.
 */
public class PreguntaOpcion {

    Pregunta pre_idPadre;
    ArrayList<Opcion> arrayListOpcion;
    int preIdSiguiente;
    String pro_numeralOpcion;

    public PreguntaOpcion() {
    }

    public PreguntaOpcion(Pregunta pre_idPadre, ArrayList<Opcion> arrayListOpcion, int preIdSiguiente, String pro_numeralOpcion) {
        this.pre_idPadre = pre_idPadre;
        this.arrayListOpcion = arrayListOpcion;
        this.preIdSiguiente = preIdSiguiente;
        this.pro_numeralOpcion = pro_numeralOpcion;
    }

    public Pregunta getPre_idPadre() {
        return pre_idPadre;
    }

    public void setPre_idPadre(Pregunta pre_idPadre) {
        this.pre_idPadre = pre_idPadre;
    }

    public ArrayList<Opcion> getArrayListOpcion() {
        return arrayListOpcion;
    }

    public void setArrayListOpcion(ArrayList<Opcion> arrayListOpcion) {
        this.arrayListOpcion = arrayListOpcion;
    }

    public int getPreIdSiguiente() {
        return preIdSiguiente;
    }

    public void setPreIdSiguiente(int preIdSiguiente) {
        this.preIdSiguiente = preIdSiguiente;
    }

    public String getPro_numeralOpcion() {
        return pro_numeralOpcion;
    }

    public void setPro_numeralOpcion(String pro_numeralOpcion) {
        this.pro_numeralOpcion = pro_numeralOpcion;
    }
}