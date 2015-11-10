
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class Pregunta {
    private String pre_id;
    private String pre_numero;
    private String pre_enunciado;
    private String pre_explicativo;
    private String pre_comentario;
    private String pre_guia_rpta;
    private String pre_tipo_rpta;
    private String pre_unica_persona;
    private String pre_cant_maxima_items;
    private String pre_maxNumRptas;
    private String pre_importaOrdenRptas;
    
    public Pregunta(){
        //Constructor de la clase Pregunta
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

    public String getPre_maxNumRptas() {
        return pre_maxNumRptas;
    }

    public void setPre_maxNumRptas(String pre_maxNumRptas) {
        this.pre_maxNumRptas = pre_maxNumRptas;
    }

    public String getPre_importaOrdenRptas() {
        return pre_importaOrdenRptas;
    }

    public void setPre_importaOrdenRptas(String pre_importaOrdenRptas) {
        this.pre_importaOrdenRptas = pre_importaOrdenRptas;
    }
}
