
package com.instituto.cuanto.sisgene.entidad;

/**
 *
 * @author JMonzalve
 */
public class PersonaEncuestada {
    private String id_tipo_doc;
    private String num_documento;
    private String nombres;
    private String apellido_paterno;
    private String apellido_materno;
    private String telefono;
    private String celular;
    private String correo;
    private String fecha_nacimiento;
    private String edad;
    private String grado_instruccion;
    private String estado_civil;
    private String genero;
    
    public PersonaEncuestada(){
        //Constructor de la Clase PersonaEncuestada
    }

    public String getId_tipo_doc() {
        return id_tipo_doc;
    }

    public void setId_tipo_doc(String id_tipo_doc) {
        this.id_tipo_doc = id_tipo_doc;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getGrado_instruccion() {
        return grado_instruccion;
    }

    public void setGrado_instruccion(String grado_instruccion) {
        this.grado_instruccion = grado_instruccion;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    
}
