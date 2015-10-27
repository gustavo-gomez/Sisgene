package com.instituto.cuanto.sisgene.entities;

/**
 * Created by Gustavo on 27/10/2015.
 */
public class Pregunta {

    Character pre_mostrar_numero;
    String pre_numero;
    String pre_enunciado;
    String pre_explicativo;
    String pre_nota;
    Character pre_guia_rpta;
    String pre_tipo_rpta;
    int pre_rpta_Mixta_longitud_max;
    int pre_num_max_allegados;

    public Pregunta() {
    }

    public Pregunta(Character pre_mostrar_numero, String pre_numero, String pre_enunciado, String pre_explicativo, String pre_nota, Character pre_guia_rpta, String pre_tipo_rpta, int pre_rpta_Mixta_longitud_max, int pre_num_max_allegados) {
        this.pre_mostrar_numero = pre_mostrar_numero;
        this.pre_numero = pre_numero;
        this.pre_enunciado = pre_enunciado;
        this.pre_explicativo = pre_explicativo;
        this.pre_nota = pre_nota;
        this.pre_guia_rpta = pre_guia_rpta;
        this.pre_tipo_rpta = pre_tipo_rpta;
        this.pre_rpta_Mixta_longitud_max = pre_rpta_Mixta_longitud_max;
        this.pre_num_max_allegados = pre_num_max_allegados;
    }

    public Character getPre_mostrar_numero() {
        return pre_mostrar_numero;
    }

    public void setPre_mostrar_numero(Character pre_mostrar_numero) {
        this.pre_mostrar_numero = pre_mostrar_numero;
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

    public String getPre_nota() {
        return pre_nota;
    }

    public void setPre_nota(String pre_nota) {
        this.pre_nota = pre_nota;
    }

    public Character getPre_guia_rpta() {
        return pre_guia_rpta;
    }

    public void setPre_guia_rpta(Character pre_guia_rpta) {
        this.pre_guia_rpta = pre_guia_rpta;
    }

    public String getPre_tipo_rpta() {
        return pre_tipo_rpta;
    }

    public void setPre_tipo_rpta(String pre_tipo_rpta) {
        this.pre_tipo_rpta = pre_tipo_rpta;
    }

    public int getPre_rpta_Mixta_longitud_max() {
        return pre_rpta_Mixta_longitud_max;
    }

    public void setPre_rpta_Mixta_longitud_max(int pre_rpta_Mixta_longitud_max) {
        this.pre_rpta_Mixta_longitud_max = pre_rpta_Mixta_longitud_max;
    }

    public int getPre_num_max_allegados() {
        return pre_num_max_allegados;
    }

    public void setPre_num_max_allegados(int pre_num_max_allegados) {
        this.pre_num_max_allegados = pre_num_max_allegados;
    }
}
