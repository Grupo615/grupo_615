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


public class Pelota extends View {
    Paint paint;
    float ancho; // de canvas
    float largo;// de canvas
    float posX = 0, posY = 0; // posiciones iniciales
    float anteriorX = 0, anteriorY = 0;
    boolean primera = true;
    Bitmap bitmap;
    private int mWidth;
    private int mHeight;
    private Bitmap pelota;
    float espacioMovimiento = this.ancho / 135;

    public Pelota(Context context) {
        super(context);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pelota2);// crea un mapa de bit de la imagen
        paint = new Paint();
    }

    public void onSizeChanged(int a, int b, int c, int d) {
        mWidth = getWidth() / 7;
        mHeight = getHeight() / 11;
        pelota = Bitmap.createScaledBitmap(bitmap, mWidth, mHeight, true); // redimensiona el bitmap
    }

    protected void onDraw(Canvas canvas) {
        //canvas.drawColor(Color.BLUE);
        this.ancho = canvas.getWidth();
        espacioMovimiento = this.ancho / 135;
        this.largo = canvas.getHeight();
        //  canvas.drawCircle(posX,posY,radio,paint); //dibuja el circulo
        canvas.drawBitmap(pelota, posX, posY, null);
    }


    public void mover(float x, float y, List<Obstaculo> listaObs) {

        double margenSuperior = 1.5; // para ignorar los movimientos minimos
        double margenInferior = -1.5;
        // desplazamiento de pelota
        // para iniciarlizar las variables anteriorX y anteriorY
        if (primera == true) {
            primera = false;
            anteriorX = x;
            anteriorY = y;
            return;
        }
        List<Boolean> puede = sinObstaculo(listaObs); // le paso la lista de obstaculos y me dice para donde se puede mover
        //arriba,abajo,izquierda,derecha
// se mueve si cambio el x
        if (anteriorX != x) {
            if (puede.get(3) && x < margenInferior && posX + this.mWidth < this.ancho) //mueve hacia la derecha
                posX += espacioMovimiento;
            else if (puede.get(2) && x > margenSuperior && posX > 0) //mueve hacia la izquierda
                posX -= espacioMovimiento;
            anteriorX = x;
        }
        // se mueve si cambio Y
        Log.d("posX", String.valueOf(this.posX));
        Log.d("posY", String.valueOf(this.posY));
        Log.d("posX+mWidth", String.valueOf(posX + mWidth));
        Log.d("posY+mHeight", String.valueOf(posY + mHeight));
        Log.d("mHeigth", String.valueOf(this.mHeight));
        Log.d("mWidth", String.valueOf(this.mWidth));
        Log.d("largo", String.valueOf(this.largo));
        Log.d("ancho", String.valueOf(this.ancho));


        if (anteriorY != y) {
            if (puede.get(1) && y < margenInferior && posY > 0) //muevo hacia arriba
                posY -= espacioMovimiento;
            else if (puede.get(0) && y > margenSuperior && posY + this.mHeight < this.largo)// muevo hacia abajo
                posY += espacioMovimiento;
            anteriorY = y;
        }

    }

    public List<Boolean> sinObstaculo(List<Obstaculo> lista) {
        List<Boolean> listaPuede = new ArrayList<Boolean>();
        boolean abajo = true, arriba = true, izquierda = true, derecha = true;
        float posYAbajoPelota = this.posY + this.mHeight;
        float posYAbajoPelotaFinal = posYAbajoPelota + this.espacioMovimiento;


        for (Obstaculo obs : lista
        ) {
            //ABAJO

            boolean hayObsIzq = obs.existeObstaculo(this.posX + (this.mWidth / 4), this.posY + (float) (this.mHeight * 0.75));
            boolean hayObsDer = obs.existeObstaculo(this.posX + (float) (this.mWidth * 0.75), this.posY + (float) (this.mHeight * 0.75));
            boolean hayObsMedio = obs.existeObstaculo(this.posX + (this.mWidth / 2), this.posY + this.mHeight);

            if (abajo && (hayObsIzq || hayObsDer || hayObsMedio)) {
                abajo = false;
            }

            hayObsMedio = obs.existeObstaculo(this.posX + (this.mWidth / 2), this.posY);
            hayObsIzq = obs.existeObstaculo(this.posX + (this.mWidth / 4), this.posY+(this.mHeight/8));
            hayObsDer = obs.existeObstaculo(this.posX + (float)(this.mWidth*0.75), this.posY+(this.mHeight/8));
            if (arriba && (hayObsIzq || hayObsDer || hayObsMedio)) {
                arriba = false;
            }
            boolean hayObsArriba = obs.existeObstaculo(this.posX + (this.mWidth / 4), this.posY + (this.mHeight / 4));
            hayObsMedio = obs.existeObstaculo(this.posX, this.posY + (this.mHeight / 2));
            boolean hayObsAbajo = obs.existeObstaculo(this.posX + (this.mWidth / 4), this.posY + (this.mHeight * (float) 0.75));
            if (izquierda && (hayObsAbajo || hayObsArriba || hayObsMedio)) {
                izquierda = false;
            }
            hayObsMedio = obs.existeObstaculo(this.posX + this.mWidth, this.posY + (this.mHeight / 2));
            hayObsArriba = obs.existeObstaculo(this.posX + (float) (this.mWidth * 0.75), this.posY + (this.mHeight / 4));
            hayObsAbajo = obs.existeObstaculo(this.posX + (float) (this.mWidth * 0.75), this.posY + (this.mHeight * (float) 0.75));
            if (derecha && (hayObsAbajo || hayObsArriba || hayObsMedio)) {
                derecha = false;
            }
        }
        listaPuede.add(abajo);
        listaPuede.add(arriba);
        listaPuede.add(izquierda);
        listaPuede.add(derecha);
        Log.i("puedeAbajo", String.valueOf(listaPuede.get(0)));
        return listaPuede;
    }

}

