package com.instituto.cuanto.sisgene.forms;

import java.util.List;
import com.instituto.cuanto.sisgene.entidad.Acceso;
import com.instituto.cuanto.sisgene.entidad.CaratulaEncuesta;
import com.instituto.cuanto.sisgene.entidad.Catalogo;
import com.instituto.cuanto.sisgene.entidad.DetalleEncuesta;
import com.instituto.cuanto.sisgene.entidad.Dispositivo;
import com.instituto.cuanto.sisgene.entidad.EstructuraEncuesta;
import com.instituto.cuanto.sisgene.entidad.Funcionalidad;
import com.instituto.cuanto.sisgene.entidad.Grupo;
import com.instituto.cuanto.sisgene.entidad.Item;
import com.instituto.cuanto.sisgene.entidad.Opcion;
import com.instituto.cuanto.sisgene.entidad.Persona;
import com.instituto.cuanto.sisgene.entidad.Pregunta;
import com.instituto.cuanto.sisgene.entidad.PreguntaItem;
import com.instituto.cuanto.sisgene.entidad.PreguntaOpcion;
import com.instituto.cuanto.sisgene.entidad.Rol;
import com.instituto.cuanto.sisgene.entidad.Seccion;
import com.instituto.cuanto.sisgene.entidad.SubSeccion;
import com.instituto.cuanto.sisgene.entidad.TipoDocumento;
import com.instituto.cuanto.sisgene.entidad.Ubigeo;
import com.instituto.cuanto.sisgene.entidad.Usuario;
import com.instituto.cuanto.sisgene.entidad.UsuarioPersona;

/**
 * Clase plantilla de json que responde el SCental para validar Supervisor
 *
 * @version 1.0 27 Octubre 2015
 * @author jmonzalve Copyright � HIPER S.A.
 */
public class ValidarAdministradorResponse {

    private String codigo_respuesta;
    private String mensaje;
    //Primera Query
    private List<Catalogo> lista_Catalogo;//x
    //Segunda Query
    private List<CaratulaEncuesta> lista_caratula_encuesta;//x
    private List<DetalleEncuesta> lista_det_encuesta;//x
    private List<EstructuraEncuesta> lista_estructura_encuesta;//x
    private List<Seccion> lista_seccion;//x
    private List<SubSeccion> lista_sub_seccion;//x
    private List<Pregunta> lista_pregunta;//x
    private List<PreguntaOpcion> lista_pregunta_opcion;//x
    private List<Opcion> lista_opcion;//x
    private List<PreguntaItem> lista_pregunta_item;//x
    private List<Item> lista_item;//x
    //Tercera Query
    private List<Usuario> lista_usuario;//x
    private List<Rol> lista_rol;//x
    private List<Persona> lista_persona;//x
    private List<Funcionalidad> lista_funcionalidad;//x
    private List<Acceso> lista_acceso;//x
    private List<TipoDocumento> lista_tipodocumento;//x
    private List<Ubigeo> lista_ubigeo;
    private List<Grupo> lista_grupo;//x
    private List<Dispositivo> lista_dispositivo;//x
    private List<UsuarioPersona> lista_usuariopersona;

    public ValidarAdministradorResponse() {
        //Constructor de la clase ValidarAdministradorResponse
    }

    public String getCodigo_respuesta() {
        return codigo_respuesta;
    }

