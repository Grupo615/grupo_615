package com.example.tp2;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;

public class ServiceSensor extends IntentService implements SensorEventListener {
    SharedPreferences Sacelerometro, Sproximidad;
    DecimalFormat dosdecimales = new DecimalFormat("###.###");
    SensorManager sensorManager;
    Sensor acelerometro;
    Sensor proximidad;

    public ServiceSensor() {
        super("ServiceSensor");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        /*synchronized (this) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                String txt = "x: " + dosdecimales.format(event.values[0]) + " m/seg2 \n";
                txt += "y: " + dosdecimales.format(event.values[1]) + " m/seg2 \n";
                txt += "z: " + dosdecimales.format(event.values[2]) + " m/seg2 \n";
                guardarPreferencias(Sacelerometro, txt, true);
            }

            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                String txt = event.values[0] + "\n";
                guardarPreferencias(Sproximidad, txt, false);
            }
        }*/
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    private void guardarPreferencias( SharedPreferences preferencias,String txt, boolean acelerometro){
      /*  if(acelerometro) {
            preferencias = getSharedPreferences("acelerometro", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString("valoresA", txt);
            editor.commit();
        } else{
            preferencias = getSharedPreferences("proximidad", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString("valoresP", txt);
            editor.commit();
        }*/
    }



}
