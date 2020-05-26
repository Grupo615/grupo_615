package com.example.tp2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.CollationElementIterator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Sensores  extends AppCompatActivity implements SensorEventListener {
    private List<TextView> valores_acelerometro = new ArrayList<TextView>();
    private List<TextView> valores_proximidad = new ArrayList<TextView>();
    TextView valores_acelerometro1,valores_acelerometro2,valores_acelerometro3,valores_acelerometro4,
            valores_proximidad1,valores_proximidad2,valores_proximidad3,valores_proximidad4;
    SharedPreferences Sacelerometro, Sproximidad;
    DecimalFormat dosdecimales = new DecimalFormat("###.###");
    SensorManager sensorManager;
    Sensor acelerometro;
    Sensor proximidad;
    int i=0,j=0,g=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        proximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        valores_acelerometro1=(TextView)findViewById(R.id.Vacelerometro);
        valores_acelerometro2=(TextView)findViewById(R.id.Vacelerometro2);
        valores_acelerometro3=(TextView)findViewById(R.id.Vacelerometro3);
        valores_acelerometro4=(TextView)findViewById(R.id.Vacelerometro4);

        valores_proximidad1=(TextView)findViewById(R.id.vproximidad);
        valores_proximidad2=(TextView)findViewById(R.id.vproximidad2);
        valores_proximidad3=(TextView)findViewById(R.id.vproximidad3);
        valores_proximidad4=(TextView)findViewById(R.id.vproximidad4);

        SharedPreferences prefs =
                getSharedPreferences("acelerometro", Context.MODE_PRIVATE);

        String valores1 = prefs.getString("valoresA0","no hay valor");
        String valores2 = prefs.getString("valoresA1","no hay valor");
        String valores3 = prefs.getString("valoresA2","no hay valor");

        valores_acelerometro2.setText(valores1);
        valores_acelerometro3.setText(valores2);
        valores_acelerometro4.setText(valores3);

        SharedPreferences prefs2 =
                getSharedPreferences("proximidad", Context.MODE_PRIVATE);

        String valores4 = prefs2.getString("valoresP0","no hay valor");
        String valores5 = prefs2.getString("valoresP1","no hay valor");
        String valores6 = prefs2.getString("valoresP","no hay valor");

        valores_proximidad2.setText(valores4);
        valores_proximidad3.setText(valores5);
        valores_proximidad4.setText(valores6);

    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, proximidad, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        // eliminar registro a evento de sensor
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY));
        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

    public void volver(View view){
        Intent intent=new Intent(Sensores.this,MenuPrincipal.class);
        startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {

            switch (event.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    String txt = "x: " + dosdecimales.format(event.values[0]) + " m/seg2 \n";
                    txt += "y: " + dosdecimales.format(event.values[1]) + " m/seg2 \n";
                    txt += "z: " + dosdecimales.format(event.values[2]) + " m/seg2 \n";
                    valores_acelerometro1.setText(txt);
                    i++;
                    if(i%2==0) {
                        guardarPreferencias(Sacelerometro, txt, true, g);
                        if (g == 3)
                            g = 0;
                        else
                            g++;
                    }
                    break;
                case Sensor.TYPE_PROXIMITY:
                    String txt2 = event.values[0] + "\n";
                    valores_proximidad1.setText(txt2);
                    guardarPreferencias(Sproximidad, txt2, false,j);
                    j++;
                    if(j==3)
                        j=0;
                    break;
            }


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void guardarPreferencias( SharedPreferences preferencias,String txt, boolean acelerometro, int n){
        if(acelerometro) {
            preferencias = getSharedPreferences("acelerometro", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            String v = "valoresA"+n;
            editor.putString(v, txt);
            editor.commit();
        } else{
            preferencias = getSharedPreferences("proximidad", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            String v = "valoresP"+n;
            editor.putString(v, txt);
            editor.commit();
        }
    }





}


