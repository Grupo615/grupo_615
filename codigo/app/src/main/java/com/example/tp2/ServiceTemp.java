package com.example.tp2;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import static java.lang.Thread.sleep;

public class ServiceTemp extends IntentService {
    private int tiempo = 0;
    private int tiempoEnPausa = 0;
    private int inicioPausa;
    private boolean noTermino = true;
    private int milisegundos;
    private int tiempoInicial;
    protected boolean play = true;
    private BroadCastTemp broadCastTemp;

    public ServiceTemp() {
        super("ServiceTemp");
       /* broadCastTemp= new BroadCastTemp();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.example.tp2.SETEAR_PLAY");
        registerReceiver(broadCastTemp,filter);

        */

    }
    public  void onDestroy(){
        super.onDestroy();
        unregisterReceiver(broadCastTemp);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        tiempoInicial = (int) System.currentTimeMillis();
        while (noTermino) {
            Intent i = new Intent();

            Log.i("estado", String.valueOf(play));
            if (!play)
                inicioPausa = (int) System.currentTimeMillis();
            while (!play) {
                int actual = (int) System.currentTimeMillis();
                tiempoEnPausa += actual - inicioPausa;
            }
            calcularTiempo();
            i.putExtra("cambioTiempo", tiempo);
            i.setAction("com.example.tp2.TIEMPO");
            sendBroadcast(i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    private void calcularTiempo() {
        milisegundos = (int) System.currentTimeMillis();
        this.tiempo = (milisegundos - tiempoInicial - tiempoEnPausa) / 1000;

    }

    class BroadCastTemp extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle= intent.getExtras();
            play= bundle.getBoolean("setearPlay");
        }
    }

}
