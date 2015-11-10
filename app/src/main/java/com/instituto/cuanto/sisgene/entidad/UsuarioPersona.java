
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class UsuarioPersona {
    private String usp_id;
    private String per_id;
    private String usu_id;
    private String gru_id;
    private String dis_id;
    private String ubi_id;
    private String cae_id;
    private String usp_estado;
    private String usp_desde_numEnc;
    private String usp_hasta_numEnc;
    private String usp_tot_encRealizadas;
    private String usp_tot_encAsignadas;
    
    public UsuarioPersona(){
        //Constructor de la clase UsuarioPersona
    }

    public String getUsp_id() {
        return usp_id;
    }

    public void setUsp_id(String usp_id) {
        this.usp_id = usp_id;
    }

    public String getPer_id() {
        return per_id;
    }

    public void setPer_id(String per_id) {
        this.per_id = per_id;
    }

    public String getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(String usu_id) {
        this.usu_id = usu_id;
    }

    public String getGru_id() {
        return gru_id;
    }

    public void setGru_id(String gru_id) {
        this.gru_id = gru_id;
    }

    public String getDis_id() {
        return dis_id;
    }

    public void setDis_id(String dis_id) {
        this.dis_id = dis_id;
    }

    public String getUbi_id() {
        return ubi_id;
    }

    public void setUbi_id(String ubi_id) {
        this.ubi_id = ubi_id;
    }

    public String getCae_id() {
        return cae_id;
    }

    public void setCae_id(String cae_id) {
        this.cae_id = cae_id;
    }

    public String getUsp_estado() {
        return usp_estado;
    }

    public void setUsp_estado(String usp_estado) {
        this.usp_estado = usp_estado;
    }

    public String getUsp_desde_numEnc() {
        return usp_desde_numEnc;
    }

    public void setUsp_desde_numEnc(String usp_desde_numEnc) {
        this.usp_desde_numEnc = usp_desde_numEnc;
    }

    public String getUsp_hasta_numEnc() {
        return usp_hasta_numEnc;
    }

    public void setUsp_hasta_numEnc(String usp_hasta_numEnc) {
        this.usp_hasta_numEnc = usp_hasta_numEnc;
    }

    public String getUsp_tot_encRealizadas() {
        return usp_tot_encRealizadas;
    }

    public void setUsp_tot_encRealizadas(String usp_tot_encRealizadas) {
        this.usp_tot_encRealizadas = usp_tot_encRealizadas;
    }

    public String getUsp_tot_encAsignadas() {
        return usp_tot_encAsignadas;
    }

    public void setUsp_tot_encAsignadas(String usp_tot_encAsignadas) {
        this.usp_tot_encAsignadas = usp_tot_encAsignadas;
    }
}
