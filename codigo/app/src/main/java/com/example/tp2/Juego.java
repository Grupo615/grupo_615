package com.example.tp2;

import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import android.util.Log;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import RetrofitPackage.ComunicacionApiRest;


public class Juego extends AppCompatActivity implements SensorEventListener {
    Pelota pelota;
    SensorManager sensorManager;
    Sensor acelerometro;
    Sensor proximidad;
    Tablero tablero;
    Temporizador temp;
    Intent iService;
    private Agujero agujero;
    boolean play = true;
    private BroadcastReceiverTemp receiverTemp;
    Obstaculo obstaculo1, obstaculo2, obstaculo3, obstaculo4, obstaculo5, obstaculo6, obstaculo7, obstaculo8;
    List<Obstaculo> listaObs;
    SharedPreferences Sacelerometro, Sproximidad;
    DecimalFormat dosdecimales = new DecimalFormat("###.###");
    Intent iServiceEvento;
    private int cantMovimientos;
    private Pausa pausa;


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
        temp = new Temporizador(this);
        receiverTemp = new BroadcastReceiverTemp(temp);
        agujero = new Agujero(this);
        pausa=new Pausa(this);
        definirObstaculos();
        layout1.addView(tablero);
        layout1.addView(agujero);
        layout1.addView(pelota); // agrega la pelota al layout
        agregarObstaculos(layout1);
        layout1.addView(pausa);
        layout1.addView(temp);
        //Servicios
        //Temporizador
        iService = new Intent(this, ServiceTemp.class);
        startService(iService);
        //Registrar eventos (de sensores y tiempo final del juego)
        iServiceEvento = new Intent(this, ServiceRegistroEvento.class);
        startService(iServiceEvento);

    }


    public void definirObstaculos() {
        //crea los obstaculos
        obstaculo2 = new Obstaculo(this, 300, 200, 15, 3, true);
        obstaculo3 = new Obstaculo(this, 400, 300, 15, 3, true);
        obstaculo4 = new Obstaculo(this, 30, 400, 15, 3, true);
        obstaculo5 = new Obstaculo(this, 100, 500, 15, 3, true);
        obstaculo6 = new Obstaculo(this, 60, 600, 15, 3, true);
        obstaculo7 = new Obstaculo(this, 600, 700, 15, 3, true);
        obstaculo8 = new Obstaculo(this, 200, 800, 15, 3, true);

        //se agregan a la lista de obstaculos
        listaObs.add(obstaculo2);
        listaObs.add(obstaculo3);
        listaObs.add(obstaculo4);
        listaObs.add(obstaculo5);
        listaObs.add(obstaculo6);
        listaObs.add(obstaculo7);
        listaObs.add(obstaculo8);

    }

    public void agregarObstaculos(RelativeLayout layout1) {
        for (Obstaculo obs : listaObs) {
            layout1.addView(obs);
        }
    }

    //cambio de valor en el sensor
    @Override
    public void onSensorChanged(SensorEvent event) {

        //Acciones cuando hay eventos de los sensores
        synchronized (this) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && play == true) {
                float x = (Math.round(event.values[0] * 10f) / 10f);
                float y = (Math.round(event.values[1] * 10f) / 10f); // redondeo a 1 decimales
                boolean seMovio = pelota.mover(x, y, listaObs);
                pelota.invalidate();

                if (seMovio) {
                    cantMovimientos++;
                    verFinGame();
                    //Solo se registra el primer movimiento del sensor acelerometro
                    if (cantMovimientos == 1)
                        ServiceRegistroEvento.agregarEvento("primer movimiento de la pelota", "sensor acelerometro");
                }

            }
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] == 0) {
                    String descripcion = "";
                    if (play) {
                        play = false;
                        descripcion = "el juego se puso en pausa";
                        tablero.setPlay(play);
                        pausa.setVisible(true);
                    } else {
                        play = true;
                        descripcion = "el juego se reanuda";
                        tablero.setPlay(play);
                        pausa.setVisible(false);
                    }
                    // comunicacionApiRest.registrarEvento(descripcion,type_events);
                    ServiceTemp.setearPlay(play);
                    ServiceRegistroEvento.agregarEvento(descripcion, "sensor Proximidad");
                }

            }

        }
    }


    private void verFinGame() {
        boolean termina = agujero.isCovered(pelota.getCentroX(), pelota.getCentroY());//Consultar que la pelota este en el agujero
        Log.i("termin", String.valueOf(termina));
        if (termina) {
            mostrarResultado();
        }

    }

    public void mostrarResultado() {
        Intent intent = new Intent(this, Resultado.class);
        String tiempo = String.valueOf(this.temp.getTiempo());
        intent.putExtra("resultado", tiempo);//se pasa el tiempo del final del juego a la activity resultado
        ServiceRegistroEvento.agregarEvento("fin de juego ", "finalizacion");
        ServiceTemp.terminar();//se termina el temporizador
        ServiceRegistroEvento.detener();
        startActivity(intent);
        finish();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.tp2.TIEMPO");
        registerReceiver(receiverTemp, filter);
        // registrarse a los eventos del sensor
        sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, proximidad, SensorManager.SENSOR_DELAY_GAME);
    }

    protected void onPause() {
        super.onPause();
        // eliminar registro a evento de sensor
        sensorManager.unregisterListener(this);
        unregisterReceiver(receiverTemp);
    }

    class BroadcastReceiverTemp extends BroadcastReceiver {
        Temporizador temporizador;

        public BroadcastReceiverTemp(Temporizador temporizador) {
            this.temporizador = temporizador;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            int tiempo = bundle.getInt("cambioTiempo");
            temporizador.setTiempo(tiempo);
        }
    }
}