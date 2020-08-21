package com.example.pparcial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLite extends SQLiteOpenHelper {
    //Constantes campos tabla usuario
    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_APELLIDO="apellido";
    public static final String CAMPO_TELEFONO="telefono";
    public static final String CAMPO_CORREO="correo";
    public static final String CAMPO_REFERENCIA="referencia";
    public static final String CAMPO_IMAGEN="imagen";


    public static final String CREAR_TABLA_USUARIO="CREATE TABLE " +
            ""+TABLA_USUARIO+" ("+CAMPO_ID+" " +
            "INTEGER, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_APELLIDO+" TEXT, "+CAMPO_TELEFONO+" TEXT, "+CAMPO_CORREO+" TEXT, "+CAMPO_REFERENCIA+" TEXT, "+CAMPO_IMAGEN+" TEXT    )";

    //Constantes campos tabla Producto
    public static final String TABLA_PRODUCTO="producto";
    public static final String CAMPO_ID_PRODUCTO="id_producto";
    public static final String CAMPO_NOMBRE_PRODUCTO="nombre_producto";
    public static final String CAMPO_PRECIO="precio";
    public static final String CAMPO_IMEI="imei";


    public static final String CREAR_TABLA_PRODUCTO="CREATE TABLE " +
            ""+TABLA_PRODUCTO+" ("+CAMPO_ID_PRODUCTO+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE_PRODUCTO+" TEXT, "+CAMPO_PRECIO+" TEXT , "+CAMPO_IMEI+" TEXT )";



    public static final String TABLA_VENTA="venta";
    public static  String CAMPO_ID_VENTA ="idVenta";
    public static  String CAMPO_NOMBREPRO ="nombreProducto";
    public static  String CAMPO_NOMBRECLIE="nombreCliente";
    public static  String CAMPO_TOTALPAGAR="TotalPagar";
    public static  String CAMPO_TOTALDEBE="TotalDebe";
    public static  String CAMPO_TOTALABONO="TotalAbono";
    public static  String CAMPO_ANOTACIONES="anotaciones";
    public static  String CAMPO_HORA="Hora";
    public static  String CAMPO_FECHA="fecha";
    public static  String CAMPO_TITULO="titulo";
    public static  String CAMPO_MENSAJE="mensaje";

    public static final String CREAR_TABLA_VENTA="CREATE TABLE " +
            ""+TABLA_VENTA+" ("+CAMPO_ID_VENTA+ " INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_NOMBREPRO+" TEXT,"+CAMPO_NOMBRECLIE+" TEXT, "+CAMPO_TOTALPAGAR+" TEXT, "+CAMPO_TOTALDEBE+" TEXT, "+CAMPO_TOTALABONO+" TEXT, "+CAMPO_ANOTACIONES+" TEXT, "+CAMPO_HORA+" TEXT, "+CAMPO_FECHA+" TEXT,"+CAMPO_TITULO+" TEXT, "+CAMPO_MENSAJE+" TEXT  )";





    public ConexionSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_PRODUCTO);
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
        db.execSQL(Utilidades.CREAR_TABLA_VENTA);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_PRODUCTO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_VENTA);

        onCreate(db);
    }
}
