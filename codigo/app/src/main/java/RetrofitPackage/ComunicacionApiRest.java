package RetrofitPackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComunicacionApiRest {
  Context context;
    public ComunicacionApiRest(Context nContext){
        context=nContext;
    }

/*
    public void enviarPostRegitro(PostRegistroLogin postRegistroLogin,ResponseRegistro responseRegistro){
        RestAdapter restAdapter=new RestAdapter();
        responseRegistro=new ResponseRegistro();


    InterfazRestApi interfazRestApi = restAdapter.conectar();
    Call<ResponseRegistro> responseRegistroCall=interfazRestApi.registarUsuario(postRegistroLogin);
    responseRegistroCall.enqueue(new Callback<ResponseRegistro>() {
        @Override
        public void onResponse(Call<ResponseRegistro> call, Response<ResponseRegistro> response) {
           responseRegistro.setEnv(response.body().env);
           responseRegistro.setState(response.body().state);
           responseRegistro.setUser(response.body().user);
        }

        @Override
        public void onFailure(Call<ResponseRegistro> call, Throwable t) {

        }
    });


    }

public void enviarLogin(PostRegistroLogin postRegistroLogin) {
    RestAdapter restAdapter = new RestAdapter();



    InterfazRestApi interfazRestApi = restAdapter.conectar();
    Call<ResponseLogin> responseLoginCall = interfazRestApi.logearse(postRegistroLogin);
    responseLoginCall.enqueue(new Callback<ResponseLogin>() {
        @Override
        public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
            if(response.body().getSuccess()!=null && response.body().getSuccess().getState().equals("success"))
                Toast.makeText(context,"ok",Toast.LENGTH_LONG).show();
            else if( response.body().getError().getState().equals("error"))
                Toast.makeText(context,"error en login", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onFailure(Call<ResponseLogin> call, Throwable t) {

        }
    });
}
*/
}
