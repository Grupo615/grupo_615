package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        Intent intent= getIntent();

    }
    public void irMenu(View v){
        Intent intent=new Intent(LoginUsuario.this,MenuPrincipal.class);
        startActivity(intent);
    }
}
