package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginUsuario extends AppCompatActivity {

    public void ComprobarConexionLogin(View v) {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = manager.getActiveNetworkInfo();
        if(ni != null && ni.isConnected())
            irMenu(v);
        else
            Toast.makeText(getApplicationContext(),"No se pudo realizar el login, "+
                    "su internet esta desconectada",Toast.LENGTH_SHORT).show();

    }

    public void ComprobarConexionRegistro(View v) {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = manager.getActiveNetworkInfo();
        if(ni != null && ni.isConnected())
            irRegistro(v);
        else
            Toast.makeText(getApplicationContext(),"No se pudo ingresar al registro, "+
                    "su internet esta desconectada",Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        Intent intent= getIntent();

    }


    public void irMenu(View v){
        Intent intent= new Intent(LoginUsuario.this,MenuPrincipal.class);
        startActivity(intent);
    }

    public void irRegistro(View view){
        Intent intent= new Intent(LoginUsuario.this,RegistroUsuario.class);
        startActivity(intent);
    }


}
