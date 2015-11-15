package com.instituto.cuanto.sisgene.dao;

import android.database.Cursor;
import com.instituto.cuanto.sisgene.bean.Usuarios;
import android.content.Context;
/**
 * Created by Jesus on 01/11/2015.
 */
public class UsuarioDAO {

    public UsuarioDAO(){}

    public int obtenerCantidadUsuarios(Context context){

        int iCantidadUsuarios = 0;

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        try {
            cursor = dataBaseHelper.db.rawQuery("select count(*) from usuario",null);

            if (cursor.moveToFirst()) {
                do {
                    iCantidadUsuarios = cursor.getInt(0);
                } while (cursor.moveToNext()) ;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return iCantidadUsuarios;

    }

    public Usuarios obtenerUsuario(Context context, String user, String rol){
        Usuarios usuario = null;
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        String arg[] = {user,rol};

        try {

            cursor = dataBaseHelper.db.rawQuery(" select per.per_nombres, per.per_appaterno, per.per_apmaterno, ro.rol_descripcion, usu.usu_clave" +
                    " from usuario usu" +
                    " inner join usuario_persona up on up.usu_id = usu.usu_id" +
                    " inner join persona per on up.per_id = per.per_id" +
                    " inner join rol ro on usu.rol_id = ro.rol_id" +
                    " where usu.usu_usuario = ? " +
                    " and ro.rol_descripcion = ? ",arg);

            if (cursor.moveToFirst()) {
                do {
                    usuario = new Usuarios();
                    usuario.setNombre(cursor.getString(0));
                    usuario.setAp_paterno(cursor.getString(1));
                    usuario.setAp_materno(cursor.getString(2));
                    usuario.setRol(cursor.getString(3));
                    usuario.setClave(cursor.getString(4));
                } while (cursor.moveToNext()) ;
            }

            return usuario;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return null;

    }

    public String obtenerGrupoPorUsuario(Context context, String user){

        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        String arg[] = {user};
        String response="";

        try {

            cursor = dataBaseHelper.db.rawQuery(" select gr.gru_numero" +
                    " from usuario usu" +
                    " inner join grupo gr on usu.usu_id = gr.usu_idsupervisor" +
                    " where usu.usu_usuario = ?",arg);

            if (cursor.moveToFirst()) {

                response = cursor.getString(0);

            }

            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return null;

    }

}
