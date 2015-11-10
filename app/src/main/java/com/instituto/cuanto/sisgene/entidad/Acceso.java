
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class Acceso {
    private String acc_id;
    private String fun_id;
    private String rol_id;
    private String acc_estado;
    
    public Acceso(){
        //Constructor de la clase Acceso
    }

    public String getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(String acc_id) {
        this.acc_id = acc_id;
    }

    public String getFun_id() {
        return fun_id;
    }

    public void setFun_id(String fun_id) {
        this.fun_id = fun_id;
    }

    public String getRol_id() {
        return rol_id;
    }

    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    public String getAcc_estado() {
        return acc_estado;
    }

    public void setAcc_estado(String acc_estado) {
        this.acc_estado = acc_estado;
    }
}
