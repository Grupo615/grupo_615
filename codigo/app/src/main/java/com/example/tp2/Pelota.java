package com.example.tp2;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Paint;

import android.view.View;


public class Pelota extends View  {
    Paint paint;
    float ancho; // de canvas
    float largo;// de canvas
    float radio=40;
    float posX=40,posY=40; // posiciones iniciales
    float  anteriorX=0,anteriorY=0;
    boolean primera=true;

    public Pelota(Context context){
        super(context);


        paint= new Paint();
    }
    protected  void onDraw(Canvas canvas){
        //canvas.drawColor(Color.BLUE);
        this.ancho=canvas.getWidth();
        this.largo=canvas.getHeight();
        canvas.drawCircle(posX,posY,radio,paint); //dibuja el circulo
    }
    public  void mover(float x, float y ) {
        double margenSuperior=1.5; // para ignorar los movimientos minimos
        double margenInferior=-1.5;
        float espacioMovimiento=4; // desplazamiento de pelota
        // para iniciarlizar las variables anteriorX y anteriorY
        if(primera==true){
            primera=false;
            anteriorX=x;
            anteriorY=y;
            return ;
        }
// se mueve si cambio el x
        if (  anteriorX !=x) {
            if(x<margenInferior && posX+this.radio<this.ancho)
                posX += espacioMovimiento;
            else if (x>margenSuperior && posX-this.radio>0)
                posX-=espacioMovimiento;
            anteriorX = x;
        }
        // se mueve si cambio Y
        if ( anteriorY != y) {
            if(y<margenInferior && posY-this.radio>0)
                posY -= espacioMovimiento;
            else if(y>margenSuperior && posY+this.radio<this.largo)
                posY+=espacioMovimiento;
            anteriorY = y;
        }

    }
}
