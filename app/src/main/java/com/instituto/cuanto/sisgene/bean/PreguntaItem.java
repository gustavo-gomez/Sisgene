package com.instituto.cuanto.sisgene.bean;

import com.instituto.cuanto.sisgene.entities.Pregunta;

/**
 * Created by Jesus on 10/11/2015.
 */
public class PreguntaItem {

    private String ite_id;
    private String ite_nombre;
    private String pri_numeralitem;

    public PreguntaItem(){}

    public String getIte_id() {
        return ite_id;
    }

    public void setIte_id(String ite_id) {
        this.ite_id = ite_id;
    }

    public String getIte_nombre() {
        return ite_nombre;
    }

    public void setIte_nombre(String ite_nombre) {
        this.ite_nombre = ite_nombre;
    }

    public String getPri_numeralitem() {
        return pri_numeralitem;
    }

    public void setPri_numeralitem(String pri_numeralitem) {
        this.pri_numeralitem = pri_numeralitem;
    }
}
