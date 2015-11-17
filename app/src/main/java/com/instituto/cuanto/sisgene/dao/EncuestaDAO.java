package com.instituto.cuanto.sisgene.dao;

import android.content.Context;
import android.database.Cursor;

import com.instituto.cuanto.sisgene.bean.EncuestaPregunta;
import com.instituto.cuanto.sisgene.bean.PreguntaAlternativa;
import com.instituto.cuanto.sisgene.bean.PreguntaItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesus on 08/11/2015.
 */
public class EncuestaDAO {

    public EncuestaDAO() {
    }

    public String obtenerCodigoEncuesta(Context context) {

        String response = "";

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        try {
            cursor = dataBaseHelper.db.rawQuery("select cae_codigo from caratula_encuesta", null);

            if (cursor.moveToFirst()) {
                do {
                    response = cursor.getString(0);
                } while (cursor.moveToNext());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public EncuestaPregunta obtenerPreguntaEncuesta(Context context) {

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        EncuestaPregunta encuestaPregunta = null;

        String sql = " SELECT distinct sec.sec_id,sec.sec_nombre,sec.sec_nota,sec.sec_numero_seccion," +
                " sus1.sus_id,sus1.sus_nombre,sus1.sus_nota,sus1.sus_numero_subseccion," +
                " pre.pre_id,pre.pre_numero,pre.pre_enunciado,pre.pre_explicativo,pre.pre_comentario," +
                " pre.pre_guia_rpta,pre.pre_tipo_rpta,pre.pre_unica_persona,pre.pre_cant_maxima_items," +
                " pre.pre_nummaxrptamu,pre.pre_importarordenrptamu" +
                " FROM  det_encuesta dee " +
                " INNER JOIN estructura_encuesta ese ON ese.ese_id = dee.ese_id" +
                " INNER JOIN seccion sec ON sec.sec_id = ese.sec_id" +
                " INNER JOIN pregunta pre ON pre.pre_id = ese.pre_id" +
                " LEFT JOIN pregunta_opcion pro ON pro.pre_id = pre.pre_id" +
                " LEFT JOIN opcion opc ON opc.opc_id = pro.opc_id" +
                " LEFT JOIN sub_seccion sus1 ON sus1.sus_id = ese.sus_id_nivel1" +
                " LEFT JOIN pregunta_item pri ON pri.pre_id = pre.pre_id" +
                " LEFT JOIN item ite ON ite.ite_id = pri.ite_id" +
                " ORDER BY pre.pre_id ASC";

        try {

            cursor = dataBaseHelper.db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                System.out.println("cahuana gay");
                encuestaPregunta = new EncuestaPregunta();
                encuestaPregunta.setSec_id((cursor.getString(0) != null) ? cursor.getString(0) : "");
                encuestaPregunta.setSec_nombre((cursor.getString(1) != null) ? cursor.getString(1) : "");
                encuestaPregunta.setSec_nota((cursor.getString(2) != null) ? cursor.getString(2) : "");
                encuestaPregunta.setSec_numero_seccion((cursor.getString(3) != null) ? cursor.getString(3) : "");
                encuestaPregunta.setSus_id((cursor.getString(4) != null) ? cursor.getString(4) : "");
                encuestaPregunta.setSus_nombre((cursor.getString(5) != null) ? cursor.getString(5) : "");
                encuestaPregunta.setSus_nota((cursor.getString(6) != null) ? cursor.getString(6) : "");
                encuestaPregunta.setSus_numero_subseccion((cursor.getString(7) != null) ? cursor.getString(7) : "");
                encuestaPregunta.setPre_id((cursor.getString(8) != null) ? cursor.getString(8) : "");
                encuestaPregunta.setPre_numero((cursor.getString(9) != null) ? cursor.getString(9) : "");
                encuestaPregunta.setPre_enunciado((cursor.getString(10) != null) ? cursor.getString(10) : "");
                encuestaPregunta.setPre_explicativo((cursor.getString(11) != null) ? cursor.getString(11) : "");
                encuestaPregunta.setPre_comentario((cursor.getString(12) != null) ? cursor.getString(12) : "");
                encuestaPregunta.setPre_guia_rpta((cursor.getString(13) != null) ? cursor.getString(13) : "");
                encuestaPregunta.setPre_tipo_rpta((cursor.getString(14) != null) ? cursor.getString(14) : "");
                encuestaPregunta.setPre_unica_persona((cursor.getString(15) != null) ? cursor.getString(15) : "");
                encuestaPregunta.setPre_cant_maxima_items((cursor.getString(16) != null) ? cursor.getString(16) : "");
                encuestaPregunta.setPre_nummaxrptamu((cursor.getString(17) != null) ? cursor.getString(17) : "");
                encuestaPregunta.setPre_importarordenrptamu((cursor.getString(18) != null) ? cursor.getString(18) : "");

            }
            System.out.println("OKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
            return encuestaPregunta;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        System.out.println("nullllllllllllllllllllllllllllllllllll");
        return null;

    }

    public EncuestaPregunta obtenerPreguntaEncuesta(Context context, String idPregunta) {

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        EncuestaPregunta encuestaPregunta = null;

        String sql = " SELECT distinct sec.sec_id,sec.sec_nombre,sec.sec_nota,sec.sec_numero_seccion," +
                " sus1.sus_id,sus1.sus_nombre,sus1.sus_nota,sus1.sus_numero_subseccion," +
                " pre.pre_id,pre.pre_numero,pre.pre_enunciado,pre.pre_explicativo,pre.pre_comentario," +
                " pre.pre_guia_rpta,pre.pre_tipo_rpta,pre.pre_unica_persona,pre.pre_cant_maxima_items," +
                " pre.pre_nummaxrptamu,pre.pre_importarordenrptamu" +
                " FROM  det_encuesta dee " +
                " INNER JOIN estructura_encuesta ese ON ese.ese_id = dee.ese_id" +
                " INNER JOIN seccion sec ON sec.sec_id = ese.sec_id" +
                " INNER JOIN pregunta pre ON pre.pre_id = ese.pre_id" +
                " LEFT JOIN pregunta_opcion pro ON pro.pre_id = pre.pre_id" +
                " LEFT JOIN opcion opc ON opc.opc_id = pro.opc_id" +
                " LEFT JOIN sub_seccion sus1 ON sus1.sus_id = ese.sus_id_nivel1" +
                " LEFT JOIN pregunta_item pri ON pri.pre_id = pre.pre_id" +
                " LEFT JOIN item ite ON ite.ite_id = pri.ite_id" +
                " WHERE pre.pre_id > " + idPregunta + " " +
                " ORDER BY pre.pre_id ASC";

        try {

            cursor = dataBaseHelper.db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {
                System.out.println("cursor");
                encuestaPregunta = new EncuestaPregunta();
                encuestaPregunta.setSec_id((cursor.getString(0) != null) ? cursor.getString(0) : "");
                encuestaPregunta.setSec_nombre((cursor.getString(1) != null) ? cursor.getString(1) : "");
                encuestaPregunta.setSec_nota((cursor.getString(2) != null) ? cursor.getString(2) : "");
                encuestaPregunta.setSec_numero_seccion((cursor.getString(3) != null) ? cursor.getString(3) : "");
                encuestaPregunta.setSus_id((cursor.getString(4) != null) ? cursor.getString(4) : "");
                encuestaPregunta.setSus_nombre((cursor.getString(5) != null) ? cursor.getString(5) : "");
                encuestaPregunta.setSus_nota((cursor.getString(6) != null) ? cursor.getString(6) : "");
                encuestaPregunta.setSus_numero_subseccion((cursor.getString(7) != null) ? cursor.getString(7) : "");
                encuestaPregunta.setPre_id((cursor.getString(8) != null) ? cursor.getString(8) : "");
                encuestaPregunta.setPre_numero((cursor.getString(9) != null) ? cursor.getString(9) : "");
                encuestaPregunta.setPre_enunciado((cursor.getString(10) != null) ? cursor.getString(10) : "");
                encuestaPregunta.setPre_explicativo((cursor.getString(11) != null) ? cursor.getString(11) : "");
                encuestaPregunta.setPre_comentario((cursor.getString(12) != null) ? cursor.getString(12) : "");
                encuestaPregunta.setPre_guia_rpta((cursor.getString(13) != null) ? cursor.getString(13) : "");
                encuestaPregunta.setPre_tipo_rpta((cursor.getString(14) != null) ? cursor.getString(14) : "");
                encuestaPregunta.setPre_unica_persona((cursor.getString(15) != null) ? cursor.getString(15) : "");
                encuestaPregunta.setPre_cant_maxima_items((cursor.getString(16) != null) ? cursor.getString(16) : "");
                encuestaPregunta.setPre_nummaxrptamu((cursor.getString(17) != null) ? cursor.getString(17) : "");
                encuestaPregunta.setPre_importarordenrptamu((cursor.getString(18) != null) ? cursor.getString(18) : "");

            }
            System.out.println("return OK");
            return encuestaPregunta;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        System.out.println("NULLLLL");
        return null;

    }

    public List<PreguntaAlternativa> obtenerAlternativas(Context context, String idPregunta) {

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        PreguntaAlternativa preguntaAlternativa = null;
        List<PreguntaAlternativa> listPreguntaAlterntiva = new ArrayList<PreguntaAlternativa>();

        String sql = "SELECT opc.opc_id, opc.opc_nombre, pro.pro_numeralopcion, pro.pro_numeropreguntasiguiente" +
                " FROM  pregunta pre" +
                " INNER JOIN pregunta_opcion pro ON pro.pre_id = pre.pre_id" +
                " INNER JOIN opcion opc ON opc.opc_id = pro.opc_id" +
                " WHERE pre.pre_id = " + idPregunta + " ";

        try {

            cursor = dataBaseHelper.db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {

                do {
                    preguntaAlternativa = new PreguntaAlternativa();
                    preguntaAlternativa.setOpc_id(cursor.getString(0));
                    preguntaAlternativa.setOpc_nombre(cursor.getString(1));
                    preguntaAlternativa.setPro_numeralopcion(cursor.getString(2));
                    preguntaAlternativa.setPro_numeropreguntasiguiente(cursor.getString(3));

                    listPreguntaAlterntiva.add(preguntaAlternativa);

                } while (cursor.moveToNext());
            }

            return listPreguntaAlterntiva;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public List<PreguntaItem> obtenerItems(Context context, String idPregunta) {

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        PreguntaItem preguntaItem = null;
        List<PreguntaItem> listPreguntaItmes = new ArrayList<PreguntaItem>();

        String sql = " SELECT SELECT ite.ite_id,ite.ite_nombre,pri.pri_numeralitem" +
                " FROM pregunta_item pri" +
                " INNER JOIN item ite on pri.ite_id = ite.ite_id" +
                " WHERE pri.pre_id = " + idPregunta + " ";

        try {

            cursor = dataBaseHelper.db.rawQuery(sql, null);

            if (cursor.moveToFirst()) {

                do {
                    preguntaItem = new PreguntaItem();
                    preguntaItem.setIte_id(cursor.getString(0));
                    preguntaItem.setIte_nombre(cursor.getString(1));
                    preguntaItem.setPri_numeralitem(cursor.getString(2));

                    listPreguntaItmes.add(preguntaItem);

                } while (cursor.moveToNext());
            }

            return listPreguntaItmes;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
}
