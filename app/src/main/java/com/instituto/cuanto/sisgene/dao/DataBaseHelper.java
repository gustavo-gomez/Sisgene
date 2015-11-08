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


}