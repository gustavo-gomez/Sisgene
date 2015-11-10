
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class SubSeccion {
    private String sus_id;
    private String sus_nombre;
    private String sus_nota;
    private String sus_numero_subseccion;
    
    public SubSeccion(){
        //Constructor de la clase SubSeccion
    }

    public String getSus_id() {
        return sus_id;
    }

    public void setSus_id(String sus_id) {
        this.sus_id = sus_id;
    }

    public String getSus_nombre() {
        return sus_nombre;
    }

    public void setSus_nombre(String sus_nombre) {
        this.sus_nombre = sus_nombre;
    }

    public String getSus_nota() {
        return sus_nota;
    }

    public void setSus_nota(String sus_nota) {
        this.sus_nota = sus_nota;
    }

    public String getSus_numero_subseccion() {
        return sus_numero_subseccion;
    }

    public void setSus_numero_subseccion(String sus_numero_subseccion) {
        this.sus_numero_subseccion = sus_numero_subseccion;
    }
}
