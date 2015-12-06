package com.instituto.cuanto.sisgene.dao;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by Jesus on 28/11/2015.
 */
public class DetalleEncRptaDAO {

    public DetalleEncRptaDAO(){}

    public ArrayList<String> obtenerRpta(Context context) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        ArrayList<String> valorRpta = null;


        try{
            String sql = " select der.deer_id, der.deer_valor_respuesta, der.pre_id " +
                    " from pregunta pre " +
                    " inner join det_enc_rpta der on pre.pre_id = der.pre_id ";

            cursor = dataBaseHelper.db.rawQuery(sql, null);

            if (cursor.moveToFirst()){
                valorRpta.add(cursor.getString(0)); // id de la respuesta
                valorRpta.add(cursor.getString(1)); // valor de la respuesta
                valorRpta.add(cursor.getString(2)); // id de la pregunta asociada a la rpta
            }

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }finally{
            if (cursor != null)
                cursor.close();
        }
        return valorRpta;
    }

    public String obtenerRptaxId(Context context, String idPregunta) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {idPregunta};
        String valorRpta = "";

        try{
            String sql = " select der.deer_valor_respuesta, der.pre_id" +
                    " from pregunta pre " +
                    " inner join det_enc_rpta der on pre.pre_id = der.pre_id "+
                    " where pre.pre_id = ?";

            cursor = dataBaseHelper.db.rawQuery(sql, arg);

            if (cursor.moveToFirst()){
                valorRpta = cursor.getString(0);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }finally{
            if (cursor != null)
                cursor.close();
        }

        return valorRpta;

    }

}
