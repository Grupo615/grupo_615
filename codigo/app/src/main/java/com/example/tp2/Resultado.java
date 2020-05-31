package com.example.tp2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Resultado extends AppCompatActivity {
    private TextView resultado,fin,text2,mejor,mejorTiempo;
    private String tiempo,tiempoInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Intent i= getIntent();
        Bundle bundle=i.getExtras();
        tiempo = bundle.getString("resultado");
        resultado= findViewById(R.id.idResultado);
        fin = findViewById(R.id.fin);
        text2 = findViewById(R.id.text2);
        mejor = findViewById(R.id.mejor);
        mejorTiempo = findViewById(R.id.mejorTiempo);
        fin.setTextColor(Color.WHITE);
        text2.setTextColor(Color.WHITE);
        mejor.setTextColor(Color.WHITE);
        mejorTiempo.setTextColor(Color.WHITE);
        resultado.setTextColor(Color.WHITE);
        resultado.setText(tiempo);
    }

    @Override
    protected void onResume(){
        super.onResume();
        resultado.setText(tiempo);
        procesarPreferencias();
    }

    public void volverAJugar (View v){
        Intent intent=new Intent(Resultado.this,MenuPrincipal.class);
        startActivity(intent);
    }



    private void guardarPreferencias( SharedPreferences preferencias,String txt){
        //Guardamos el mejor tiempo del jugador
        preferencias = getSharedPreferences("mejorTiempo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("tiempo",txt);
        editor.commit();
    }

    private void procesarPreferencias(){

        SharedPreferences prefs3 =
                getSharedPreferences("mejorTiempo", Context.MODE_PRIVATE);

        String valor = prefs3.getString("tiempo","");
        String[] arraytiempo = tiempo.split(":");
        //Paso tiempo de minutos y segundos a milisegundos para comprar el tiempo final del juego con su menor tiempo
        int minutos = Integer.parseInt(arraytiempo[0]);
        int segundos = Integer.parseInt(arraytiempo[1]);
        int milisegundos = minutos*60000 + segundos * 1000;
        int t= milisegundos;

        int mt;
        if(valor.equals("")) {
            mt = 0;
        }else{
            String[] arraymejortiempo = valor.split(":");
            int mejorminutos = Integer.parseInt(arraymejortiempo[0]);
            int mejorsegundos = Integer.parseInt(arraymejortiempo[1]);
            int mejormilisegundos = mejorminutos*60000 + mejorsegundos * 1000;
            mt = mejormilisegundos;
        }
        Log.i("mejortiempo",String.valueOf(mt));
        Log.i("tiempo",String.valueOf(t));
        //Si el tiempo realizado es menor a el mejor tiempo del jugador lo guardamos
        if(mt>t || mt==0){
            guardarPreferencias(prefs3, tiempo);
        }else{
            guardarPreferencias(prefs3, valor);
        }
        mejor.setText(valor);
    }
}
