package com.example.alexgas.chupitosok;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by alexgas on 11/03/15.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.example.alexgas.chupitosok/databases/";
    private static final String DB_shoots = "chupitos.sqlite";
    private static final int DB_version =1;
    private final Context myContext;
    private SQLiteDatabase myDataBase;


    public DbHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(contexto, DB_shoots, null, version);
        this.myContext = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Esto es para inicializar la BD
        DbHelper db2= new DbHelper(myContext,DB_shoots,null,DB_version );

       try {
           Toast.makeText(myContext, "Buscando...", Toast.LENGTH_SHORT).show();
            db2.createDataBase();


           db2.openDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        db.execSQL(DataBaseManager.Create_Table);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            Toast.makeText(myContext, "Existe", Toast.LENGTH_SHORT).show();
            copyDataBase();

            // Si existe, no haemos nada!
        } else {
            // Llamando a este método se crea la base de datos vacía en la ruta
            // por defecto del sistema de nuestra aplicación por lo que
            // podremos sobreescribirla con nuestra base de datos.
            this.getReadableDatabase();
            Toast.makeText(myContext, "else", Toast.LENGTH_SHORT).show();

            try {


                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copiando database");
            }
        }
    }
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_shoots;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {
            // Base de datos no creada todavia
        }

        if (checkDB != null) {

            checkDB.close();
        }

        return checkDB != null ? true : false;

    }

    public void openDataBase()  {

        // Open the database
        Toast.makeText(myContext, "open", Toast.LENGTH_SHORT).show();
        String myPath = DB_PATH + DB_shoots;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        //Toast.makeText(myContext, "opened", Toast.LENGTH_LONG).show();


    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();
    }

    private void copyDataBase() throws IOException {
        Toast.makeText(myContext, "entrA", Toast.LENGTH_LONG).show();
        OutputStream databaseOutputStream = new FileOutputStream("" + DB_PATH + DB_shoots);
        InputStream databaseInputStream;

        byte[] buffer = new byte[1024];
        int length;

        databaseInputStream = myContext.getAssets().open("chupitos.sqlite");


        openDataBase();

        while ((length = databaseInputStream.read(buffer)) > 0) {
            databaseOutputStream.write(buffer);


        }

        databaseInputStream.close();
        //databaseOutputStream.flush();
      //  databaseOutputStream.close();
        Toast.makeText(myContext, "sale", Toast.LENGTH_SHORT).show();

    }

}
