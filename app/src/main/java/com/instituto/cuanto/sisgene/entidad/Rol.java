
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class Rol {
    private String rol_id;
    private String rol_codigo;
    private String rol_descripcion;
    
    public Rol(){
        //Constructor de la clase Rol
    }

    public String getRol_id() {
        return rol_id;
    }

    public void setRol_id(String rol_id) {
        this.rol_id = rol_id;
    }

    public String getRol_codigo() {
        return rol_codigo;
    }

    public void setRol_codigo(String rol_codigo) {
        this.rol_codigo = rol_codigo;
    }

    public String getRol_descripcion() {
        return rol_descripcion;
    }

    public void setRol_descripcion(String rol_descripcion) {
        this.rol_descripcion = rol_descripcion;
    }
}
