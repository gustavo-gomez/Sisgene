package com.instituto.cuanto.sisgene.dao;


import android.content.Context;
import android.database.Cursor;

import com.instituto.cuanto.sisgene.bean.Usuario;

/**
 * Created by Gustavo on 01/11/2015.
 */
public class TipoPreguntaDAO {

    public int obtenerCantidadUsuarios(Context context){

        int iCantidadUsuarios = 0;

        Cursor cursor = null;
        Usuario usu = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        try {
            cursor = dataBaseHelper.db.rawQuery("select count(*) from usuario",null);

            if (cursor.moveToFirst()) {
                usu = new Usuario();
                do {
                    iCantidadUsuarios = cursor.getInt(1);
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



}
