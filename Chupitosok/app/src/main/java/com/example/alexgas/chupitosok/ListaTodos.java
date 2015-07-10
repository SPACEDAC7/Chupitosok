package com.example.alexgas.chupitosok;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class ListaTodos extends ActionBarActivity implements View.OnClickListener {

    private DataBaseManager manager;
    private Cursor cursor;
    private ListView listashoots;
    private SimpleCursorAdapter adaptador;
    private TextView tv;
    private ImageButton btbuscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_todos);
        manager = new DataBaseManager(this);

        listashoots = (ListView) findViewById(R.id.lista_todos);
        tv = (TextView) findViewById(R.id.buscador);
        btbuscar = (ImageButton) findViewById(R.id.boton_buscar);
        btbuscar.setOnClickListener(this);

        String[] from = new String[]{manager.ColN_name, manager.ColN_ingredientes, manager.ColN_nivel};
        int[] to = new int[]{android.R.id.text1, android.R.id.text2};


        cursor = manager.cargarCursorChupis();
        adaptador = new SimpleCursorAdapter(this,android.R.layout.two_line_list_item,cursor,from,to,0);
        listashoots.setAdapter(adaptador);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_todos, menu);
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

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.boton_buscar){
            new BuscarTask().execute();
        }
    }
    private class BuscarTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "Buscando...", Toast.LENGTH_SHORT).show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            cursor= manager.buscarChupito(tv.getText().toString());

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(),"A beber",Toast.LENGTH_SHORT).show();
            adaptador.changeCursor(cursor);

        }

    }
}
