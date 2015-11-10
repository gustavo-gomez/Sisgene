
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class Allegado {
    private String codigo_identificacion;
    private String nombres;
    private String apellido_paterno;
    private String apellido_materno;
    private String grado_familiaridad;
    
    public Allegado(){
        //Constructor de la clase Allegado
    }

    public String getCodigo_identificacion() {
        return codigo_identificacion;
    }

    public void setCodigo_identificacion(String codigo_identificacion) {
        this.codigo_identificacion = codigo_identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getGrado_familiaridad() {
        return grado_familiaridad;
    }

    public void setGrado_familiaridad(String grado_familiaridad) {
        this.grado_familiaridad = grado_familiaridad;
    }
    
    
}
