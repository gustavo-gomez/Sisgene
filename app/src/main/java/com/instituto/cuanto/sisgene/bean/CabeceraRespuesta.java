package com.instituto.cuanto.sisgene.bean;

/**
 * Created by Jesus on 01/11/2015.
 */
public class CabeceraRespuesta {

    private String userEncuestador;
    private String numEncuesta;
    private String fechaDesarrollo;
    private String nombreEncuestado;
    private String apPaternoEncuestado;
    private String apMaternoEncuestado;
    private String horaInicio;
    private String horaFin;
    private String tiempo;
    private String estado;

    public CabeceraRespuesta(){}

    public String getUserEncuestador() {
        return userEncuestador;
    }

    public void setUserEncuestador(String userEncuestador) {
        this.userEncuestador = userEncuestador;
    }

    public String getNumEncuesta() {
        return numEncuesta;
    }

    public void setNumEncuesta(String numEncuesta) {
        this.numEncuesta = numEncuesta;
    }

    public String getFechaDesarrollo() {
        return fechaDesarrollo;
    }

    public void setFechaDesarrollo(String fechaDesarrollo) {
        this.fechaDesarrollo = fechaDesarrollo;
    }

    public String getNombreEncuestado() {
        return nombreEncuestado;
    }

    public void setNombreEncuestado(String nombreEncuestado) {
        this.nombreEncuestado = nombreEncuestado;
    }

    public String getApPaternoEncuestado() {
        return apPaternoEncuestado;
    }

    public void setApPaternoEncuestado(String apPaternoEncuestado) {
        this.apPaternoEncuestado = apPaternoEncuestado;
    }

    public String getApMaternoEncuestado() {
        return apMaternoEncuestado;
    }

    public void setApMaternoEncuestado(String apMaternoEncuestado) {
        this.apMaternoEncuestado = apMaternoEncuestado;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
