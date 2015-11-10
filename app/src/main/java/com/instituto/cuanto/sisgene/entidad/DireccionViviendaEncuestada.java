
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class DireccionViviendaEncuestada {
    private String tipo_ubicacion;
    private String num_puerta;
    private String interior;
    private String piso;
    private String etapa;
    private String sector;
    private String grupo;
    private String manzana;
    private String lote;
    private String km;
    
    public DireccionViviendaEncuestada(){
        //Constructor de la Clase DireccionViviendaEncuestada
    }

    public String getTipo_ubicacion() {
        return tipo_ubicacion;
    }

    public void setTipo_ubicacion(String tipo_ubicacion) {
        this.tipo_ubicacion = tipo_ubicacion;
    }

    public String getNum_puerta() {
        return num_puerta;
    }

    public void setNum_puerta(String num_puerta) {
        this.num_puerta = num_puerta;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }
    
    
}
