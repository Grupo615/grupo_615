package com.example.tp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Paint;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class Pelota extends View  {
    Paint paint;
    float ancho; // de canvas
    float largo;// de canvas
    float posX=40,posY=80; // posiciones iniciales
    float  anteriorX=0,anteriorY=0;
    boolean primera=true;
    Bitmap bitmap;
    private int mWidth;
    private int mHeight;
    private Bitmap pelota;
    float espacioMovimiento=this.ancho /135;

    public Pelota(Context context){
        super(context);

        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.pelota2);// crea un mapa de bit de la imagen
        paint= new Paint();
    }
    public void onSizeChanged(int  a ,int b , int c , int d){
        mWidth = getWidth() / 7;
        mHeight=getHeight()/11;
        pelota=Bitmap.createScaledBitmap(bitmap,mWidth,mHeight,true); // redimensiona el bitmap
    }
    protected  void onDraw(Canvas canvas){
        //canvas.drawColor(Color.BLUE);
        this.ancho=canvas.getWidth();
        espacioMovimiento=this.ancho /135;
        this.largo=canvas.getHeight();
      //  canvas.drawCircle(posX,posY,radio,paint); //dibuja el circulo
        canvas.drawBitmap(pelota,posX,posY,null);
    }


    public  void mover(float x, float y, List<Obstaculo> listaObs) {

        double margenSuperior=1.5; // para ignorar los movimientos minimos
        double margenInferior=-1.5;
      // desplazamiento de pelota
        // para iniciarlizar las variables anteriorX y anteriorY
        if(primera==true){
            primera=false;
            anteriorX=x;
            anteriorY=y;
            return ;
        }
// se mueve si cambio el x
        if (  anteriorX !=x) {
            if(x<margenInferior && posX+this.mWidth<this.ancho) //mueve hacia la derecha
                posX += espacioMovimiento;
            else if (x>margenSuperior && posX>0) //mueve hacia la izquierda
                posX-=espacioMovimiento;
            anteriorX = x;
        }
        // se mueve si cambio Y
       Log.d("posX",String.valueOf(this.posX));
        Log.d("posY",String.valueOf(this.posY));
        Log.d("posX+mWidth",String.valueOf(posX+mWidth));
        Log.d("posY+mHeight",String.valueOf(posY+mHeight));
        Log.d("mHeigth",String.valueOf(this.mHeight));
        Log.d("mWidth",String.valueOf(this.mWidth));
        Log.d("largo",String.valueOf(this.largo));
        Log.d("ancho",String.valueOf(this.ancho));
        List<Boolean> puede=sinObstaculo(listaObs); // le paso la lista de obstaculos y me dice para donde se puede mover
        if ( anteriorY != y) {
            if(y<margenInferior && posY>0) //muevo hacia arriba
                posY -= espacioMovimiento;
            else if( y>margenSuperior && posY+this.mHeight<this.largo)// muevo hacia abajo
                posY+=espacioMovimiento;
            anteriorY = y;
        }

    }
     public List<Boolean> sinObstaculo(List<Obstaculo> lista){
        List <Boolean> listaPuede= new ArrayList<Boolean>();
        boolean abajo=false,arriba=true,izquierda=true,derecha=true;
         float posYAbajoPelota= this.posY+this.mHeight;
         float posYAbajoPelotaFinal=posYAbajoPelota+this.espacioMovimiento;
         Log.i("posAbajoPelota", String.valueOf(posYAbajoPelota));
         Log.i("posAbajoPelotaFinal", String.valueOf(posYAbajoPelotaFinal));


         for (Obstaculo obs: lista
              ) {
                    //ABAJO
                            Log.i("posObstaculo",String.valueOf(obs.getPosY()));
                            Log.i("posObstaculoFinal",String.valueOf(obs.getPosY()+obs.getmHeigth()));
                           if(abajo==false && obs.getPosY()+obs.getmHeigth()<this.posY && obs.getPosY()>posYAbajoPelota){
                              abajo=true;
                           }

             }
         listaPuede.add(abajo);
         Log.i("puedeAbajo",String.valueOf(listaPuede.get(0)));
         return listaPuede;
         }

     }

