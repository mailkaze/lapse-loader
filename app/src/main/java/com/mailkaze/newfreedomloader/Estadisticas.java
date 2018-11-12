package com.mailkaze.newfreedomloader;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static android.widget.SeekBar.OnSeekBarChangeListener;


public class Estadisticas extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        TextView txtTranscurrido = (TextView)findViewById(R.id.txtTranscurrido);
        TextView txtRestante = (TextView)findViewById(R.id.txtRestante);
        final TextView txtPorcentaje = (TextView)findViewById(R.id.txtPorcentaje);

        final Calendar fecha = Calendar.getInstance();
        String elMes = "";

        Bundle bundle = this.getIntent().getExtras();
        final long transcurrido = bundle.getLong("TRANSCURRIDO");
        final long total = bundle.getLong("TOTAL");
        final long porcentaje = bundle.getLong("PORCENTAJE");

        int anos = (int) (transcurrido/1000/60/60/24/365.25);
        long minutosSobrantes = (long) ((transcurrido/1000/60)-(anos*365.25*24*60));
        int meses = (int) (minutosSobrantes/60/24/30.4375);
        minutosSobrantes = (long) ((transcurrido/1000/60)-(anos*365.25*24*60)-(meses*30.4375*24*60));
        int dias = (int) (minutosSobrantes/60/24);
        minutosSobrantes = (long) ((transcurrido/1000/60)-(anos*365.25*24*60)-(meses*30.4375*24*60)-(dias*24*60));
        int horas = (int) (minutosSobrantes/60);
        minutosSobrantes = (long) ((transcurrido/1000/60)-(anos*365.25*24*60)-(meses*30.4375*24*60)-(dias*24*60)-(horas*60));
        txtTranscurrido.setText("- Han pasado " + anos + " años, " + meses + " meses, " + dias + " días, " + horas + " horas y " + minutosSobrantes + " minutos.\n");


        long falta = total - transcurrido;
        anos = (int) (falta/1000/60/60/24/365.25);
        minutosSobrantes = (long) ((falta/1000/60)-(anos*365.25*24*60));
        meses = (int) (minutosSobrantes/60/24/30.4375);
        minutosSobrantes = (long) ((falta/1000/60)-(anos*365.25*24*60)-(meses*30.4375*24*60));
        dias = (int) (minutosSobrantes/60/24);
        minutosSobrantes = (long) ((falta/1000/60)-(anos*365.25*24*60)-(meses*30.4375*24*60)-(dias*24*60));
        horas = (int) (minutosSobrantes/60);
        minutosSobrantes = (long) ((falta/1000/60)-(anos*365.25*24*60)-(meses*30.4375*24*60)-(dias*24*60)-(horas*60));
        txtRestante.setText("- Faltan " + anos + " años, " + meses + " meses, " + dias + " días, " + horas + " horas y " + minutosSobrantes + " minutos.\n");

        final SeekBar barra = (SeekBar)findViewById(R.id.seekBar);

        barra.setProgress((int)porcentaje);
        elMes=nombrarMes(fecha.get(Calendar.MONTH));
        txtPorcentaje.setText(String.valueOf(porcentaje)+"%, "+fecha.get(Calendar.DAY_OF_MONTH)+" de "+elMes+" de "+fecha.get(Calendar.YEAR));

        barra.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                Calendar f= Calendar.getInstance();
                Calendar inicio = Calendar.getInstance();
                inicio.set(2007, 9, 27, 0, 0, 0); //enero es el cero, así que octubre es el 9
                f.setTimeInMillis((progress*total/100)+inicio.getTimeInMillis());
                String elDia = String.valueOf(f.get(Calendar.DAY_OF_MONTH));
                String elMes = nombrarMes(f.get(Calendar.MONTH));
                String elAno = String.valueOf(f.get(Calendar.YEAR));
                txtPorcentaje.setText(String.valueOf(progress)+"%, "+elDia+" de "+elMes+" de "+elAno);

            }
        });

    }

    public String nombrarMes(int m){
        String mes = "";
        switch (m){
            case 0: mes="enero";
                break;
            case 1: mes="febrero";
                break;
            case 2: mes="marzo";
                break;
            case 3: mes="abril";
                break;
            case 4: mes="mayo";
                break;
            case 5: mes="junio";
                break;
            case 6: mes="julio";
                break;
            case 7: mes="agosto";
                break;
            case 8: mes="septiembre";
                break;
            case 9: mes="octubre";
                break;
            case 10: mes="noviembre";
                break;
            case 11: mes="diciembre";
                break;
        }
        return mes;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_estadisticas, menu);
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
