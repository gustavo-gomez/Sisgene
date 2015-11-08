package com.instituto.cuanto.sisgene.dao;

import android.content.Context;
import android.database.Cursor;

import com.instituto.cuanto.sisgene.bean.CabeceraRespuesta;
import com.instituto.cuanto.sisgene.bean.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesus on 01/11/2015.
 */
public class CabeceraRespuestaDAO {

    public CabeceraRespuestaDAO(){}

    public List<CabeceraRespuesta> obtenerCabeceraRespuestas(Context context){
        CabeceraRespuesta cabeceraResp = null;
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<CabeceraRespuesta> listaCabeceraRespuesta = new ArrayList<CabeceraRespuesta>();

        try {

            cursor = dataBaseHelper.db.rawQuery(" select usu.usu_usuario, cer.caer_numero_encuesta, cer.caer_fencuesta, " +
                    " per.per_nombres, per.per_appaterno, " +
                    " per.per_apmaterno, cer.caer_hora_inicio, cer.caer_hora_fin,cer.caer_tiempo, cer.caer_estado" +
                    " from cab_enc_rpta cer" +
                    " inner join persona per on cer.per_id = per.per_id" +
                    " inner join usuario_persona usp on cer.usp_id = usp.usp_id" +
                    " inner join usuario usu on usp.usu_id = usu.usu_id ",null);

            if (cursor.moveToFirst()) {
                do {
                    cabeceraResp = new CabeceraRespuesta();
                    cabeceraResp.setUserEncuestador(cursor.getString(0));
                    cabeceraResp.setNumEncuesta(cursor.getString(1));
                    cabeceraResp.setFechaDesarrollo(cursor.getString(2));
                    cabeceraResp.setNombreEncuestado(cursor.getString(3));
                    cabeceraResp.setApPaternoEncuestado(cursor.getString(4));
                    cabeceraResp.setApMaternoEncuestado(cursor.getString(5));
                    cabeceraResp.setHoraInicio(cursor.getString(6));
                    cabeceraResp.setHoraFin(cursor.getString(7));
                    cabeceraResp.setTiempo(cursor.getString(8));
                    cabeceraResp.setEstado(cursor.getString(9));

                    listaCabeceraRespuesta.add(cabeceraResp);
                } while (cursor.moveToNext()) ;
            }

            return listaCabeceraRespuesta;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return null;

    }

    public List<CabeceraRespuesta> obtenerCabeceraRespuestas(Context context, String fIni, String fFin){
        CabeceraRespuesta cabeceraResp = null;
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<CabeceraRespuesta> listaCabeceraRespuesta = new ArrayList<CabeceraRespuesta>();

        try {

            String sql = " select usu.usu_usuario, cer.caer_numero_encuesta, cer.caer_fencuesta, " +
                    " per.per_nombres, per.per_appaterno, " +
                    " per.per_apmaterno, cer.caer_hora_inicio, cer.caer_hora_fin,cer.caer_tiempo, cer.caer_estado" +
                    " from cab_enc_rpta cer" +
                    " inner join persona per on cer.per_id = per.per_id" +
                    " inner join usuario_persona usp on cer.usp_id = usp.usp_id" +
                    " inner join usuario usu on usp.usu_id = usu.usu_id " +
                    " where usu.usu_estado = '1'";
            if(!fIni.equals("")){
                sql = sql + " and cer.caer_fencuesta >= "+fIni;
            }
            if(!fFin.equals("")){
                sql = sql + " and cer.caer_fencuesta <= "+fFin;
            }

            cursor = dataBaseHelper.db.rawQuery(sql,null);

            if (cursor.moveToFirst()) {
                do {
                    cabeceraResp = new CabeceraRespuesta();
                    cabeceraResp.setUserEncuestador(cursor.getString(0));
                    cabeceraResp.setNumEncuesta(cursor.getString(1));
                    cabeceraResp.setFechaDesarrollo(cursor.getString(2));
                    cabeceraResp.setNombreEncuestado(cursor.getString(3));
                    cabeceraResp.setApPaternoEncuestado(cursor.getString(4));
                    cabeceraResp.setApMaternoEncuestado(cursor.getString(5));
                    cabeceraResp.setHoraInicio(cursor.getString(6));
                    cabeceraResp.setHoraFin(cursor.getString(7));
                    cabeceraResp.setTiempo(cursor.getString(8));
                    cabeceraResp.setEstado(cursor.getString(9));

                    listaCabeceraRespuesta.add(cabeceraResp);
                } while (cursor.moveToNext()) ;
            }

            return listaCabeceraRespuesta;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return null;

    }

    public List<CabeceraRespuesta> obtenerCabeceraRespuestas(Context context, String estadoEnviado){
        CabeceraRespuesta cabeceraResp = null;
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<CabeceraRespuesta> listaCabeceraRespuesta = new ArrayList<CabeceraRespuesta>();

        try {

            String sql = " select usu.usu_usuario, cer.caer_numero_encuesta, cer.caer_fencuesta, " +
                    " per.per_nombres, per.per_appaterno, " +
                    " per.per_apmaterno, cer.caer_hora_inicio, cer.caer_hora_fin,cer.caer_tiempo, cer.caer_benviado" +
                    " from cab_enc_rpta cer" +
                    " inner join persona per on cer.per_id = per.per_id" +
                    " inner join usuario_persona usp on cer.usp_id = usp.usp_id" +
                    " inner join usuario usu on usp.usu_id = usu.usu_id " +
                    " where usu.usu_estado = '1'";
            if(!estadoEnviado.equals("")){
                sql = sql + " and cer.caer_benviado >= '"+estadoEnviado+"' ";
            }

            cursor = dataBaseHelper.db.rawQuery(sql,null);

            if (cursor.moveToFirst()) {
                do {
                    cabeceraResp = new CabeceraRespuesta();
                    cabeceraResp.setUserEncuestador(cursor.getString(0));
                    cabeceraResp.setNumEncuesta(cursor.getString(1));
                    cabeceraResp.setFechaDesarrollo(cursor.getString(2));
                    cabeceraResp.setNombreEncuestado(cursor.getString(3));
                    cabeceraResp.setApPaternoEncuestado(cursor.getString(4));
                    cabeceraResp.setApMaternoEncuestado(cursor.getString(5));
                    cabeceraResp.setHoraInicio(cursor.getString(6));
                    cabeceraResp.setHoraFin(cursor.getString(7));
                    cabeceraResp.setTiempo(cursor.getString(8));
                    cabeceraResp.setEstado(cursor.getString(9));

                    listaCabeceraRespuesta.add(cabeceraResp);
                } while (cursor.moveToNext()) ;
            }

            return listaCabeceraRespuesta;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return null;

    }

    public List<CabeceraRespuesta> obtenerCabeceraRespuesta(Context context, String estadoEnviado){
        CabeceraRespuesta cabeceraResp = null;
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<CabeceraRespuesta> listaCabeceraRespuesta = new ArrayList<CabeceraRespuesta>();

        try {

            String sql = " select usu.usu_usuario, cer.caer_numero_encuesta, cer.caer_fencuesta, " +
                    " per.per_nombres, per.per_appaterno, " +
                    " per.per_apmaterno, cer.caer_hora_inicio, cer.caer_hora_fin,cer.caer_tiempo, cer.caer_benviado, " +
                    " per.per_num_documento,per.per_telefono,per.per_celular,per_correo,cer.caer_nconglomerado,cer.caer_nzona_aer,"+
                    " cer.caer_nmanzana,cer.caer_nvivienda,cer.caer_nhogar,cer.caer_nombre_centropoblado, per.per_id, cer.caer_id"+
                    " from cab_enc_rpta cer" +
                    " inner join persona per on cer.per_id = per.per_id" +
                    " inner join usuario_persona usp on cer.usp_id = usp.usp_id" +
                    " inner join usuario usu on usp.usu_id = usu.usu_id " +
                    " where usu.usu_estado = '1'";
            if(!estadoEnviado.equals("")){
                sql = sql + " and cer.caer_benviado >= '"+estadoEnviado+"' ";
            }

            cursor = dataBaseHelper.db.rawQuery(sql,null);

            if (cursor.moveToFirst()) {
                do {
                    cabeceraResp = new CabeceraRespuesta();
                    cabeceraResp.setUserEncuestador(cursor.getString(0));
                    cabeceraResp.setNumEncuesta(cursor.getString(1));
                    cabeceraResp.setFechaDesarrollo(cursor.getString(2));
                    cabeceraResp.setNombreEncuestado(cursor.getString(3));
                    cabeceraResp.setApPaternoEncuestado(cursor.getString(4));
                    cabeceraResp.setApMaternoEncuestado(cursor.getString(5));
                    cabeceraResp.setHoraInicio(cursor.getString(6));
                    cabeceraResp.setHoraFin(cursor.getString(7));
                    cabeceraResp.setTiempo(cursor.getString(8));
                    cabeceraResp.setEstado(cursor.getString(9));
                    cabeceraResp.setNum_documento(cursor.getString(10));
                    cabeceraResp.setCelular(cursor.getString(12));
                    cabeceraResp.setTelefono(cursor.getString(11));
                    cabeceraResp.setCorreo(cursor.getString(13));
                    cabeceraResp.setConglomerado(cursor.getString(14));
                    cabeceraResp.setZona(cursor.getString(15));
                    cabeceraResp.setManzana(cursor.getString(16));
                    cabeceraResp.setVivienda(cursor.getString(17));
                    cabeceraResp.setHogar(cursor.getString(18));
                    cabeceraResp.setCentropoblado(cursor.getString(19));
                    cabeceraResp.setIdPesona(cursor.getInt(20));
                    cabeceraResp.setIdCabeceraEnc(cursor.getInt(21));

                    listaCabeceraRespuesta.add(cabeceraResp);
                } while (cursor.moveToNext()) ;
            }

            return listaCabeceraRespuesta;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return null;

    }

    public boolean actualizarCabEnc(Context context, String conglomerado, String zona, String manzana,
                                    String vivienda, String hogar, String centropoblado, int per_id){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String idPers = per_id+"";
        String arg[] = {conglomerado,zona,manzana,vivienda,hogar,centropoblado,idPers};
        boolean response = false;


        try {
            String sql = " UPDATE cab_enc_rpta " +
                    " SET caer_nconglomerado = ?, " +
                    " caer_nzona_aer = ?, " +
                    " caer_nmanzana = ?, " +
                    " caer_nvivienda = ?, " +
                    " caer_nhogar = ?, " +
                    " caer_nombre_centropoblado = ? " +
                    " WHERE caer_id = ?";

            dataBaseHelper.db.execSQL(sql,arg);

            response = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public boolean insertarCabEnc(Context context, String conglomerado, String zona, String manzana,
                                    String vivienda, String hogar, String centropoblado, int per_id){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String idPers = per_id+"";
        String arg[] = {conglomerado,zona,manzana,vivienda,hogar,centropoblado,idPers};
        boolean response = false;


        try {
            String sql = " INSERT INTO cab_enc_rpta (caer_nconglomerado,caer_nzona_aer,caer_nmanzana,caer_nvivienda,caer_nhogar,caer_nombre_centropoblado)" +
                    " VALUES (?,?,?,?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            response = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

}
