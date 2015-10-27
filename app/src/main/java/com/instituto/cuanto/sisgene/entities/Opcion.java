package com.instituto.cuanto.sisgene.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gustavo on 27/10/2015.
 */
public class Opcion implements Parcelable {

    String numero;
    String nombre;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    protected Opcion(Parcel in) {
        numero = in.readString();
        nombre = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(numero);
        dest.writeString(nombre);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Opcion> CREATOR = new Parcelable.Creator<Opcion>() {
        @Override
        public Opcion createFromParcel(Parcel in) {
            return new Opcion(in);
        }

        @Override
        public Opcion[] newArray(int size) {
            return new Opcion[size];
        }
    };
}