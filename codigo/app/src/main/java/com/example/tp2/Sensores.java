package com.example.tp2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Sensores  extends AppCompatActivity {
    private List<TextView> valores_acelerometro = new ArrayList<TextView>();
    private List<TextView> valores_proximidad = new ArrayList<TextView>();
    TextView valores_acelerometro1 , valores_acelerometro2, valores_acelerometro3, valores_acelerometro4, valores_acelerometro5, valores_acelerometro6, valores_acelerometro7, valores_acelerometro8, valores_acelerometro9, valores_acelerometro10,valores_acelerometro11,valores_acelerometro12, valores_acelerometro13, valores_acelerometro14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensores);

        /*valores_acelerometro.add(valores_acelerometro1 = (TextView) findViewById(R.id.acelerometro1));
        valores_acelerometro.add(valores_acelerometro2=(TextView)findViewById(R.id.acelerometro1));
        valores_acelerometro.add(valores_acelerometro3=(TextView)findViewById(R.id.acelerometro1));
        valores_acelerometro.add(valores_acelerometro4=(TextView)findViewById(R.id.acelerometro1));
        valores_acelerometro.add(valores_acelerometro5=(TextView)findViewById(R.id.acelerometro1));
        valores_acelerometro.add(valores_acelerometro6=(TextView)findViewById(R.id.acelerometro1));
        valores_acelerometro.add(valores_acelerometro7=(TextView)findViewById(R.id.acelerometro1));
        valores_acelerometro.add(valores_acelerometro8=(TextView)findViewById(R.id.acelerometro1));
        valores_acelerometro.add(valores_acelerometro9=(TextView)findViewById(R.id.acelerometro1));
        valores_acelerometro.add(valores_acelerometro10=(TextView)findViewById(R.id.acelerometro1));
        valores_acelerometro.add(valores_acelerometro11=(TextView)findViewById(R.id.acelerometro1));
        valores_acelerometro.add(valores_acelerometro12=(TextView)findViewById(R.id.acelerometro1));
        valores_acelerometro.add(valores_acelerometro13=(TextView)findViewById(R.id.acelerometro1));
        valores_acelerometro.add(valores_acelerometro14=(TextView)findViewById(R.id.acelerometro1));
        

        SharedPreferences prefs =
                getSharedPreferences("acelerometro", Context.MODE_PRIVATE);

        for (TextView valor_acelerometro:valores_acelerometro) {
            valor_acelerometro.setText(prefs.getString("valores",null));
        }*/

    }

    public void volver(View view){
        Intent intent=new Intent(Sensores.this,MenuPrincipal.class);
        startActivity(intent);
    }

}


