
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class Grupo {
    private String gru_id;
    private String usu_id_supervisor;
    private String gru_numero;
    private String gru_tot_encuestadores;
    
    public Grupo(){
        //Constructor de la clase Grupo
    }

    public String getGru_id() {
        return gru_id;
    }

    public void setGru_id(String gru_id) {
        this.gru_id = gru_id;
    }

    public String getUsu_id_supervisor() {
        return usu_id_supervisor;
    }

    public void setUsu_id_supervisor(String usu_id_supervisor) {
        this.usu_id_supervisor = usu_id_supervisor;
    }

    public String getGru_numero() {
        return gru_numero;
    }

    public void setGru_numero(String gru_numero) {
        this.gru_numero = gru_numero;
    }

    public String getGru_tot_encuestadores() {
        return gru_tot_encuestadores;
    }

    public void setGru_tot_encuestadores(String gru_tot_encuestadores) {
        this.gru_tot_encuestadores = gru_tot_encuestadores;
    }
    
}
