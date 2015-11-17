
package com.instituto.cuanto.sisgene.forms;

/**
 *
 * @author JMonzalve
 */
public class GuardarEncuestaResponse {
    private String codigo_mensaje;
    private String mensaje;
    
    public GuardarEncuestaResponse(){
        //Constructor de la clase GuardarEncuestaResponse
    }

    public String getCodigo_mensaje() {
        return codigo_mensaje;
    }

    public void setCodigo_mensaje(String codigo_mensaje) {
        this.codigo_mensaje = codigo_mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}