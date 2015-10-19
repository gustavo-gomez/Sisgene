package com.instituto.cuanto.sisgene.forms;

/**
 * Created by Jesus on 14/10/2015.
 */
public class LoginRequest {

    private String usuario;
    private String contrasenia;

    public LoginRequest(){
        //Constructor
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
