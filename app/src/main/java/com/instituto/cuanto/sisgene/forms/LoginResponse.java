package com.instituto.cuanto.sisgene.forms;

import com.instituto.cuanto.sisgene.bean.Encuestador;
import com.instituto.cuanto.sisgene.bean.Usuario;
import java.util.List;

/**
 * Created by Jesus on 14/10/2015.
 */
public class LoginResponse {

    private String response_code;
    private String message;
    private Usuario usuario;
    private List<Encuestador> encuestadores;

    public LoginResponse(){
        //Constructor
    }

    public String getResponse_code() {
        return response_code;
    }

    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Encuestador> getEncuestadores() {
        return encuestadores;
    }

    public void setEncuestadores(List<Encuestador> encuestadores) {
        this.encuestadores = encuestadores;
    }
}
