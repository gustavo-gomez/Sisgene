
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class Seccion {
    private String sec_id;
    private String sec_nombre;
    private String sec_nota;
    private String sec_numero_seccion;
    
    public Seccion(){
        //Constructor de la clase Seccion
    }

    public String getSec_id() {
        return sec_id;
    }

    public void setSec_id(String sec_id) {
        this.sec_id = sec_id;
    }

    public String getSec_nombre() {
        return sec_nombre;
    }

    public void setSec_nombre(String sec_nombre) {
        this.sec_nombre = sec_nombre;
    }

    public String getSec_nota() {
        return sec_nota;
    }

    public void setSec_nota(String sec_nota) {
        this.sec_nota = sec_nota;
    }

    public String getSec_numero_seccion() {
        return sec_numero_seccion;
    }

    public void setSec_numero_seccion(String sec_numero_seccion) {
        this.sec_numero_seccion = sec_numero_seccion;
    }
}
