package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import RetrofitPackage.ErrorResponse;
import RetrofitPackage.InterfazRestApi;
import RetrofitPackage.PostRegistroLogin;
import RetrofitPackage.ResponseRegistro;
import RetrofitPackage.RestAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroUsuario extends AppCompatActivity {
    private Button bt1;
    private EditText nombre, apellido, contraseña, dni, email, comision, grupo;
    Intent intent2;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        nombre = (EditText) findViewById(R.id.etNom);
        apellido = (EditText) findViewById(R.id.eTApe);
        contraseña = (EditText) findViewById(R.id.eTContraseña);
        dni = (EditText) findViewById(R.id.eTDni);
        email = (EditText) findViewById(R.id.idEmail);
        comision = (EditText) findViewById(R.id.eTcomision);
        grupo = (EditText) findViewById(R.id.eTGrup);
        Intent intent = getIntent();
        context = this;

    }

    public void ComprobarConexionRegistro(View v) {
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo ni = manager.getActiveNetworkInfo();

        if (ni != null && ni.isConnected()) {
            registrar();
        } else
            Toast.makeText(getApplicationContext(), "No se pudo realizar el registro, " +
                    "su internet esta desconectada", Toast.LENGTH_SHORT).show();


    }

    public void registrar() {
        PostRegistroLogin postRegistroLogin = new PostRegistroLogin();
        if (comision.getText().toString().isEmpty()) {
            Toast.makeText(context, "debe ingresar el numero de comision", Toast.LENGTH_LONG).show();
            return;
        }
        if (dni.getText().toString().isEmpty()) {
            Toast.makeText(context, "debe ingresar el numero de dni", Toast.LENGTH_LONG).show();
            return;
        }
        if (grupo.getText().toString().isEmpty()) {
            Toast.makeText(context, "debe ingresar el numero de grupo", Toast.LENGTH_LONG).show();
            return;
        }
        int comisionInt = Integer.parseInt(comision.getText().toString());
        int dniInt = Integer.parseInt(dni.getText().toString());
        int grupoInt = Integer.parseInt(grupo.getText().toString());
        String emailString, passwordString, lastnameString, nameString;
        emailString = email.getText().toString();
        passwordString = contraseña.getText().toString();
        lastnameString = apellido.getText().toString();
        nameString = nombre.getText().toString();

        postRegistroLogin.setCommission(comisionInt);
        postRegistroLogin.setDni(dniInt);
        postRegistroLogin.setEmail(emailString);
        postRegistroLogin.setGroup(grupoInt);
        postRegistroLogin.setLastname(lastnameString);
        postRegistroLogin.setName(nameString);
        postRegistroLogin.setPassword(passwordString);

        RestAdapter restAdapter = new RestAdapter();


        // responseRegistro=new ResponseRegistro();
        InterfazRestApi interfazRestApi = restAdapter.conectar();
        Call<ResponseRegistro> responseRegistroCall = interfazRestApi.registarUsuario(postRegistroLogin);
        responseRegistroCall.enqueue(new Callback<ResponseRegistro>() {
            @Override
            public void onResponse(Call<ResponseRegistro> call, Response<ResponseRegistro> response) {

                if (response.body() == null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<ErrorResponse>() {
                    }.getType();
                    ErrorResponse errorResponse = gson.fromJson(response.errorBody().charStream(), type);
                    Toast.makeText(context, errorResponse.getMsg(), Toast.LENGTH_LONG).show();
                } else if (response.body().getState().equals("success")) {
                    Toast.makeText(context, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<ResponseRegistro> call, Throwable t) {
                Toast.makeText(context, "fallo la conexion", Toast.LENGTH_LONG).show();
            }
        });


    }

    public void irLogin(View v) {

        Intent intent = new Intent(RegistroUsuario.this, LoginUsuario.class);
        startActivity(intent);
    }
}