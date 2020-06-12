package com.example.parcialcorte3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Marcador {
    private String titulo,nombre, descripcion;
    private double latitud, longitud;

    public Marcador(String titulo, String nombre, String descripcion, double latitud, double longitud) {
        this.titulo = titulo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Marcador() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return titulo;
    }

    public SQLiteDatabase lectura (Context context){
        SqlHelper sqlHelper = new SqlHelper( context );
        return sqlHelper.getReadableDatabase();
    }
    public SQLiteDatabase escritura (Context context){
        SqlHelper sqlHelper = new SqlHelper( context );
        return sqlHelper.getWritableDatabase();
    }

    public ArrayAdapter<Object> obtenerMarcadores (Context context){
        try{
            ArrayAdapter<Object> adapter = new ArrayAdapter<>( context,android.R.layout.simple_list_item_1);
            ArrayList<Object> arrayList = new ArrayList<>();
            Cursor c = lectura(context).rawQuery( "select * from marcador ",null );
            while (c.moveToNext()){
                arrayList.add(new Marcador(c.getString( 0 ), c.getString( 1 ), c.getString( 2 ), c.getDouble( 3 ), c.getDouble( 4 )));
            }
            adapter.addAll( arrayList );
            return adapter;
        }catch (Exception e){
            return new ArrayAdapter<Object>( context, android.R.layout.simple_expandable_list_item_1 );
        }
    }


    public long ingresar (Context context) {
        ContentValues content = new ContentValues();
        content.put ("titulo", titulo);
        content.put("nombre",nombre);
        content.put("descripcion",descripcion);
        content.put("latitud",latitud);
        content.put("longitud",longitud);
        return escritura( context ).insert("marcador",null,content);
    }
}