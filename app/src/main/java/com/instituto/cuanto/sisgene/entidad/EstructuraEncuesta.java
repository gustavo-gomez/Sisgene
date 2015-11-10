
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class EstructuraEncuesta {
    private String ese_id;
    private String sec_id;
    private String sus_id_nivel1;
    private String sus_id_nivel2;
    private String pre_id;
    
    public EstructuraEncuesta(){
        //Constructor de la clase EstructuraEncuesta
    }

    public String getEse_id() {
        return ese_id;
    }

    public void setEse_id(String ese_id) {
        this.ese_id = ese_id;
    }

    public String getSec_id() {
        return sec_id;
    }

    public void setSec_id(String sec_id) {
        this.sec_id = sec_id;
    }

    public String getSus_id_nivel1() {
        return sus_id_nivel1;
    }

    public void setSus_id_nivel1(String sus_id_nivel1) {
        this.sus_id_nivel1 = sus_id_nivel1;
    }

    public String getSus_id_nivel2() {
        return sus_id_nivel2;
    }

    public void setSus_id_nivel2(String sus_id_nivel2) {
        this.sus_id_nivel2 = sus_id_nivel2;
    }

    public String getPre_id() {
        return pre_id;
    }

    public void setPre_id(String pre_id) {
        this.pre_id = pre_id;
    }
}
