package com.mailkaze.newfreedomloader;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;


public class Principal extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        TextView txtProgreso = (TextView)findViewById(R.id.txtProgreso);
        ProgressBar barra = (ProgressBar)findViewById(R.id.barraProgreso);

        Calendar inicio = Calendar.getInstance();
        inicio.set(2007, 9, 27, 0, 0, 0); //En la clase Calendar, el array de meses empieza con el cero como Enero, así que Octubre es el 9.

        Calendar fin = Calendar.getInstance();
        fin.set(2022, 9, 27, 0, 0, 0); //En la clase Calendar, el array de meses empieza con el cero como Enero, así que Octubre es el 9.

        Calendar hoy = Calendar.getInstance();

        final long tiempoTotal = fin.getTimeInMillis()-inicio.getTimeInMillis();
        final long tiempoTranscurrido = hoy.getTimeInMillis()-inicio.getTimeInMillis();
        final long porcentaje = tiempoTranscurrido*100/tiempoTotal;
        barra.setProgress((int)porcentaje);
        txtProgreso.setText(String.valueOf(porcentaje));

        Button btnEstadisticas = (Button)findViewById(R.id.btnEstadisticas);
        btnEstadisticas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Principal.this, Estadisticas.class);
                Bundle b = new Bundle();
                b.putLong("TRANSCURRIDO", tiempoTranscurrido);
                b.putLong("TOTAL", tiempoTotal);
                b.putLong("PORCENTAJE", porcentaje);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
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
}
