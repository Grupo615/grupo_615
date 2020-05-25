package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {
    TextView resultado;
    String tiempo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Intent i= getIntent();
        Bundle bundle=i.getExtras();
        tiempo=bundle.getString("resultado");
        resultado= findViewById(R.id.idResultado);
        resultado.setText(tiempo);

    }

    public void volverAJugar (View v){
        Intent intent=new Intent(Resultado.this,MenuPrincipal.class);
        startActivity(intent);
    }

}
