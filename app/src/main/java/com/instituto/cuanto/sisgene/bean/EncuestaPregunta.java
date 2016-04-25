package com.instituto.cuanto.sisgene.bean;

import com.instituto.cuanto.sisgene.dao.EncuestaDAO;

/**
 * Created by Jesus on 10/11/2015.
 */
public class EncuestaPregunta {

    private String sec_id;
    private String sec_nombre;
    private String sec_nota;
    private String sec_numero_seccion;
    private String sus_id;
    private String sus_nombre;
    private String sus_nota;
    private String sus_numero_subseccion;
    private String pre_id;
    private String pre_numero;
    private String pre_enunciado;
    private String pre_explicativo;
    private String pre_comentario;
    private String pre_guia_rpta;
    private String pre_tipo_rpta;
    private String pre_unica_persona;
    private String pre_cant_maxima_items;
    private String pre_nummaxrptamu;
    private String pre_importarordenrptamu;
    private String pre_subtipo;
    private String pre_tiponumerico;
    private String pre_desde;
    private String pre_hasta;

    public EncuestaPregunta(){

    }

    public String getSec_id() {
        return sec_id;
    }

    public void setSec_id(String sec_id) {
        this.sec_id = sec_id;
    }

    public String getSec_nombre() {
        return sec_nombre;
    }

    public void setSec_nombre(String sec_nombre) {
        this.sec_nombre = sec_nombre;
    }

    public String getSec_nota() {
        return sec_nota;
    }

    public void setSec_nota(String sec_nota) {
        this.sec_nota = sec_nota;
    }

    public String getSec_numero_seccion() {
        return sec_numero_seccion;
    }

    public void setSec_numero_seccion(String sec_numero_seccion) {
        this.sec_numero_seccion = sec_numero_seccion;
    }

    public String getSus_id() {
        return sus_id;
    }

    public void setSus_id(String sus_id) {
        this.sus_id = sus_id;
    }

    public String getSus_nombre() {
        return sus_nombre;
    }

    public void setSus_nombre(String sus_nombre) {
        this.sus_nombre = sus_nombre;
    }

    public String getSus_nota() {
        return sus_nota;
    }

    public void setSus_nota(String sus_nota) {
        this.sus_nota = sus_nota;
    }

    public String getSus_numero_subseccion() {
        return sus_numero_subseccion;
    }

    public void setSus_numero_subseccion(String sus_numero_subseccion) {
        this.sus_numero_subseccion = sus_numero_subseccion;
    }

    public String getPre_id() {
        return pre_id;
    }

    public void setPre_id(String pre_id) {
        this.pre_id = pre_id;
    }

    public String getPre_numero() {
        return pre_numero;
    }

    public void setPre_numero(String pre_numero) {
        this.pre_numero = pre_numero;
    }

    public String getPre_enunciado() {
        return pre_enunciado;
    }

    public void setPre_enunciado(String pre_enunciado) {
        this.pre_enunciado = pre_enunciado;
    }

    public String getPre_explicativo() {
        return pre_explicativo;
    }

    public void setPre_explicativo(String pre_explicativo) {
        this.pre_explicativo = pre_explicativo;
    }

    public String getPre_comentario() {
        return pre_comentario;
    }

    public void setPre_comentario(String pre_comentario) {
        this.pre_comentario = pre_comentario;
    }

    public String getPre_guia_rpta() {
        return pre_guia_rpta;
    }

    public void setPre_guia_rpta(String pre_guia_rpta) {
        this.pre_guia_rpta = pre_guia_rpta;
    }

    public String getPre_tipo_rpta() {
        return pre_tipo_rpta;
    }

    public void setPre_tipo_rpta(String pre_tipo_rpta) {
        this.pre_tipo_rpta = pre_tipo_rpta;
    }

    public String getPre_unica_persona() {
        return pre_unica_persona;
    }

    public void setPre_unica_persona(String pre_unica_persona) {
        this.pre_unica_persona = pre_unica_persona;
    }

    public String getPre_cant_maxima_items() {
        return pre_cant_maxima_items;
    }

    public void setPre_cant_maxima_items(String pre_cant_maxima_items) {
        this.pre_cant_maxima_items = pre_cant_maxima_items;
    }

    public String getPre_nummaxrptamu() {
        return pre_nummaxrptamu;
    }

    public void setPre_nummaxrptamu(String pre_nummaxrptamu) {
        this.pre_nummaxrptamu = pre_nummaxrptamu;
    }

    public String getPre_importarordenrptamu() {
        return pre_importarordenrptamu;
    }

    public void setPre_importarordenrptamu(String pre_importarordenrptamu) {
        this.pre_importarordenrptamu = pre_importarordenrptamu;
    }

    public String getPre_subtipo() {
        return pre_subtipo;
    }

    public void setPre_subtipo(String pre_subtipo) {
        this.pre_subtipo = pre_subtipo;
    }

    public String getPre_tiponumerico() {
        return pre_tiponumerico;
    }

    public void setPre_tiponumerico(String pre_tiponumerico) {
        this.pre_tiponumerico = pre_tiponumerico;
    }

    public String getPre_desde() {
        return pre_desde;
    }

    public void setPre_desde(String pre_desde) {
        this.pre_desde = pre_desde;
    }

    public String getPre_hasta() {
        return pre_hasta;
    }

    public void setPre_hasta(String pre_hasta) {
        this.pre_hasta = pre_hasta;
    }
}
