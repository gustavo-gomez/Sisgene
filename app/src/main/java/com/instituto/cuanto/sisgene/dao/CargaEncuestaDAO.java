package com.instituto.cuanto.sisgene.dao;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Jesus on 10/11/2015.
 */
public class CargaEncuestaDAO {

    public CargaEncuestaDAO(){}

    public boolean cargarCatalogo(Context context, String cat_grupo, String cat_sub_grupo, String cat_nombre,
                                  String cat_descripcion, String cat_codigo){
            Cursor cursor   = null;
            DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
            String arg[] = {cat_grupo,cat_sub_grupo,cat_nombre,cat_descripcion,cat_codigo};
            boolean response = false;


            try {
                String sql = " INSERT INTO catalogo(cat_grupo,cat_sub_grupo,cat_nombre,cat_descripcion,cat_codigo)" +
                        " VALUES(?,?,?,?,?)";

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

    public boolean cargarCaratulaEncuesta(Context context, String cae_codigo, String cae_nombre, String cae_descripcion,
                                  String cae_finicio, String cae_ffin, String cae_estado, String cae_tipo_dispositivo,
                                  String cae_numero_encuestas_usu, String cae_logo_empresa, String cae_tot_supervisores,
                                  String cae_id){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {cae_codigo,cae_nombre,cae_descripcion,cae_finicio,cae_ffin,cae_estado,
                cae_tipo_dispositivo,cae_numero_encuestas_usu,cae_logo_empresa,cae_tot_supervisores,cae_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO caratula_encuesta(cae_codigo,cae_nombre,cae_descripcion,cae_finicio,cae_ffin,cae_estado," +
                    " cae_tipo_dispositivo,cae_numero_encuestas_usu,cae_logo_empresa,cae_tot_supervisores,ref_cae_id"+
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?)";

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

    public boolean cargarDet_encuesta(Context context, String dee_id, String cae_id, String ese_id){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {cae_id,ese_id,dee_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO det_encuesta(cae_id,ese_id,ref_dee_id}"+
                    " VALUES(?,?,?)";

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

    public boolean cargarPregunta(Context context, String pre_id, String pre_numero, String pre_enunciado, String pre_explicativo,
                                          String pre_comentario, String pre_guia_rpta, String pre_tipo_rpta, String pre_unica_persona,
                                          String pre_cant_maxima_items, String pre_maxNumRptas, String pre_importaOrdenRptas){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {pre_numero,pre_enunciado,pre_explicativo,pre_comentario,pre_guia_rpta,pre_tipo_rpta,
                pre_unica_persona,pre_cant_maxima_items,pre_maxNumRptas,pre_importaOrdenRptas,pre_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO pregunta(pre_numero,pre_enunciado,pre_explicativo,pre_comentario,pre_guia_rpta,pre_tipo_rpta," +
                    " pre_unica_persona,pre_cant_maxima_items,pre_nummaxrptamu,pre_importarordenrptamu,ref_pre_id"+
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?)";

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

    public boolean cargarSeccion(Context context, String sec_nombre, String sec_nota, String sec_numero_seccion, String sec_id){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {sec_nombre,sec_nota,sec_numero_seccion,sec_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO seccion(sec_nombre,sec_nota,sec_numero_seccion,ref_sec_id}"+
                    " VALUES(?,?,?,?)";

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

    public boolean cargarSubseccion(Context context, String sus_nombre, String sus_nota, String sus_numero_seccion, String sus_id){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {sus_nombre,sus_nota,sus_numero_seccion,sus_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO sub_seccion(sus_nombre,sus_nota,sus_numero_seccion,ref_sus_id}"+
                    " VALUES(?,?,?,?)";

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

    public boolean cargarEstructura_encuesta(Context context, String sec_id, String sus_id_nivel1, String sus_id_nivel2,
                                             String pre_id, String ese_id){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {sec_id,sus_id_nivel1,sus_id_nivel2,pre_id,ese_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO estructura_encuesta(sec_id,sus_id_nivel1,sus_id_nivel2,pre_id,ref_ese_id}"+
                    " VALUES(?,?,?,?,?)";

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

    public boolean cargarOpcion(Context context, String opc_id, String opc_nombre){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {opc_nombre,opc_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO opcion(opc_nombre,ref_opc_id}"+
                    " VALUES(?,?)";

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

    public boolean cargarItem(Context context, String ite_id, String ite_nombre){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {ite_nombre,ite_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO item(ite_nombre,ref_ite_id}"+
                    " VALUES(?,?)";

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

    public boolean cargarPreguntaOpcion(Context context, String pro_id, String pre_id, String opc_id,
                                             String pro_numeralOpcion, String pro_numeroPreguntaSiguiente, String pro_idEncuesta){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {pre_id,opc_id,pro_numeralOpcion,pro_numeroPreguntaSiguiente,pro_idEncuesta,pro_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO pregunta_opcion(pre_id,opc_id,pro_numeralopcion,pro_numeropreguntasiguiente,pro_idencuesta,ref_pro_id}"+
                    " VALUES(?,?,?,?,?,?)";

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

    public boolean cargarPreguntaItem(Context context, String pri_id, String pre_id, String ite_id,
                                        String pri_numeralItem){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {pre_id,ite_id,pri_numeralItem,pri_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO pregunta_item(pre_id,ite_id,pri_numeralitem,ref_pri_id}"+
                    " VALUES(?,?,?,?)";

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

    public boolean cargarFuncionalidad(Context context, String fun_id, String fun_nombre, String fun_estado){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {fun_nombre,fun_estado,fun_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO funcionalidad(fun_nombre,fun_estado,ref_fun_id}"+
                    " VALUES(?,?,?)";

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

    public boolean cargarRol(Context context, String rol_id, String rol_codigo, String rol_descripcion){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {rol_codigo,rol_descripcion,rol_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO rol(rol_codigo,rol_descripcion,ref_rol_id}"+
                    " VALUES(?,?,?)";

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

    public boolean cargarAcceso(Context context, String acc_id, String fun_id, String rol_id, String acc_estado){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {fun_id,rol_id,acc_estado,acc_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO acceso(fun_id,rol_id,acc_estado,ref_acc_id}"+
                    " VALUES(?,?,?,?)";

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

    public boolean cargarTipoDocumento(Context context, String tip_id, String tip_nombre, String tip_descripcion,
                                        String tip_longitud, String tip_estado, String tip_tipo_caracter){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {tip_nombre,tip_descripcion,tip_longitud,tip_estado,tip_tipo_caracter,tip_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO tipo_documento(tip_nombre,tip_descripcion,tip_longitud,tip_estado,tip_tipo_caracter,ref_tip_id}"+
                    " VALUES(?,?,?,?,?,?)";

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

    public boolean cargarPersona(Context context, String per_id, String id_tipo_doc, String num_documento, String nombres,
                                 String apellido_paterno, String apellido_materno, String telefono, String celular,
                                 String correo, String fecha_nacimiento, String edad, String grado_instruccion,
                                 String estado_civil, String genero){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {num_documento,nombres,apellido_paterno,apellido_materno,telefono,celular,
                correo,fecha_nacimiento,edad,grado_instruccion,estado_civil,genero,id_tipo_doc,per_id};
        boolean response = false;

        try {
            String sql = " INSERT INTO persona(per_num_documento,per_nombres,per_appaterno,per_apmaterno,per_telefono," +
                    " per_celular,per_correo,per_fnacimiento,per_edad,per_gradinstruccion,per_estadocivil,per_genero,tip_id,ref_per_id}"+
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