    public void setCodigo_respuesta(String codigo_respuesta) {
        this.codigo_respuesta = codigo_respuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Catalogo> getLista_Catalogo() {
        return lista_Catalogo;
    }

    public void setLista_Catalogo(List<Catalogo> lista_Catalogo) {
        this.lista_Catalogo = lista_Catalogo;
    }

    public List<CaratulaEncuesta> getLista_caratula_encuesta() {
        return lista_caratula_encuesta;
    }

    public void setLista_caratula_encuesta(List<CaratulaEncuesta> lista_caratula_encuesta) {
        this.lista_caratula_encuesta = lista_caratula_encuesta;
    }

    public List<DetalleEncuesta> getLista_det_encuesta() {
        return lista_det_encuesta;
    }

    public void setLista_det_encuesta(List<DetalleEncuesta> lista_det_encuesta) {
        this.lista_det_encuesta = lista_det_encuesta;
    }

    public List<EstructuraEncuesta> getLista_estructura_encuesta() {
        return lista_estructura_encuesta;
    }

    public void setLista_estructura_encuesta(List<EstructuraEncuesta> lista_estructura_encuesta) {
        this.lista_estructura_encuesta = lista_estructura_encuesta;
    }

    public List<Seccion> getLista_seccion() {
        return lista_seccion;
    }

    public void setLista_seccion(List<Seccion> lista_seccion) {
        this.lista_seccion = lista_seccion;
    }

    public List<SubSeccion> getLista_sub_seccion() {
        return lista_sub_seccion;
    }

    public void setLista_sub_seccion(List<SubSeccion> lista_sub_seccion) {
        this.lista_sub_seccion = lista_sub_seccion;
    }

    public List<Pregunta> getLista_pregunta() {
        return lista_pregunta;
    }

    public void setLista_pregunta(List<Pregunta> lista_pregunta) {
        this.lista_pregunta = lista_pregunta;
    }

    public List<PreguntaOpcion> getLista_pregunta_opcion() {
        return lista_pregunta_opcion;
    }

    public void setLista_pregunta_opcion(List<PreguntaOpcion> lista_pregunta_opcion) {
        this.lista_pregunta_opcion = lista_pregunta_opcion;
    }

    public List<Opcion> getLista_opcion() {
        return lista_opcion;
    }

    public void setLista_opcion(List<Opcion> lista_opcion) {
        this.lista_opcion = lista_opcion;
    }

    public List<PreguntaItem> getLista_pregunta_item() {
        return lista_pregunta_item;
    }

    public void setLista_pregunta_item(List<PreguntaItem> lista_pregunta_item) {
        this.lista_pregunta_item = lista_pregunta_item;
    }

    public List<Item> getLista_item() {
        return lista_item;
    }

    public void setLista_item(List<Item> lista_item) {
        this.lista_item = lista_item;
    }

    public List<Usuario> getLista_usuario() {
        return lista_usuario;
    }

    public void setLista_usuario(List<Usuario> lista_usuario) {
        this.lista_usuario = lista_usuario;
    }

    public List<Rol> getLista_rol() {
        return lista_rol;
    }

    public void setLista_rol(List<Rol> lista_rol) {
        this.lista_rol = lista_rol;
    }

    public List<Persona> getLista_persona() {
        return lista_persona;
    }

    public void setLista_persona(List<Persona> lista_persona) {
        this.lista_persona = lista_persona;
    }

    public List<Funcionalidad> getLista_funcionalidad() {
        return lista_funcionalidad;
    }

    public void setLista_funcionalidad(List<Funcionalidad> lista_funcionalidad) {
        this.lista_funcionalidad = lista_funcionalidad;
    }

    public List<Acceso> getLista_acceso() {
        return lista_acceso;
    }

    public void setLista_acceso(List<Acceso> lista_acceso) {
        this.lista_acceso = lista_acceso;
    }

    public List<TipoDocumento> getLista_tipodocumento() {
        return lista_tipodocumento;
    }

    public void setLista_tipodocumento(List<TipoDocumento> lista_tipodocumento) {
        this.lista_tipodocumento = lista_tipodocumento;
    }

    public List<Ubigeo> getLista_ubigeo() {
        return lista_ubigeo;
    }

    public void setLista_ubigeo(List<Ubigeo> lista_ubigeo) {
        this.lista_ubigeo = lista_ubigeo;
    }

    public List<Grupo> getLista_grupo() {
        return lista_grupo;
    }

    public void setLista_grupo(List<Grupo> lista_grupo) {
        this.lista_grupo = lista_grupo;
    }

    public List<Dispositivo> getLista_dispositivo() {
        return lista_dispositivo;
    }

    public void setLista_dispositivo(List<Dispositivo> lista_dispositivo) {
        this.lista_dispositivo = lista_dispositivo;
    }

    public List<UsuarioPersona> getLista_usuariopersona() {
        return lista_usuariopersona;
    }

    public void setLista_usuariopersona(List<UsuarioPersona> lista_usuariopersona) {
        this.lista_usuariopersona = lista_usuariopersona;
    }


    
}