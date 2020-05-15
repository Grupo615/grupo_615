package com.example.pruebaaa;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Paint;

import android.view.View;


public class Pelota extends View  {
    Paint paint;

    float posX=40,posY=40;
    float anteriorX=0,anteriorY=0;
    boolean primera=true;
    final String IZQUIERDA="IZQUIERDA",DERECHA="DERECHA",ARRIBA="ARRIBA",ABAJO="ABAJO";
    public Pelota(Context context){
        super(context);


        paint= new Paint();
    }
    protected  void onDraw(Canvas canvas){
        //canvas.drawColor(Color.BLUE);
        canvas.drawCircle(posX,posY,40,paint); //dibuja el circulo
    }
    public  void mover(float x, float y ) {

        // para iniciarlizar las variables anteriorX y anteriorY
            if(primera==true){
                primera=false;
                anteriorX=x;
                anteriorY=y;
                return ;
            }
// se mueve si cambio el x
        if (  anteriorX !=x) {
            if(x<0)
            posX += 10;
            else
                posX-=10;
            anteriorX = x;
        }
   // se mueve si cambio Y
        if ( anteriorY != y) {
            if(y<0)
            posY -= 10;
            else
                posY+=10;
            anteriorY = y;
        }

    }
    }
