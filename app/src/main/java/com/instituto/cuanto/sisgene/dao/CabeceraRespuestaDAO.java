package com.instituto.cuanto.sisgene.dao;

import android.content.Context;
import android.database.Cursor;

import com.instituto.cuanto.sisgene.bean.CabeceraRespuesta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesus on 01/11/2015.
 */
public class CabeceraRespuestaDAO {

    public CabeceraRespuestaDAO() {
    }

    public boolean actualizarCabEncEstadoEnviado(Context context, int cabRpta_id) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String idCabRpta = cabRpta_id + "";
        String arg[] = {idCabRpta};
        boolean response = false;


        try {
            String sql = " UPDATE cab_enc_rpta " +
                    " SET caer_benviado = '1' " +
                    " WHERE caer_id = ?";

            dataBaseHelper.db.execSQL(sql, arg);

            response = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public List<CabeceraRespuesta> obtenerCabeceraRespuestas(Context context) {
        CabeceraRespuesta cabeceraResp = null;
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<CabeceraRespuesta> listaCabeceraRespuesta = new ArrayList<CabeceraRespuesta>();

        try {

            cursor = dataBaseHelper.db.rawQuery(" select usu.usu_usuario, cer.caer_numero_encuesta, cer.caer_fencuesta, " +
                    " per.per_nombres, per.per_appaterno, " +
                    " per.per_apmaterno, cer.caer_hora_inicio, cer.caer_hora_fin,cer.caer_tiempo, cer.caer_estado" +
                    " from cab_enc_rpta cer" +
                    " inner join persona per on cer.per_id = per.per_id" +
                    " inner join usuario_persona usp on cer.usp_id = usp.usp_id" +
                    " inner join usuario usu on usp.usu_id = usu.usu_id ", null);

            if (cursor.moveToFirst()) {
                System.out.println("ENTRO A TRAER INFO");
                do {
                    cabeceraResp = new CabeceraRespuesta();
                    cabeceraResp.setUserEncuestador((cursor.getString(0) != null) ? cursor.getString(0) : "");
                    cabeceraResp.setNumEncuesta((cursor.getString(1) != null) ? cursor.getString(1) : "");
                    cabeceraResp.setFechaDesarrollo((cursor.getString(2) != null) ? cursor.getString(2) : "");
                    cabeceraResp.setNombreEncuestado((cursor.getString(3) != null) ? cursor.getString(3) : "");
                    cabeceraResp.setApPaternoEncuestado((cursor.getString(4) != null) ? cursor.getString(4) : "");
                    cabeceraResp.setApMaternoEncuestado((cursor.getString(5) != null) ? cursor.getString(5) : "");
                    cabeceraResp.setHoraInicio((cursor.getString(6) != null) ? cursor.getString(6) : "");
                    cabeceraResp.setHoraFin((cursor.getString(7) != null) ? cursor.getString(7) : "");
                    cabeceraResp.setTiempo((cursor.getString(8) != null) ? cursor.getString(8) : "");
                    cabeceraResp.setEstado((cursor.getString(9) != null) ? cursor.getString(9) : "");

                    listaCabeceraRespuesta.add(cabeceraResp);
                } while (cursor.moveToNext());
            }

            return listaCabeceraRespuesta;
        } catch (Exception ex) {
            System.out.println("ERROR AL TRAER CAB_ENC_RPTA SIN FILTROS");
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return null;

    }

    public int obtenerUltimoNumeroEncuestaCabecera(Context context) {
        int UltimoNumeroEncuestaCabecera = -1;
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<CabeceraRespuesta> listaCabeceraRespuesta = new ArrayList<CabeceraRespuesta>();

        try {

            cursor = dataBaseHelper.db.rawQuery("select cab.caer_numero_encuesta from cab_enc_rpta cab order by cab.caer_numero_encuesta desc ", null);

            if (cursor.moveToFirst()) {
                UltimoNumeroEncuestaCabecera = cursor.getInt(0);
            }
            System.out.println("obtenerUltimoNumeroEncuestaCabecera,OK  UltimoNumeroEncuestaCabecera:" + UltimoNumeroEncuestaCabecera);
            return UltimoNumeroEncuestaCabecera;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        System.out.println("obtenerUltimoNumeroEncuestaCabecera, ERROR UltimoNumeroEncuestaCabecera:" + UltimoNumeroEncuestaCabecera);
        return UltimoNumeroEncuestaCabecera;

    }

    public List<CabeceraRespuesta> obtenerCabeceraRespuestas(Context context, String fIni, String fFin) {
        CabeceraRespuesta cabeceraResp = null;
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<CabeceraRespuesta> listaCabeceraRespuesta = new ArrayList<CabeceraRespuesta>();
        System.out.println("ENTRO A CARGAR CABECERA POR FECHAS");
        System.out.println("FECHA INICIO : " + fIni);
        System.out.println("FECHA FIN : " + fFin);

        try {

            String sql = " select usu.usu_usuario, cer.caer_numero_encuesta, cer.caer_fencuesta, " +
                    " per.per_nombres, per.per_appaterno, " +
                    " per.per_apmaterno, cer.caer_hora_inicio, cer.caer_hora_fin,cer.caer_tiempo, cer.caer_benviado, cer.caer_id" +
                    " from cab_enc_rpta cer" +
                    " inner join persona per on cer.per_id = per.per_id" +
                    " inner join usuario_persona usp on cer.usp_id = usp.usp_id" +
                    " inner join usuario usu on usp.usu_id = usu.usu_id ";
            //" where cer.caer_benviado = '0'";
            if (!fIni.equals("")) {
                sql = sql + " and cer.caer_fencuesta >= " + fIni;
            }
            if (!fFin.equals("")) {
                sql = sql + " and cer.caer_fencuesta <= " + fFin;
            }

            cursor = dataBaseHelper.db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                System.out.println("OK1");
                do {
                    System.out.println("OK2");
                    cabeceraResp = new CabeceraRespuesta();
                    cabeceraResp.setUserEncuestador((cursor.getString(0) != null) ? cursor.getString(0) : "");
                    cabeceraResp.setNumEncuesta((cursor.getString(1) != null) ? cursor.getString(1) : "");
                    cabeceraResp.setFechaDesarrollo((cursor.getString(2) != null) ? cursor.getString(2) : "");
                    cabeceraResp.setNombreEncuestado((cursor.getString(3) != null) ? cursor.getString(3) : "");
                    cabeceraResp.setApPaternoEncuestado((cursor.getString(4) != null) ? cursor.getString(4) : "");
                    cabeceraResp.setApMaternoEncuestado((cursor.getString(5) != null) ? cursor.getString(5) : "");
                    cabeceraResp.setHoraInicio((cursor.getString(6) != null) ? cursor.getString(6) : "");
                    cabeceraResp.setHoraFin((cursor.getString(7) != null) ? cursor.getString(7) : "");
                    cabeceraResp.setTiempo((cursor.getString(8) != null) ? cursor.getString(8) : "");
                    cabeceraResp.setEstado((cursor.getString(9) != null) ? cursor.getString(9) : "");
                    cabeceraResp.setIdCabeceraEnc(cursor.getInt(10));

                    listaCabeceraRespuesta.add(cabeceraResp);
                } while (cursor.moveToNext());
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

    public List<CabeceraRespuesta> obtenerCabeceraRespuestas(Context context, String estadoEnviado) {
        CabeceraRespuesta cabeceraResp = null;
        Cursor cursor = null;
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
            if (!estadoEnviado.equals("")) {
                sql = sql + " and cer.caer_benviado = '" + estadoEnviado + "' ";
            }

            cursor = dataBaseHelper.db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                do {
                    cabeceraResp = new CabeceraRespuesta();
                    cabeceraResp.setUserEncuestador((cursor.getString(0) != null) ? cursor.getString(0) : "");
                    cabeceraResp.setNumEncuesta((cursor.getString(1) != null) ? cursor.getString(1) : "");
                    cabeceraResp.setFechaDesarrollo((cursor.getString(2) != null) ? cursor.getString(2) : "");
                    cabeceraResp.setNombreEncuestado((cursor.getString(3) != null) ? cursor.getString(3) : "");
                    cabeceraResp.setApPaternoEncuestado((cursor.getString(4) != null) ? cursor.getString(4) : "");
                    cabeceraResp.setApMaternoEncuestado((cursor.getString(5) != null) ? cursor.getString(5) : "");
                    cabeceraResp.setHoraInicio((cursor.getString(6) != null) ? cursor.getString(6) : "");
                    cabeceraResp.setHoraFin((cursor.getString(7) != null) ? cursor.getString(7) : "");
                    cabeceraResp.setTiempo((cursor.getString(8) != null) ? cursor.getString(8) : "");
                    cabeceraResp.setEstado((cursor.getString(9) != null) ? cursor.getString(9) : "");

                    listaCabeceraRespuesta.add(cabeceraResp);
                } while (cursor.moveToNext());
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

    public List<CabeceraRespuesta> obtenerCabeceraRespuesta(Context context, String estadoEnviado) {
        CabeceraRespuesta cabeceraResp = null;
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<CabeceraRespuesta> listaCabeceraRespuesta = new ArrayList<CabeceraRespuesta>();

        try {

            String sql = " select usu.usu_usuario, cer.caer_numero_encuesta, cer.caer_fencuesta, " +
                    " per.per_nombres, per.per_appaterno, " +
                    " per.per_apmaterno, cer.caer_hora_inicio, cer.caer_hora_fin,cer.caer_tiempo, cer.caer_benviado, " +
                    " per.per_num_documento,per.per_telefono,per.per_celular,per_correo,cer.caer_nconglomerado,cer.caer_nzona_aer," +
                    " cer.caer_nmanzana,cer.caer_nvivienda,cer.caer_nhogar,cer.caer_nombre_centropoblado, per.per_id, cer.caer_id" +
                    " from cab_enc_rpta cer" +
                    " inner join persona per on cer.per_id = per.per_id" +
                    " inner join usuario_persona usp on cer.usp_id = usp.usp_id" +
                    " inner join usuario usu on usp.usu_id = usu.usu_id " +
                    " where usu.usu_estado = '1'";
            if (!estadoEnviado.equals("")) {
                sql = sql + " and cer.caer_benviado = '" + estadoEnviado + "' ";
            }

            cursor = dataBaseHelper.db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                do {
                    cabeceraResp = new CabeceraRespuesta();
                    cabeceraResp.setUserEncuestador((cursor.getString(0) != null) ? cursor.getString(0) : "");
                    cabeceraResp.setNumEncuesta((cursor.getString(1) != null) ? cursor.getString(1) : "");
                    cabeceraResp.setFechaDesarrollo((cursor.getString(2) != null) ? cursor.getString(2) : "");
                    cabeceraResp.setNombreEncuestado((cursor.getString(3) != null) ? cursor.getString(3) : "");
                    cabeceraResp.setApPaternoEncuestado((cursor.getString(4) != null) ? cursor.getString(4) : "");
                    cabeceraResp.setApMaternoEncuestado((cursor.getString(5) != null) ? cursor.getString(5) : "");
                    cabeceraResp.setHoraInicio((cursor.getString(6) != null) ? cursor.getString(6) : "");
                    cabeceraResp.setHoraFin((cursor.getString(7) != null) ? cursor.getString(7) : "");
                    cabeceraResp.setTiempo((cursor.getString(8) != null) ? cursor.getString(8) : "");
                    cabeceraResp.setEstado((cursor.getString(9) != null) ? cursor.getString(9) : "");
                    cabeceraResp.setNum_documento((cursor.getString(10) != null) ? cursor.getString(10) : "");
                    cabeceraResp.setCelular((cursor.getString(12) != null) ? cursor.getString(12) : "");
                    cabeceraResp.setTelefono((cursor.getString(11) != null) ? cursor.getString(11) : "");
                    cabeceraResp.setCorreo((cursor.getString(13) != null) ? cursor.getString(13) : "");
                    cabeceraResp.setConglomerado((cursor.getString(14) != null) ? cursor.getString(14) : "");
                    cabeceraResp.setZona((cursor.getString(15) != null) ? cursor.getString(15) : "");
                    cabeceraResp.setManzana((cursor.getString(16) != null) ? cursor.getString(16) : "");
                    cabeceraResp.setVivienda((cursor.getString(17) != null) ? cursor.getString(17) : "");
                    cabeceraResp.setHogar((cursor.getString(18) != null) ? cursor.getString(18) : "");
                    cabeceraResp.setCentropoblado((cursor.getString(19) != null) ? cursor.getString(19) : "");
                    cabeceraResp.setIdPesona(cursor.getInt(20));
                    cabeceraResp.setIdCabeceraEnc(cursor.getInt(21));

                    listaCabeceraRespuesta.add(cabeceraResp);
                } while (cursor.moveToNext());
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
                                    String vivienda, String hogar, String centropoblado, int per_id) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String idPers = per_id + "";
        String arg[] = {conglomerado, zona, manzana, vivienda, hogar, centropoblado, idPers};
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

            dataBaseHelper.db.execSQL(sql, arg);

            response = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public int obtenerHastaNumEnc(Context context, String usu_id) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {usu_id};
        int HastaNumEnc = -1;


        try {
            String sql = " select usp.usp_hasta_numenc " +
                    " from usuario_persona usp " +
                    " inner join usuario usu on usu.usu_id = usp.usu_id " +
                    " where usu.usu_usuario  = ?";

            cursor = dataBaseHelper.db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                HastaNumEnc = cursor.getInt(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return HastaNumEnc;

    }

    public boolean insertarCabEnc(Context context, String conglomerado, String zona, String manzana,
                                  String vivienda, String hogar, String centropoblado, int per_id) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String idPers = per_id + "";
        String arg[] = {conglomerado, zona, manzana, vivienda, hogar, centropoblado, idPers};
        boolean response = false;

        System.out.println("conglomerad : " + conglomerado);
        System.out.println("zonA : " + zona);
        System.out.println("manzana : " + manzana);
        System.out.println("vivienda : " + vivienda);
        System.out.println("hogar : " + hogar);
        System.out.println("centor poblado : " + centropoblado);
        System.out.println("IDPERS : " + idPers);

        try {
            String sql = " INSERT INTO cab_enc_rpta (caer_nconglomerado,caer_nzona_aer,caer_nmanzana," +
                    "caer_nvivienda,caer_nhogar,caer_nombre_centropoblado,per_id)" +
                    " VALUES (?,?,?,?,?,?,?)";

            dataBaseHelper.db.execSQL(sql, arg);

            System.out.println("INSERTO CORRECTAMENTE LA CAB_ENC_RPTA");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL GUARDAR CAB_EN_RPTA : " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public boolean insertarCabEnc2(Context context, String caer_numero_encuesta, String caer_estado, String caer_fencuesta,
                                   String caer_observaciones, String caer_nconglomerado, String caer_nzona_aer, String caer_nmanzana, String caer_nvivienda,
                                   String caer_nhogar, String caer_narea, String caer_ncondicion, String caer_codigo_informante, String caer_hora_inicio,
                                   String caer_hora_fin, String caer_tiempo, String caer_codigo_centropoblado, String caer_nombre_centropoblado,
                                   String caer_categoria_centropoblado, String caer_benviado, String caer_fencuestaenviada, String usp_id, String per_id, String dir_id) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {caer_numero_encuesta, caer_estado, caer_fencuesta, caer_observaciones, caer_nconglomerado, caer_nzona_aer,
                caer_nmanzana, caer_nvivienda, caer_nhogar, caer_narea, caer_ncondicion, caer_codigo_informante, caer_hora_inicio, caer_hora_fin,
                caer_tiempo, caer_codigo_centropoblado, caer_nombre_centropoblado, caer_categoria_centropoblado, caer_benviado, caer_fencuestaenviada,
                usp_id, per_id, dir_id};

        boolean response = false;

        try {
            String sql = " INSERT INTO cab_enc_rpta (caer_numero_encuesta,caer_estado,caer_fencuesta," +
                    " caer_observaciones,caer_nconglomerado,caer_nzona_aer,caer_nmanzana,caer_nvivienda, " +
                    " caer_nhogar,caer_narea,caer_ncondicion,caer_codigo_informante,caer_hora_inicio, " +
                    " caer_hora_fin,caer_tiempo,caer_codigo_centropoblado,caer_nombre_centropoblado, " +
                    " caer_categoria_centropoblado,caer_benviado,caer_fencuestaenviada,usp_id,per_id,dir_id)" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            dataBaseHelper.db.execSQL(sql, arg);

            response = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public boolean insertarDetEnc(Context context, String valorRespuesta, String idCabEnc, String idPregunta) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {valorRespuesta, idCabEnc, idPregunta};
        boolean response = false;


        try {
            String sql = " INSERT INTO det_enc_rpta (deer_valor_respuesta,caer_id,pre_id)" +
                    " VALUES (?,?,?)";

            dataBaseHelper.db.execSQL(sql, arg);
            System.out.println("nsertarDetEnc ****OK****");
            response = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        System.out.println("insertarDetEnc ****ERROR****");
        return response;

    }

    public CabeceraRespuesta obteneridUltimaCabecera(Context context) {
        CabeceraRespuesta cabeceraResp = null;
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        try {

            cursor = dataBaseHelper.db.rawQuery(" select cab.caer_id from cab_enc_rpta cab order by cab.caer_id desc ", null);

            if (cursor.moveToFirst()) {
                cabeceraResp = new CabeceraRespuesta();
                cabeceraResp.setIdCabeceraEnc(Integer.parseInt(cursor.getString(0)));
            }

            return cabeceraResp;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return null;

    }

    public boolean actualizarCabEncFinalEjecucion(Context context, String estado, String horaFin, String tiempo,
                                                  String bEnviado, String fecha_envio, String obs,int cabRpta_id) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String idCabRpta = cabRpta_id + "";
        String arg[] = {estado, horaFin, tiempo, bEnviado, fecha_envio, obs, idCabRpta};
        boolean response = false;


        try {
            String sql = " UPDATE cab_enc_rpta " +
                    " SET caer_estado = ?, " +
                    " caer_hora_fin = ?, " +
                    " caer_tiempo = ?, " +
                    " caer_benviado = ?, " +
                    " caer_fencuestaenviada = ? " +
                    " caer_observaciones = ? " +
                    " WHERE caer_id = ?";

            dataBaseHelper.db.execSQL(sql, arg);

            response = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public List<String> obtenerRangoFechasEncuesta(Context context, String username) {

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<String> rangoFechas = new ArrayList<String>();

        try {

            cursor = dataBaseHelper.db.rawQuery(" select cae.cae_finicio,cae.cae_ffin " +
                    " from caratula_encuesta cae " +
                    " inner join usuario_persona usp on usp.cae_id = cae.cae_id " +
                    " inner join usuario usu on usu.usu_id = usp.usu_id " +
                    " where usu.usu_usuario = '" + username + "' ", null);

            if (cursor.moveToFirst()) {
                rangoFechas.add(cursor.getString(0));
                rangoFechas.add(cursor.getString(1));
            }

            return rangoFechas;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;

    }

    public int obtenerCantidadEncuestas(Context context) {

        int iCantidadUsuarios = -1;

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        try {
            cursor = dataBaseHelper.db.rawQuery("select count(*) from cab_enc_rpta", null);

            if (cursor.moveToFirst()) {
                iCantidadUsuarios = cursor.getInt(0);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return iCantidadUsuarios;

    }


    public int obtenerDesdeNumEnc(Context context, String usu_id) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {usu_id};
        int HastaNumEnc = -1;


        try {
            String sql = " select usp.usp_desde_numenc " +
                    " from usuario_persona usp " +
                    " inner join usuario usu on usu.usu_id = usp.usu_id " +
                    " where usu.usu_usuario  = ?";

            cursor = dataBaseHelper.db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                HastaNumEnc = cursor.getInt(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        System.out.println("obtenerDesdeNumEnc, HastaNumEnc:" + HastaNumEnc);
        return HastaNumEnc;

    }

}
