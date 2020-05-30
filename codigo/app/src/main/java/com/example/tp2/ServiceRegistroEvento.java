package com.example.tp2;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

import RetrofitPackage.ComunicacionApiRest;


public class ServiceRegistroEvento extends IntentService {

    public ServiceRegistroEvento() {
        super("ServiceRegistroEvento");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Bundle bundle=intent.getExtras();
        String descripcion=bundle.getString("descripcion");
        String type_events=bundle.getString("type_events");
        ComunicacionApiRest comunicacionApiRest =new ComunicacionApiRest(this);
        comunicacionApiRest.registrarEvento(descripcion,type_events);
    }
}
