
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class TipoDocumento {
    private String tip_id;
    private String tip_nombre;
    private String tip_descripcion;
    private String tip_longitud;
    private String tip_estado;
    private String tip_tipo_caracter;
    
    public TipoDocumento(){
        //Constructor de la clase TipoDocumento
    }

    public String getTip_id() {
        return tip_id;
    }

    public void setTip_id(String tip_id) {
        this.tip_id = tip_id;
    }

    public String getTip_nombre() {
        return tip_nombre;
    }

    public void setTip_nombre(String tip_nombre) {
        this.tip_nombre = tip_nombre;
    }

    public String getTip_descripcion() {
        return tip_descripcion;
    }

    public void setTip_descripcion(String tip_descripcion) {
        this.tip_descripcion = tip_descripcion;
    }

    public String getTip_longitud() {
        return tip_longitud;
    }

    public void setTip_longitud(String tip_longitud) {
        this.tip_longitud = tip_longitud;
    }

    public String getTip_estado() {
        return tip_estado;
    }

    public void setTip_estado(String tip_estado) {
        this.tip_estado = tip_estado;
    }

    public String getTip_tipo_caracter() {
        return tip_tipo_caracter;
    }

    public void setTip_tipo_caracter(String tip_tipo_caracter) {
        this.tip_tipo_caracter = tip_tipo_caracter;
    }
}
