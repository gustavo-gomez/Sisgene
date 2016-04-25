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

                System.out.println("CARGO CATALOGO OK");
                response = true;

            } catch (Exception ex) {
                System.out.println("ERROR AL GUARDAR CATALOGOS: "+ex.getMessage());
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
        String arg[] = {cae_id,cae_codigo,cae_nombre,cae_descripcion,cae_finicio,cae_ffin,cae_estado,
                cae_tipo_dispositivo,cae_numero_encuestas_usu,cae_logo_empresa,cae_tot_supervisores};
        boolean response = false;


        try {
            String sql = " INSERT INTO caratula_encuesta(cae_id,cae_codigo,cae_nombre,cae_descripcion,cae_finicio,cae_ffin,cae_estado," +
                    " cae_tipo_dispositivo,cae_numero_encuestas_usu,cae_logo_empresa,cae_tot_supervisores)"+
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("CARGO OK CARATULA_ENCUESTA");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL CARGAR CARATULA ENCUESTA: "+ex.getMessage());
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
        String arg[] = {dee_id,cae_id,ese_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO det_encuesta(dee_id,cae_id,ese_id)"+
                    " VALUES(?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("CAERGO OK DET_ENCUESTA");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL CARGA DET_ENCUESTA : "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public boolean cargarPregunta(Context context, String pre_id, String pre_numero, String pre_enunciado, String pre_explicativo,
                                          String pre_comentario, String pre_guia_rpta, String pre_tipo_rpta, String pre_unica_persona,
                                          String pre_cant_maxima_items, String pre_maxNumRptas, String pre_importaOrdenRptas,
                                          String pre_subtipo, String pre_tiponumerico, String pre_desde, String pre_hasta){

        System.out.println(" ***  -->> "+pre_subtipo+" - "+pre_tiponumerico+" - "+pre_desde+" - "+pre_hasta);

        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {pre_id,pre_numero,pre_enunciado,pre_explicativo,pre_comentario,pre_guia_rpta,pre_tipo_rpta,
                pre_unica_persona,pre_cant_maxima_items,pre_maxNumRptas,pre_importaOrdenRptas,pre_subtipo,pre_tiponumerico,pre_desde,pre_hasta};
        boolean response = false;


        try {
            String sql = " INSERT INTO pregunta(pre_id,pre_numero,pre_enunciado,pre_explicativo,pre_comentario,pre_guia_rpta,pre_tipo_rpta," +
                    " pre_unica_persona,pre_cant_maxima_items,pre_nummaxrptamu,pre_importarordenrptamu,pre_subtipo,pre_tiponumerico,pre_desde,pre_hasta)"+
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("OK AL CARGA PREGUNTA ");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL CARGA PREGUNTA: "+ex.getMessage());
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
        String arg[] = {sec_id,sec_nombre,sec_nota,sec_numero_seccion};
        boolean response = false;


        try {
            String sql = " INSERT INTO seccion(sec_id,sec_nombre,sec_nota,sec_numero_seccion)"+
                    " VALUES(?,?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("OK AL CARGA PREGUNTA");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL CARGA SECCION: "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public boolean cargarSubseccion(Context context, String sus_id, String sus_nombre, String sus_nota, String sus_numero_seccion){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {sus_id,sus_nombre,sus_nota,sus_numero_seccion};
        boolean response = false;


        try {
            String sql = " INSERT INTO sub_seccion(sus_id,sus_nombre,sus_nota,sus_numero_subseccion)"+
                    " VALUES(?,?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("OK AL CARGA SUBSECCION");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL CARGA SUBSECCION: "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public boolean cargarEstructura_encuesta(Context context, String sec_id, String sus_id_nivel1, String sus_id_nivel2,
                                             String pre_id, String ese_id){
        System.out.println("PRE_ID _ --> "+pre_id);

        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {ese_id,sec_id,sus_id_nivel1,sus_id_nivel2,pre_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO estructura_encuesta(ese_id,sec_id,sus_id_nivel1,sus_id_nivel2,pre_id)"+
                    " VALUES(?,?,?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("ERROR AL CARGA ESTRUCTURA ENCUESTA");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL CARGA ESTRUCTURA_ENCUESTA: "+ex.getMessage());
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
        String arg[] = {opc_id,opc_nombre};
        boolean response = false;

        System.out.println(":::____::::>>>> "+opc_id);
        System.out.println(":::____::::>>>> "+opc_nombre);
        try {
            String sql = " INSERT INTO opcion(opc_id,opc_nombre)"+
                    " VALUES(?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("ok AL CARGA opcion");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL CARGA OPCION: "+ex.getMessage());
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
        String arg[] = {ite_id,ite_nombre};
        boolean response = false;

        System.out.println("ITEEEEM ID : "+ite_id+" -- NOMBRE : "+ite_nombre);


        try {
            String sql = " INSERT INTO item(ite_id,ite_nombre)"+
                    " VALUES(?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("OK AL CARGA ITEM");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL CARGA ITEM: "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public boolean cargarPreguntaOpcion(Context context, String pro_id, String pre_id, String opc_id,
                                             String pro_numeralOpcion, String pro_numeroPreguntaSiguiente, String pro_idEncuesta, String pro_valor){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {pro_id,pre_id,opc_id,pro_numeralOpcion,pro_numeroPreguntaSiguiente,pro_idEncuesta,pro_valor};
        boolean response = false;


        try {
            String sql = " INSERT INTO pregunta_opcion(pro_id,pre_id,opc_id,pro_numeralopcion,pro_numeropreguntasiguiente,pro_idencuesta,pro_valor)"+
                    " VALUES(?,?,?,?,?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("OK AL CARGA PREGUNTA_OPCION");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL CARGA RPEGUNTA_OPCION: "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public boolean cargarPreguntaItem(Context context, String pri_id, String pre_id, String ite_id,
                                        String pri_numeralItem, String pri_valor){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {pri_id,pre_id,ite_id,pri_numeralItem,pri_valor};
        boolean response = false;

        System.out.println("PRI_ID : "+pri_id + " -- PRE_ID : "+pre_id + " -- ITEM_ID : "+ite_id+ " -- PRI_NUMERALITEM : "+pri_numeralItem);

        try {
            String sql = " INSERT INTO pregunta_item(pri_id,pre_id,ite_id,pri_numeralitem,pri_valor)"+
                    " VALUES(?,?,?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("OK AL CARGA PREGUNTA_ITEM");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL CARGA PREGUNTA_ITEM: "+ex.getMessage());
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
        String arg[] = {fun_id,fun_nombre,fun_estado};
        boolean response = false;

        try {
            String sql = " INSERT INTO funcionalidad(fun_id,fun_nombre,fun_estado)"+
                    " VALUES(?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("OK AL CARGA FUNCIONALIDAD");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL CARGA FUNCIONALIDAD: "+ex.getMessage());
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
        String arg[] = {rol_id,rol_codigo,rol_descripcion};
        boolean response = false;


        try {
            String sql = " INSERT INTO rol(rol_id,rol_codigo,rol_descripcion)"+
                    " VALUES(?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("OK AL CARGA rROL");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL CARGA ROL: "+ex.getMessage());
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
        String arg[] = {acc_id,fun_id,rol_id,acc_estado};
        boolean response = false;


        try {
            String sql = " INSERT INTO acceso(acc_id,fun_id,rol_id,acc_estado)"+
                    " VALUES(?,?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("OK AL ACCESO");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL ACCESO: "+ex.getMessage());
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
        String arg[] = {tip_id,tip_nombre,tip_descripcion,tip_longitud,tip_estado,tip_tipo_caracter};
        boolean response = false;


        try {
            String sql = " INSERT INTO tipo_documento(tip_id,tip_nombre,tip_descripcion,tip_longitud,tip_estado,tip_tipo_caracter)"+
                    " VALUES(?,?,?,?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("OK AL TIPO_DOCU");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL TIPO_DOCU: "+ex.getMessage());
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
        String arg[] = {per_id,num_documento,nombres,apellido_paterno,apellido_materno,telefono,celular,
                correo,fecha_nacimiento,edad,grado_instruccion,estado_civil,genero,id_tipo_doc};
        boolean response = false;

        try {
            String sql = " INSERT INTO persona(per_id,per_num_documento,per_nombres,per_appaterno,per_apmaterno,per_telefono," +
                    " per_celular,per_correo,per_fnacimiento,per_edad,per_gradinstruccion,per_estadocivil,per_genero,tip_id)"+
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("OK AL PERSONA");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL PERSONA: "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public boolean cargarUsuario(Context context, String usu_id, String usu_usuario, String usu_clave,
                                       String rol_id){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {usu_id,usu_usuario,usu_clave,rol_id};
        boolean response = false;


        try {
            String sql = " INSERT INTO usuario(usu_id,usu_usuario,usu_clave,rol_id)"+
                    " VALUES(?,?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("OK AL USUARIO");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL USUARIO: "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public boolean cargarGrupo(Context context, String gru_id, String usu_id_supervisor, String gru_numero,
                                 String gru_tot_encuestadores){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {gru_id,usu_id_supervisor,gru_numero,gru_tot_encuestadores};
        boolean response = false;


        try {
            String sql = " INSERT INTO grupo(gru_id,usu_idsupervisor,gru_numero,gru_tot_encuestadores)"+
                    " VALUES(?,?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("ok AL GRUPO");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL CARGAR GRUPO: "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public boolean cargarDispositivo(Context context, String dis_id, String dis_nombre, String dis_descripcion,
                               String dis_marca, String dis_modelo, String dis_serie){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {dis_id,dis_nombre,dis_descripcion,dis_marca,dis_modelo,dis_serie};
        boolean response = false;


        try {
            String sql = " INSERT INTO dispositivo(dis_id,dis_nombre,dis_descripcion,dis_marca,dis_modelo,dis_serie)"+
                    " VALUES(?,?,?,?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("OK AL DISPOSITIVO");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL DISPOSITIVO: "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }

    public boolean cargarUsuarioPersona(Context context, String usp_id, String per_id, String usu_id, String gru_id,
                                 String dis_id, String ubi_id, String cae_id, String usp_estado,
                                 String usp_desde_numEnc, String usp_hasta_numEnc, String usp_tot_encRealizadas, String usp_tot_encAsignadas){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {usp_id,per_id,usu_id,gru_id,dis_id,ubi_id,cae_id,
                usp_estado,usp_desde_numEnc,usp_hasta_numEnc,usp_tot_encRealizadas,usp_tot_encAsignadas};
        boolean response = false;

        try {
            String sql = " INSERT INTO usuario_persona(usp_id,per_id,usu_id,gru_id,dis_id,ubi_id," +
                    " cae_id,usp_estado,usp_desde_numenc,usp_hasta_numenc,usp_tot_encrealizadas,usp_tot_encasignadas)"+
                    " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

            dataBaseHelper.db.execSQL(sql,arg);

            System.out.println("OK AL USUARIO_PERONA");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL USUARIO_PERSONA: "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;

    }
}
