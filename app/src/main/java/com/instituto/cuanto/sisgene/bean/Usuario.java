package com.instituto.cuanto.sisgene.bean;

/**
 * Created by Jesus on 14/10/2015.
 */
public class Usuario {

    private String nombre;
    private String ap_paterno;
    private String ap_materno;
    private String rol;

    public Usuario(){
        //Constructor
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp_paterno() {
        return ap_paterno;
    }

    public void setAp_paterno(String ap_paterno) {
        this.ap_paterno = ap_paterno;
    }

    public String getAp_materno() {
        return ap_materno;
    }

    public void setAp_materno(String ap_materno) {
        this.ap_materno = ap_materno;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
