package com.example.parcialcorte3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlHelper extends SQLiteOpenHelper {
    static String nombre="mapa";
    static int version=1;
    public SqlHelper (Context context) {
        super(context,nombre,null,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String marcador = "create table marcador (titulo text primary key, nombre text, descripcion text, latitud decimal(10,8), longitud decimal(10,8) ) ";
        db.execSQL(marcador);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
