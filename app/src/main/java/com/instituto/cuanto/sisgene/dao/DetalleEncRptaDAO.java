package com.instituto.cuanto.sisgene.dao;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Jesus on 28/11/2015.
 */
public class DetalleEncRptaDAO {

    public DetalleEncRptaDAO(){}

    public String obtenerRpta(Context context) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        String valorRpta = "";


        try{
            String sql = " select der.deer_valor_respuesta " +
                    " from pregunta pre " +
                    " inner join det_enc_rpta der on pre.pre_id = der.pre_id ";

            cursor = dataBaseHelper.db.rawQuery(sql, null);

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

    public String obtenerRptaxId(Context context, String idPRegunta) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {idPRegunta};
        String valorRpta = "";


        try{
            String sql = " select der.deer_valor_respuesta " +
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
