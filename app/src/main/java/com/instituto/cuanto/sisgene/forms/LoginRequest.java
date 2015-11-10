package com.instituto.cuanto.sisgene.forms;

/**
 * Created by Jesus on 14/10/2015.
 */
public class LoginRequest {

    private String usuario;
    private String clave;

    public LoginRequest(){
        //Constructor
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
