package com.instituto.cuanto.sisgene.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import com.instituto.cuanto.sisgene.bean.Usuario;


/**

 /**
 * Created by luisrios on 9/5/15.
 */
public class DataBaseHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "sisgenebd.sqlite";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase db;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    public Usuario obtenerUsuario(Context context) {
        Cursor cursor = null;
        Usuario usu = null;
        String[] valores_recuperar = {"usu_usuario", "usu_clave"};
        DataBaseHelper dataBaseHelper;


        try {
            cursor = db.query("usuario", valores_recuperar, null, null, null, null, null);

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