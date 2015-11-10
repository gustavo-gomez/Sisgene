
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class Dispositivo {
    private String dis_id;
    private String dis_nombre;
    private String dis_descripcion;
    private String dis_marca;
    private String dis_modelo;
    private String dis_serie;
    
    public Dispositivo(){
        //Constructor de la clase Dispositivo
    }

    public String getDis_id() {
        return dis_id;
    }

    public void setDis_id(String dis_id) {
        this.dis_id = dis_id;
    }

    public String getDis_nombre() {
        return dis_nombre;
    }

    public void setDis_nombre(String dis_nombre) {
        this.dis_nombre = dis_nombre;
    }

    public String getDis_descripcion() {
        return dis_descripcion;
    }

    public void setDis_descripcion(String dis_descripcion) {
        this.dis_descripcion = dis_descripcion;
    }

    public String getDis_marca() {
        return dis_marca;
    }

    public void setDis_marca(String dis_marca) {
        this.dis_marca = dis_marca;
    }

    public String getDis_modelo() {
        return dis_modelo;
    }

    public void setDis_modelo(String dis_modelo) {
        this.dis_modelo = dis_modelo;
    }

    public String getDis_serie() {
        return dis_serie;
    }

    public void setDis_serie(String dis_serie) {
        this.dis_serie = dis_serie;
    }
}
