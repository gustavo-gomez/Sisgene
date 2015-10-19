package com.instituto.cuanto.sisgene.bean;

/**
 * Created by Jesus on 14/10/2015.
 */
public class Usuario {

    private String nombre;
    private String apellido;

    public Usuario(){
        //Constructor
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
