package com.example.tp2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import RetrofitPackage.ComunicacionApiRest;
import RetrofitPackage.PostRegistroLogin;

public class LoginUsuario extends AppCompatActivity {
    private EditText email, contraseña;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        Intent intent = getIntent();
        context = this;
        email = (EditText) findViewById(R.id.idEmail);
        contraseña = (EditText) findViewById(R.id.idcontraseña);

    }

    public void ComprobarConexionLogin(View v) {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = manager.getActiveNetworkInfo();
        //Antes de logearse consultar estado de internet
        if (ni != null && ni.isConnected()) {
            logearse();
        } else
            Toast.makeText(getApplicationContext(), "No se pudo realizar el login, " +
                    "su internet esta desconectada", Toast.LENGTH_SHORT).show();

    }

    public void logearse() {
        PostRegistroLogin postRegistroLogin = new PostRegistroLogin();
        postRegistroLogin.setEmail(email.getText().toString());
        postRegistroLogin.setPassword(contraseña.getText().toString());

        ComunicacionApiRest comunicacionApiRest = new ComunicacionApiRest(this);
        comunicacionApiRest.enviarLogin(postRegistroLogin,LoginUsuario.this);

    }

    public void ComprobarConexionRegistro(View v) {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = manager.getActiveNetworkInfo();
        //Antes de pasar a la activity de registro para registrarse , comprobar si hay internet
        if (ni != null && ni.isConnected())
            irRegistro(v);
        else
            Toast.makeText(getApplicationContext(), "No se pudo ingresar al registro, " +
                    "su internet esta desconectada", Toast.LENGTH_SHORT).show();

    }


    public void irMenu() {
        Intent intent = new Intent(LoginUsuario.this, MenuPrincipal.class);
        startActivity(intent);
    }

    public void irRegistro(View view) {
        Intent intent = new Intent(LoginUsuario.this, RegistroUsuario.class);
        startActivity(intent);
        finish();
    }


}
