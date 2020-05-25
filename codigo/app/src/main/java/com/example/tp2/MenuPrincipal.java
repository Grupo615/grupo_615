package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Intent intent = getIntent();
    }

    public void iniciarJuego(View view){
        Intent intent=new Intent(MenuPrincipal.this,Juego.class);
        startActivity(intent);
    }


    public void verActividadSensores(View view){
        Intent intent=new Intent(MenuPrincipal.this,Sensores.class);
        startActivity(intent);
    }

}
