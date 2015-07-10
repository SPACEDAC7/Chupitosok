package com.example.alexgas.chupitosok;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;


public class MainActivity extends ActionBarActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        findViewById(R.id.botonTodo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pulsamosInicio = new Intent(MainActivity.this, ListaTodos.class);
                startActivity(pulsamosInicio);
            }
        });

        findViewById(R.id.botonFavs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pulsamosFavs = new Intent (MainActivity.this,Listafavs.class);
                startActivity(pulsamosFavs);
            }
        });
        findViewById(R.id.botonMas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pulsamosIncluir = new Intent (MainActivity.this,IncluirChupito.class);
                startActivity(pulsamosIncluir);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.boton_recomendar) {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setType("text/*");
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.asunto_recomendar_amigo));
            intent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.cuerpo_recomendar_amigo));
           // intent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + getString(R.string.uri)));



            startActivity(Intent.createChooser(intent,getString(R.string.enviar_chooser)));
        }


        return super.onOptionsItemSelected(item);
    }

}
