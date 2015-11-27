
package com.instituto.cuanto.sisgene.forms;

/**
 *
 * @author JMonzalve
 */
public class GuardarEncuestaResponse {
    private String codigo_respuesta;
    private String mensaje;
    
    public GuardarEncuestaResponse(){
        //Constructor de la clase GuardarEncuestaResponse
    }

    public String getCodigo_respuesta() {
        return codigo_respuesta;
    }

    public void setCodigo_respuesta(String codigo_mensaje) {
        this.codigo_respuesta = codigo_mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}