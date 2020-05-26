package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import RetrofitPackage.ComunicacionApiRest;
import RetrofitPackage.InterfazRestApi;
import RetrofitPackage.PostRegistroLogin;
import RetrofitPackage.ResponseLogin;
import RetrofitPackage.RestAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUsuario extends AppCompatActivity {
private EditText email,contraseña;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        Intent intent= getIntent();
        context=this;
        email=(EditText)findViewById(R.id.idEmail);
        contraseña=(EditText) findViewById(R.id.idcontraseña);

    }

    public void ComprobarConexionLogin(View v) {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = manager.getActiveNetworkInfo();
        if(ni != null && ni.isConnected()) {
            logearse();
        }
        else
            Toast.makeText(getApplicationContext(),"No se pudo realizar el login, "+
                    "su internet esta desconectada",Toast.LENGTH_SHORT).show();

    }

    public void  logearse(){
     ///   ComunicacionApiRest comunicacionApiRest = new ComunicacionApiRest(this);
        PostRegistroLogin postRegistroLogin= new PostRegistroLogin();
        postRegistroLogin.setEmail(email.getText().toString());
        postRegistroLogin.setPassword(contraseña.getText().toString());
      //  comunicacionApiRest.enviarLogin(postRegistroLogin);

        RestAdapter restAdapter = new RestAdapter();
        InterfazRestApi interfazRestApi = restAdapter.conectar();
        Call<ResponseLogin> responseLoginCall = interfazRestApi.logearse(postRegistroLogin);
        responseLoginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if(response.body().getState().equals("success"))
                    Toast.makeText(context,"ok",Toast.LENGTH_LONG).show();
                else if( response.body().getState().equals("error"))
                    Toast.makeText(context,"error en login", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

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




    public void irMenu(View v){
        Intent intent= new Intent(LoginUsuario.this,MenuPrincipal.class);
        startActivity(intent);
    }

    public void irRegistro(View view){
        Intent intent= new Intent(LoginUsuario.this,RegistroUsuario.class);
        startActivity(intent);
        finish();
    }


}
