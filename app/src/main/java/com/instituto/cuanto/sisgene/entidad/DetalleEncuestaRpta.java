
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class DetalleEncuestaRpta {
    private String preg_id;
    private String valor_respuesta;
    
    public DetalleEncuestaRpta(){
        //Constructor de la clase DetalleEncuestaRpta
    }

    public String getPreg_id() {
        return preg_id;
    }

    public void setPreg_id(String preg_id) {
        this.preg_id = preg_id;
    }

    public String getValor_respuesta() {
        return valor_respuesta;
    }

    public void setValor_respuesta(String valor_respuesta) {
        this.valor_respuesta = valor_respuesta;
    }
    
    
}
