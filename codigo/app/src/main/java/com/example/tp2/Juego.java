package com.example.tp2;
import android.content.Context;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;


public class Juego extends AppCompatActivity implements SensorEventListener {
    Pelota pelota;
    SensorManager sensorManager;
    Sensor acelerometro;
    Tablero tablero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.layout1); // obtiene el layout
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        pelota = new Pelota(this);
        tablero= new Tablero(this);
        layout1.addView(tablero);
        layout1.addView(pelota); // agrega la pelota al layout


    }

    //cambio de valor en el sensor
    @Override
    public void onSensorChanged(SensorEvent event) {

        synchronized (this) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {


                float x = (Math.round(event.values[0] * 10f) / 10f);
                float y = (Math.round(event.values[1] * 10f) / 10f); // redondeo a 1 decimales
                pelota.mover(x, y);
                pelota.invalidate();


            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    protected  void onResume(){
        super.onResume();
        // registrarse a los eventos del sensor
        sensorManager.registerListener(this,acelerometro,SensorManager.SENSOR_DELAY_GAME);
    }
    protected  void onPause(){
        super.onPause();
        // eliminar registro a evento de sensor
        sensorManager.unregisterListener(this);
    }
}