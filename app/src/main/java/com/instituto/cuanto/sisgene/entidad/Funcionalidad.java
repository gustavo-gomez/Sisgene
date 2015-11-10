
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class Funcionalidad {
    private String fun_id;
    private String fun_nombre;
    private String fun_estado;
    
    public Funcionalidad(){
        //Constructor de la clase Funcionalidad
    }

    public String getFun_id() {
        return fun_id;
    }

    public void setFun_id(String fun_id) {
        this.fun_id = fun_id;
    }

    public String getFun_nombre() {
        return fun_nombre;
    }

    public void setFun_nombre(String fun_nombre) {
        this.fun_nombre = fun_nombre;
    }

    public String getFun_estado() {
        return fun_estado;
    }

    public void setFun_estado(String fun_estado) {
        this.fun_estado = fun_estado;
    }
    
}
