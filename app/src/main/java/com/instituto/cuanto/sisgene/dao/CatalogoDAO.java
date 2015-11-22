package com.instituto.cuanto.sisgene.dao;

import android.content.Context;
import android.database.Cursor;

import com.instituto.cuanto.sisgene.bean.CabeceraRespuesta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesus on 21/11/2015.
 */
public class CatalogoDAO {

    public CatalogoDAO(){}

    public List<String> obtenerTipoZona(Context context){
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<String> listaTipoZonas = new ArrayList<String>();

        try {

            cursor = dataBaseHelper.db.rawQuery(" select cat_nombre " +
                    " from catalogo" +
                    " where cat_grupo = 5 ", null);

            if (cursor.moveToFirst()) {
                System.out.println("ENTRO A TRAER INFO");
                do {
                    listaTipoZonas.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }

            return listaTipoZonas;
        } catch (Exception ex) {
            System.out.println("ERROR AL TRAER CATALOGO");
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return null;
    }


    public List<String> obtenerGradoFam(Context context){
        Cursor cursor = null;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        List<String> listaTipoZonas = new ArrayList<String>();

        try {

            cursor = dataBaseHelper.db.rawQuery(" select cat_nombre " +
                    " from catalogo" +
                    " where cat_grupo = 6 ", null);

            if (cursor.moveToFirst()) {
                System.out.println("ENTRO A TRAER INFO");
                do {
                    listaTipoZonas.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }

            return listaTipoZonas;
        } catch (Exception ex) {
            System.out.println("ERROR AL TRAER CATALOGO");
            ex.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
        }

        return null;
    }
}
