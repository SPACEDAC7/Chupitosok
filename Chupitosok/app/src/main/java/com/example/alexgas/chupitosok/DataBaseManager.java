package com.example.alexgas.chupitosok;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alexgas on 11/03/15.
 */
public class DataBaseManager {

    public static final String nombreTabla= "Chupis";
    public static final String ColN_id = "_id";
    public static final String ColN_name = "nombre";
    public static final String ColN_ingredientes = "ingredientes";
    public static final String ColN_nivel= "nivel";

    public static final String Create_Table = "create table " +nombreTabla+ " ("
            + ColN_id + " integer primary key autoincrement,"
            +ColN_name + " text not null,"
            +ColN_ingredientes + " text not null,"
            +ColN_nivel + " text not null);";

    private DbHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {

        helper= new DbHelper(context,"chupitos.sqlite",null,1);
         db = helper.getWritableDatabase();

        //Con este .java lo que hacemos es crear una tabla nueva, no nos interesa
        // http://www.aprendeandroid.com/l5/sql4.htm
        //aunque luego nos interesará poder modificar la DB

    }
    private ContentValues generarContentValues (String name, String ingr, String nivel){
        ContentValues valores = new ContentValues();
        valores.put(ColN_name, name);
        valores.put(ColN_ingredientes, ingr);
        valores.put(ColN_nivel, nivel);
        return valores;


    }
    public  void insertar (String name, String ingr, String nivel){
      db.insert(nombreTabla,null,generarContentValues(name, ingr, nivel));
    }


    public void insertar2 (String name, String ingr, String nivel){

        db.execSQL("insert into "+ nombreTabla  +" values (null,'"+name+"','"+ingr+"','"+nivel+"')");
                //LECHUGA, Vodka y Pippermint, Fuerte ");

    }

    public void eliminar (String name){
        db.delete(nombreTabla,ColN_name+"=?",new String[]{name});
    }


    public void elimarVarios( String nom1, String nom2){
        db.delete(nombreTabla,ColN_name+"IN (?,?)",new String[]{nom1,nom2});

    }

    public void modifiShoot (String name,String ingr, String Nivel){
        db.update(nombreTabla,generarContentValues(name,ingr, Nivel),ColN_name+"=?",new String[]{name});

    }

    public Cursor cargarCursorChupis(){

        String[] columnas = new String[]{ColN_id,ColN_name,ColN_ingredientes,ColN_nivel};

        return db.query(nombreTabla,columnas,null,null,null,null,null);
    }

    public Cursor buscarChupito(String nombre){
        String[] columnas = new String[]{ColN_id,ColN_name,ColN_ingredientes,ColN_nivel};
        String[] buscamos= new String[]{nombre};

        if (buscamos[0]==""){// si buscador lista vacia
            return db.query(nombreTabla,columnas,null,null,null,null,null);}



        return db.query(nombreTabla,columnas,ColN_name+"=?",buscamos,null,null,null);
    }

}

