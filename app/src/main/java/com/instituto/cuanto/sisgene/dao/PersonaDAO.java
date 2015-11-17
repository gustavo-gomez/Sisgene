package com.instituto.cuanto.sisgene.dao;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Jesus on 08/11/2015.
 */
public class PersonaDAO {

    public PersonaDAO() {
    }

    public boolean actualizarPersona(Context context, String nombre, String appaterno, String apmaterno,
                                     String numdoc, String telefono, String celular, String correo, int per_id) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String idPers = per_id + "";
        String arg[] = {nombre, appaterno, apmaterno, numdoc, telefono, celular, correo, idPers};
        boolean response = false;

        System.out.println("IDM PERSONA : " + per_id);

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

            dataBaseHelper.db.execSQL(sql, arg);

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
                                   String numdoc, String telefono, String celular, String correo) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {nombre, appaterno, apmaterno, numdoc, telefono, celular, correo};
        boolean response = false;

        try {
            String sql = " INSERT INTO persona (per_nombres,per_appaterno,per_apmaterno,per_num_documento,per_telefono,per_celular,per_correo)" +
                    " VALUES(?,?,?,?,?,?,?)";

            dataBaseHelper.db.execSQL(sql, arg);

            response = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;
    }

    public int obtenerUltIdPersona(Context context) {

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        int response = 0;

        try {

            cursor = dataBaseHelper.db.rawQuery(" select per.per_id" +
                    " from persona per" +
                    " order by per.per_id desc", null);

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

    public int obtenerIdPersonabyNombres(Context context, String nombre, String appaterno, String apmaterno) {

        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);

        int response = 0;
        String arg[] = {nombre, appaterno, apmaterno};

        String sql = " SELECT all.all_id " +
                " from allegado all " +
                " where all.all_nombres = ? " +
                " and all_appaterno = ? " +
                " and all_apmaterno = ?";

        try {
            cursor = dataBaseHelper.db.rawQuery(sql, arg);

            if (cursor.moveToFirst()) {
                response = cursor.getInt(1);
            }

            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return -1;

    }

    public boolean insertarAllegado(Context context, String nombre, String appaterno, String apmaterno) {
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String arg[] = {nombre, appaterno, apmaterno};
        boolean response = false;

        try {
            String sql = " INSERT INTO allegado (all_nombres,all_appaterno,all_apmaterno,)" +
                    " VALUES(?,?,?,?,?,?,?)";

            dataBaseHelper.db.execSQL(sql, arg);

            response = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return response;
    }


}
