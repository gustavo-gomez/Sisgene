package com.instituto.cuanto.sisgene.dao;

import android.database.Cursor;
import com.instituto.cuanto.sisgene.bean.Usuario;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Jesus on 27/10/2015.
 */
public class loginDAO {

    public loginDAO(){}

    public Usuario obtenerUsuario(Context context) {
        Cursor cursor = null;
        Usuario usu = null;
        String[] valores_recuperar = {"usu_usuario", "usu_clave"};
        DataBaseHelper dataBaseHelper;

        try {
            dataBaseHelper = new DataBaseHelper(context);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            cursor = DataBaseHelper.myDataBase.query("usuario", valores_recuperar, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                usu = new Usuario();
                do {
                    usu.setNombre(cursor.isNull(cursor.getColumnIndex("usu_usuario")) ? "" : cursor.getString(cursor.getColumnIndex("usu_usuario")));
                    usu.setApellido(cursor.isNull(cursor.getColumnIndex("usu_clave")) ? "" : cursor.getString(cursor.getColumnIndex("usu_clave")));
                } while (cursor.moveToNext()) ;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return usu;
    }
}
