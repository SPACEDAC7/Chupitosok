package com.example.alexgas.chupitosok;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class IncluirChupito extends ActionBarActivity implements View.OnClickListener {
    private DataBaseManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incluir_chupito);


        Spinner spin = (Spinner) findViewById(R.id.spinnernivel);
        ArrayAdapter<CharSequence> adaptador;
        adaptador = ArrayAdapter.createFromResource(this,R.array.niveles,android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adaptador);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_incluir_chupito, menu);
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
        if (v.getId()== R.id.buttonincluir){

            final String nombreaincluir = ((TextView) findViewById(R.id.nombre_incluir)).getText().toString();
            final String ingredientesaincluir = ((TextView) findViewById(R.id.ingr_incluir)).getText().toString();
            final String nivelselec = ((Spinner)findViewById(R.id.spinnernivel)).getSelectedItem().toString();


            if (nombreaincluir!="" && ingredientesaincluir!="") {  //comprobar que nombre e ingredientes no estén vacios)
                manager.insertar(nombreaincluir, ingredientesaincluir, nivelselec);
                Toast.makeText(getApplicationContext(), "Incluido! ", Toast.LENGTH_SHORT).show();


                finishActivity(1);// terminar actividad

            }
            else
                Toast.makeText(getApplicationContext(), "Incluido! ", Toast.LENGTH_SHORT).show();


        }

    }

}