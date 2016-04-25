
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class PreguntaOpcion {
    private String pro_id;
    private String pre_id;
    private String opc_id;
    private String pro_numeralOpcion;
    private String pro_numeroPreguntaSiguiente;
    private String pro_idEncuesta;
    private String pro_valor;
    
    public PreguntaOpcion(){
        //Constructor de la clase PreguntaOpcion
    }

    public String getPro_id() {
        return pro_id;
    }

    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
    }

    public String getPre_id() {
        return pre_id;
    }

    public void setPre_id(String pre_id) {
        this.pre_id = pre_id;
    }

    public String getOpc_id() {
        return opc_id;
    }

    public void setOpc_id(String opc_id) {
        this.opc_id = opc_id;
    }

    public String getPro_numeralOpcion() {
        return pro_numeralOpcion;
    }

    public void setPro_numeralOpcion(String pro_numeralOpcion) {
        this.pro_numeralOpcion = pro_numeralOpcion;
    }

    public String getPro_numeroPreguntaSiguiente() {
        return pro_numeroPreguntaSiguiente;
    }

    public void setPro_numeroPreguntaSiguiente(String pro_numeroPreguntaSiguiente) {
        this.pro_numeroPreguntaSiguiente = pro_numeroPreguntaSiguiente;
    }

    public String getPro_idEncuesta() {
        return pro_idEncuesta;
    }

    public void setPro_idEncuesta(String pro_idEncuesta) {
        this.pro_idEncuesta = pro_idEncuesta;
    }

    public String getPro_valor() {
        return pro_valor;
    }

    public void setPro_valor(String pro_valor) {
        this.pro_valor = pro_valor;
    }
}
