package com.instituto.cuanto.sisgene.bean;

/**
 * Created by Jesus on 10/11/2015.
 */
public class PreguntaAlternativa {

    private String opc_id;
    private String opc_nombre;
    private String pro_numeralopcion;
    private String pro_numeropreguntasiguiente;

    public PreguntaAlternativa(){}

    public String getOpc_id() {
        return opc_id;
    }

    public void setOpc_id(String opc_id) {
        this.opc_id = opc_id;
    }

    public String getOpc_nombre() {
        return opc_nombre;
    }

    public void setOpc_nombre(String opc_nombre) {
        this.opc_nombre = opc_nombre;
    }

    public String getPro_numeralopcion() {
        return pro_numeralopcion;
    }

    public void setPro_numeralopcion(String pro_numeralopcion) {
        this.pro_numeralopcion = pro_numeralopcion;
    }

    public String getPro_numeropreguntasiguiente() {
        return pro_numeropreguntasiguiente;
    }

    public void setPro_numeropreguntasiguiente(String pro_numeropreguntasiguiente) {
        this.pro_numeropreguntasiguiente = pro_numeropreguntasiguiente;
    }
}
