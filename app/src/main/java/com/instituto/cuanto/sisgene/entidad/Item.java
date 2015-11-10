
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class Item {
    private String ite_id;
    private String ite_nombre;
    
    public Item(){
        //Constructor de la clase Item
    }

    public String getIte_id() {
        return ite_id;
    }

    public void setIte_id(String ite_id) {
        this.ite_id = ite_id;
    }

    public String getIte_nombre() {
        return ite_nombre;
    }

    public void setIte_nombre(String ite_nombre) {
        this.ite_nombre = ite_nombre;
    }
}
