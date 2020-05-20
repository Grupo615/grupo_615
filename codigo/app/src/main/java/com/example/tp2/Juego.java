package com.example.tp2;

import android.content.Context;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class Juego extends AppCompatActivity implements SensorEventListener {
    Pelota pelota;
    SensorManager sensorManager;
    Sensor acelerometro;
    Sensor proximidad;
    Tablero tablero;
    boolean play = true;
    Obstaculo obstaculo1, obstaculo2, obstaculo3;
    List<Obstaculo> listaObs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.layout1); // obtiene el layout
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        proximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        listaObs = new ArrayList<Obstaculo>();
        pelota = new Pelota(this);
        tablero = new Tablero(this);
        definirObstaculos();
        layout1.addView(tablero);
        layout1.addView(pelota); // agrega la pelota al layout
        agregarObstaculos(layout1);

    }



    public void definirObstaculos() {
        obstaculo1 = new Obstaculo(this, 0, 1, 15,1,true);
        obstaculo2 = new Obstaculo(this, 500, 500,15,15, false);
        obstaculo3 = new Obstaculo(this, 20, 700, 15,15,false);
        listaObs.add(obstaculo1);
        listaObs.add(obstaculo2);
        listaObs.add(obstaculo3);

    }

    public void agregarObstaculos(RelativeLayout layout1) {
        for (Obstaculo obs : listaObs) {
            layout1.addView(obs);
        }
    }

    //cambio de valor en el sensor
    @Override
    public void onSensorChanged(SensorEvent event) {

        synchronized (this) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && play == true) {


                float x = (Math.round(event.values[0] * 10f) / 10f);
                float y = (Math.round(event.values[1] * 10f) / 10f); // redondeo a 1 decimales
                pelota.mover(x, y,listaObs);
                pelota.invalidate();


            }
        }
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {

            if (event.values[0] == 0) {
                if (play) {
                    play = false;
                    tablero.setPlay(play);
                } else {
                    play = true;
                    tablero.setPlay(play);
                }
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onResume() {
        super.onResume();
        // registrarse a los eventos del sensor
        sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, proximidad, SensorManager.SENSOR_DELAY_GAME);
    }

    protected void onPause() {
        super.onPause();
        // eliminar registro a evento de sensor
        sensorManager.unregisterListener(this);
    }
}