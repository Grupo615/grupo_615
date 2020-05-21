package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class RegistroUsuario extends AppCompatActivity {
    private Button bt1;
    private EditText nombre,apellido,contraseña,dni,email,comision,grupo;
    Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        Intent intent= getIntent();

    }

    public void ComprobarConexionRegistro(View v) {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = manager.getActiveNetworkInfo();
        if(ni != null && ni.isConnected())
            Toast.makeText(getApplicationContext(),"Se pudo realizar el registro, "+
                    "pero se perdieron los datos jjajajaja",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"No se pudo realizar el registro, "+
                    "su internet esta desconectada",Toast.LENGTH_SHORT).show();

    }


}
