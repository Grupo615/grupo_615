package RetrofitPackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComunicacionApiRest {

    public ComunicacionApiRest(){}

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
public void registrarEvento(String descripcion,String type_events){
    PostEvento postEvento= new PostEvento();
    postEvento.setEnv("TEST");
    postEvento.setDescription(descripcion);
    postEvento.setState("ACTIVO");
    postEvento.setType_events(type_events);
    RestAdapter restAdapter = new RestAdapter();


    // responseRegistro=new ResponseRegistro();
    InterfazRestApi interfazRestApi = restAdapter.conectar();
    Call<ResponseEvento> responseEventoCall = interfazRestApi.registrarEvento(ConstantesRest.getToken(),postEvento);
    responseEventoCall.enqueue(new Callback<ResponseEvento>() {
        @Override
        public void onResponse(Call<ResponseEvento> call, Response<ResponseEvento> response) {
            if(response.body()==null){
                Gson gson = new Gson();
                Type type = new TypeToken<ErrorResponse>() {
                }.getType();
                ErrorResponse errorResponse = gson.fromJson(response.errorBody().charStream(), type);
                Log.i("mensajeError",errorResponse.getMsg());
              //  Toast.makeText(context, errorResponse.getMsg(), Toast.LENGTH_LONG).show();
            }
            else  if( response.body().getState().equals("success"))
              //  Toast.makeText(context,descripcion+ "exitoso",Toast.LENGTH_LONG).show();
                Log.i("mensajeSuccess",descripcion+ "exito");
            else{
                Log.i("mensajeFallo","fallo "+descripcion);
               // Toast.makeText(context,"fallo "+ descripcion ,Toast.LENGTH_LONG).show();
            }


        }

        @Override
        public void onFailure(Call<ResponseEvento> call, Throwable t) {

        }
    });


}
}
