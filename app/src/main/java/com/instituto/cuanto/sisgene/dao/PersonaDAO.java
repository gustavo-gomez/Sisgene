package com.instituto.cuanto.sisgene.dao;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Jesus on 08/11/2015.
 */
public class PersonaDAO {

    public PersonaDAO(){}

    public boolean actualizarPersona(Context context, String nombre, String appaterno, String apmaterno,
                                     String numdoc, String telefono, String celular, String correo, int per_id){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String idPers = per_id+"";
        String arg[] = {nombre,appaterno,apmaterno,numdoc,telefono,celular,correo,idPers};
        boolean response = false;

        System.out.println("IDM PERSONA : "+per_id);

        try {
            String sql = " UPDATE persona " +
                    " SET per_nombres = ?, " +
                    " per_appaterno = ?, " +
                    " per_apmaterno = ?, " +
                    " per_num_documento = ?, " +
                    " per_telefono = ?, " +
                    " per_celular = ?, " +
                    " per_correo = ? " +
                    " WHERE per_id = ?";

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

    public boolean insertarPersona(Context context, String nombre, String appaterno, String apmaterno,
                                    String numdoc, String telefono, String celular, String correo){
        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {nombre,appaterno,apmaterno,numdoc,telefono,celular,correo};
        boolean response = false;

        try {
            String sql = " INSERT INTO persona (per_nombres,per_appaterno,per_apmaterno,per_num_documento,per_telefono,per_celular,per_correo)" +
                    " VALUES(?,?,?,?,?,?,?)";

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

    public int obtenerUltIdPersona(Context context){

        Cursor cursor   = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        int response = 0;

        try {

            cursor = dataBaseHelper.db.rawQuery(" select per.per_id" +
                    " from persona per" +
                    " order by per.per_id desc",null);

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
