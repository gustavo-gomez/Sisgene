
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class PreguntaItem {
    private String pri_id;
    private String pre_id;
    private String ite_id;
    private String pri_numeralItem;
    private String pri_valor;
    
    public PreguntaItem(){
        //Constructor de la clase PreguntaItem
    }

    public String getPri_id() {
        return pri_id;
    }

    public void setPri_id(String pri_id) {
        this.pri_id = pri_id;
    }

    public String getPre_id() {
        return pre_id;
    }

    public void setPre_id(String pre_id) {
        this.pre_id = pre_id;
    }

    public String getIte_id() {
        return ite_id;
    }

    public void setIte_id(String ite_id) {
        this.ite_id = ite_id;
    }

    public String getPri_numeralItem() {
        return pri_numeralItem;
    }

    public void setPri_numeralItem(String pri_numeralItem) {
        this.pri_numeralItem = pri_numeralItem;
    }

    public String getPri_valor() {
        return pri_valor;
    }

    public void setPri_valor(String pri_valor) {
        this.pri_valor = pri_valor;
    }
}