
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class Opcion {
    
    private String opc_id;
    private String opc_nombre;
    
    public Opcion(){
        //Constructor de la clase Opcion
    }

    public String getOpc_id() {
        return opc_id;
    }

    public void setOpc_id(String opc_id) {
        this.opc_id = opc_id;
    }

    public String getOpc_nombre() {
        return opc_nombre;
    }

    public void setOpc_nombre(String opc_nombre) {
        this.opc_nombre = opc_nombre;
    }
}
