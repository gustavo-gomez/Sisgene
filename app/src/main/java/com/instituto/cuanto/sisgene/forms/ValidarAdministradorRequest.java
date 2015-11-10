package com.instituto.cuanto.sisgene.forms;

/**
 * Sistema Central WS - Tablet SISGENE
 *
 * @version 1.0 27 Octubre 2015
 * @author jmonzalve
 */
public class ValidarAdministradorRequest {

    private String usuario;
    private String clave;
    private String codigo_encuesta;

    public ValidarAdministradorRequest() {
        //Constructor
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCodigo_encuesta() {
        return codigo_encuesta;
    }

    public void setCodigo_encuesta(String codigo_encuesta) {
        this.codigo_encuesta = codigo_encuesta;
    }

    

}
