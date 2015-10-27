package com.instituto.cuanto.sisgene.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Gustavo on 27/10/2015.
 */
public class PreguntaOpcion implements Parcelable {

    int pre_idPadre;
    ArrayList<Opcion> arrayListOpcion;
    int preIdSiguiente;
    String pro_numeralOpcion;

    public PreguntaOpcion(int pre_idPadre, ArrayList<Opcion> arrayListOpcion, int preIdSiguiente, String pro_numeralOpcion) {
        this.pre_idPadre = pre_idPadre;
        this.arrayListOpcion = arrayListOpcion;
        this.preIdSiguiente = preIdSiguiente;
        this.pro_numeralOpcion = pro_numeralOpcion;
    }

    public int getPre_idPadre() {
        return pre_idPadre;
    }

    public void setPre_idPadre(int pre_idPadre) {
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

    protected PreguntaOpcion(Parcel in) {
        pre_idPadre = in.readInt();
        if (in.readByte() == 0x01) {
            arrayListOpcion = new ArrayList<Opcion>();
            in.readList(arrayListOpcion, Opcion.class.getClassLoader());
        } else {
            arrayListOpcion = null;
        }
        preIdSiguiente = in.readInt();
        pro_numeralOpcion = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pre_idPadre);
        if (arrayListOpcion == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(arrayListOpcion);
        }
        dest.writeInt(preIdSiguiente);
        dest.writeString(pro_numeralOpcion);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PreguntaOpcion> CREATOR = new Parcelable.Creator<PreguntaOpcion>() {
        @Override
        public PreguntaOpcion createFromParcel(Parcel in) {
            return new PreguntaOpcion(in);
        }

        @Override
        public PreguntaOpcion[] newArray(int size) {
            return new PreguntaOpcion[size];
        }
    };
}