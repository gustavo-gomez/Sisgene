package com.instituto.cuanto.sisgene.dao;

import android.content.Context;
import android.database.Cursor;

import com.instituto.cuanto.sisgene.entidad.Allegado;
import com.instituto.cuanto.sisgene.entidad.CabeceraEncuestaRpta;
import com.instituto.cuanto.sisgene.entidad.DetalleEncuestaRpta;
import com.instituto.cuanto.sisgene.entidad.DireccionViviendaEncuestada;
import com.instituto.cuanto.sisgene.entidad.Persona;
import com.instituto.cuanto.sisgene.entidad.PersonaEncuestada;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesus on 17/11/2015.
 */
public class EnviarEncuestaDAO {


    public PersonaEncuestada obtenerPersonaEncuestada(Context context,String idCabEnc) {

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        PersonaEncuestada persona = null;

        String sql = " select per.tip_id ,per.per_num_documento,per.per_nombres,per.per_appaterno, " +
                " per.per_apmaterno,per.per_telefono,per.per_celular,per.per_correo,per.per_fnacimiento, " +
                " per.per_edad,per.per_gradinstruccion,per.per_estadocivil,per.per_genero " +
                " from persona per " +
                " inner join cab_enc_rpta caer on per.per_id = caer.per_id " +
                " where caer.caer_id = "+idCabEnc;

        try {

            cursor = dataBaseHelper.db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                persona = new PersonaEncuestada();

                persona.setId_tipo_doc((cursor.getString(0) != null) ? cursor.getString(0) : "");
                persona.setNum_documento((cursor.getString(1) != null) ? cursor.getString(1) : "");
                persona.setNombres((cursor.getString(2) != null) ? cursor.getString(2) : "");
                persona.setApellido_paterno((cursor.getString(3) != null) ? cursor.getString(3) : "");
                persona.setApellido_materno((cursor.getString(4) != null) ? cursor.getString(4) : "");
                persona.setTelefono((cursor.getString(5) != null) ? cursor.getString(5) : "");
                persona.setCelular((cursor.getString(6) != null) ? cursor.getString(6) : "");
                persona.setCorreo((cursor.getString(7) != null) ? cursor.getString(7) : "");
                persona.setFecha_nacimiento((cursor.getString(8) != null) ? cursor.getString(8) : "");
                persona.setEdad((cursor.getString(9) != null) ? cursor.getString(9) : "");
                persona.setGrado_instruccion((cursor.getString(10) != null) ? cursor.getString(10) : "");
                persona.setEstado_civil((cursor.getString(11) != null) ? cursor.getString(11) : "");
                persona.setGenero((cursor.getString(12) != null) ? cursor.getString(12) : "");

            }
            System.out.println("OKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
            return persona;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        System.out.println("nullllllllllllllllllllllllllllllllllll");
        return null;

    }

    public DireccionViviendaEncuestada obtenerDireccionEncuestada(Context context,String idCabEnc) {

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        DireccionViviendaEncuestada direccion = null;

        String sql = " select dir.dir_tipo_ubicacion,dir.dir_num_puerta,dir.dir_int,dir.dir_piso,dir.dir_etapa, " +
                " dir.dir_sector,dir.dir_grupo,dir.dir_manzana,dir.dir_lote,dir.dir_km " +
                " from direccion dir " +
                " inner join cab_enc_rpta caer on caer.dir_id = dir.dir_id " +
                " where caer.caer_id = "+idCabEnc;

        try {

            cursor = dataBaseHelper.db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                direccion = new DireccionViviendaEncuestada();

                direccion.setTipo_ubicacion((cursor.getString(0) != null) ? cursor.getString(0) : "");
                direccion.setNum_puerta((cursor.getString(1) != null) ? cursor.getString(1) : "");
                direccion.setInterior((cursor.getString(2) != null) ? cursor.getString(2) : "");
                direccion.setPiso((cursor.getString(3) != null) ? cursor.getString(3) : "");
                direccion.setEtapa((cursor.getString(4) != null) ? cursor.getString(4) : "");
                direccion.setSector((cursor.getString(5) != null) ? cursor.getString(5) : "");
                direccion.setGrupo((cursor.getString(6) != null) ? cursor.getString(6) : "");
                direccion.setManzana((cursor.getString(7) != null) ? cursor.getString(7) : "");
                direccion.setLote((cursor.getString(8) != null) ? cursor.getString(8) : "");
                direccion.setKm((cursor.getString(9) != null) ? cursor.getString(9) : "");
            }
            System.out.println("OKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
            return direccion;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        System.out.println("nullllllllllllllllllllllllllllllllllll");
        return null;
    }

    public CabeceraEncuestaRpta obtenerCabEncRtpaEncuestada(Context context,String idCabEnc) {

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        CabeceraEncuestaRpta cabeceraRpta = null;

        String sql = " select usp.usu_id,caer.caer_numero_encuesta,caer.caer_estado,caer.caer_fencuesta,caer.caer_observaciones, " +
                " caer.caer_nconglomerado,caer.caer_nzona_aer,caer.caer_nmanzana,caer.caer_nvivienda,caer.caer_nhogar, " +
                " caer.caer_narea,caer.caer_ncondicion,caer.caer_codigo_informante,caer.caer_fvisita1,caer.caer_codigo_digitador, " +
                " caer.caer_maquina_digitador,caer.caer_fdigitacion,caer.caer_hora_inicio,caer.caer_hora_fin,caer.caer_tiempo, " +
                " caer.caer_observacion_supervisor,caer.caer_codigo_centropoblado,caer.caer_nombre_centropoblado, " +
                " caer.caer_categoria_centropoblado,caer.caer_fencuestaenviada " +
                " from  cab_enc_rpta caer " +
                " inner join usuario_persona usp on usp.usp_id = caer.usp_id " +
                " where caer.caer_id = "+idCabEnc;

        try {

            cursor = dataBaseHelper.db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                cabeceraRpta = new CabeceraEncuestaRpta();

                cabeceraRpta.setId_usuario_encuestador((cursor.getString(0) != null) ? cursor.getString(0) : "");
                cabeceraRpta.setNumero_encuesta((cursor.getString(1) != null) ? cursor.getString(1) : "");
                cabeceraRpta.setEstado((cursor.getString(2) != null) ? cursor.getString(2) : "");
                cabeceraRpta.setFecha((cursor.getString(3) != null) ? cursor.getString(3) : "");
                cabeceraRpta.setObservaciones((cursor.getString(4) != null) ? cursor.getString(4) : "");
                cabeceraRpta.setNum_conglomerado((cursor.getString(5) != null) ? cursor.getString(5) : "");
                cabeceraRpta.setNum_zona((cursor.getString(6) != null) ? cursor.getString(6) : "");
                cabeceraRpta.setNum_manzana((cursor.getString(7) != null) ? cursor.getString(7) : "");
                cabeceraRpta.setNum_vivienda((cursor.getString(8) != null) ? cursor.getString(8) : "");
                cabeceraRpta.setNum_hogar((cursor.getString(9) != null) ? cursor.getString(9) : "");
                cabeceraRpta.setNum_area((cursor.getString(10) != null) ? cursor.getString(10) : "");
                cabeceraRpta.setCondicion((cursor.getString(11) != null) ? cursor.getString(11) : "");
                cabeceraRpta.setCodigo_informante((cursor.getString(12) != null) ? cursor.getString(12) : "");
                cabeceraRpta.setFecha_visita((cursor.getString(13) != null) ? cursor.getString(13) : "");
                cabeceraRpta.setCodigo_digitador((cursor.getString(14) != null) ? cursor.getString(14) : "");
                cabeceraRpta.setMaquina_digitador((cursor.getString(15) != null) ? cursor.getString(15) : "");
                cabeceraRpta.setFecha_digitacion((cursor.getString(16) != null) ? cursor.getString(16) : "");
                cabeceraRpta.setHora_inicio((cursor.getString(17) != null) ? cursor.getString(17) : "");
                cabeceraRpta.setHora_fin((cursor.getString(18) != null) ? cursor.getString(18) : "");
                cabeceraRpta.setTiempo((cursor.getString(19) != null) ? cursor.getString(19) : "");
                cabeceraRpta.setObervacion_supervisor((cursor.getString(20) != null) ? cursor.getString(20) : "");
                cabeceraRpta.setCodigo_centropoblado((cursor.getString(21) != null) ? cursor.getString(21) : "");
                cabeceraRpta.setNombre_centropoblado((cursor.getString(22) != null) ? cursor.getString(22) : "");
                cabeceraRpta.setCategoría_centropoblado((cursor.getString(23) != null) ? cursor.getString(23) : "");
                cabeceraRpta.setFecha_envio((cursor.getString(24) != null) ? cursor.getString(24) : "");

            }
            System.out.println("OKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
            return cabeceraRpta;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        System.out.println("nullllllllllllllllllllllllllllllllllll");
        return null;
    }

    public List<DetalleEncuestaRpta> obtenerDenEncEncuestada(Context context,String idCabEnc) {

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<DetalleEncuestaRpta> listDetEnc = new ArrayList<DetalleEncuestaRpta>();
        DetalleEncuestaRpta detEnc = null;

        String sql = " select deer.pre_id,deer.deer_valor_respuesta " +
                " from cab_enc_rpta caer " +
                " inner join det_enc_rpta deer on caer.caer_id = deer.caer_id " +
                " where caer.caer_id = "+idCabEnc;

        try {

            cursor = dataBaseHelper.db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                do {

                    detEnc = new DetalleEncuestaRpta();
                    detEnc.setPreg_id((cursor.getString(0) != null) ? cursor.getString(0) : "");
                    detEnc.setValor_respuesta((cursor.getString(1) != null) ? cursor.getString(1) : "");
                    listDetEnc.add(detEnc);

                } while (cursor.moveToNext()) ;

            }
            System.out.println("OKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
            return listDetEnc;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        System.out.println("nullllllllllllllllllllllllllllllllllll");
        return null;
    }

    public List<Allegado> obtenerAllegadoEncuestada(Context context,String idCabEnc) {

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<Allegado> listAllegado = new ArrayList<Allegado>();
        Allegado allegado = null;

        String sql = " select alle.all_codigo_identificacion,alle.all_nombre,alle.all_appaterno, " +
                " alle.all_apmaterno,alle.all_gradofamiliaridad " +
                " from cab_enc_rpta caer " +
                " inner join allegado alle on caer.caer_id = alle.caer_id " +
                " where caer.caer_id = "+idCabEnc;

        try {

            cursor = dataBaseHelper.db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                do {
                    allegado = new Allegado();
                    allegado.setCodigo_identificacion((cursor.getString(0) != null) ? cursor.getString(0) : "");
                    allegado.setNombres((cursor.getString(1) != null) ? cursor.getString(1) : "");
                    allegado.setApellido_paterno((cursor.getString(2) != null) ? cursor.getString(2) : "");
                    allegado.setApellido_materno((cursor.getString(3) != null) ? cursor.getString(3) : "");
                    allegado.setGrado_familiaridad((cursor.getString(4) != null) ? cursor.getString(4) : "");

                    listAllegado.add(allegado);

                } while (cursor.moveToNext()) ;

            }
            System.out.println("OKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
            return listAllegado;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        System.out.println("nullllllllllllllllllllllllllllllllllll");
        return null;
    }
}
