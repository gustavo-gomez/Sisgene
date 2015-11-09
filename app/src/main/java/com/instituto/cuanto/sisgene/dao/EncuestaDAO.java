package com.instituto.cuanto.sisgene.dao;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Jesus on 08/11/2015.
 */
public class EncuestaDAO {

    public EncuestaDAO(){}

    public String obtenerCodigoEncuesta(Context context){

        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        String response="";

        try {

            cursor = dataBaseHelper.db.rawQuery(" select cae.cae_codigo" +
                    " from caratula_encuesta cae" +
                    " order by cae.cae_id  desc",null);

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
