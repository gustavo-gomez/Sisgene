package com.instituto.cuanto.sisgene.dao;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Jesus on 21/11/2015.
 */
public class DireccionDAO {

    public DireccionDAO(){}

    public boolean insertarDireccion(Context context, String direccion) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {direccion};
        boolean response = false;

        try {
            String sql = " INSERT INTO direccion (dir_tipo_ubicacion)" +
                    " VALUES (?)";

            dataBaseHelper.db.execSQL(sql, arg);

            System.out.println("INSERTO CORRECTAMENTE LA DIRECCION");
            response = true;
        } catch (Exception ex) {
            System.out.println("ERROR AL GUARDAR DIRECCION : "+ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;
    }

    public int obtenerUltIdDireccion(Context context) {

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        int response = 0;

        try {

            cursor = dataBaseHelper.db.rawQuery(" select dir.dir_id" +
                    " from direccion dir" +
                    " order by dir.dir_id desc", null);

            if (cursor.moveToFirst()) {

                response = cursor.getInt(0);

            }

            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return 0;

    }
}
