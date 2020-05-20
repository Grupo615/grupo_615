package com.example.tp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class Obstaculo extends View {
    private boolean horizontal;
    private Bitmap bitmap;
    private Bitmap obstaculo;
    private int mHeigth;
    private int mWidth;
    private int divAlto;
    private int divAncho;
    private int posX;
    private int posY;

    public Obstaculo(Context context, int posX, int posY, int divAlto,int divAncho,boolean horizontal) {
        super(context);
        this.posX = posX;
        this.posY = posY;
        this.horizontal = horizontal;
        this.divAlto=divAlto;
        this.divAncho=divAncho;

        if (horizontal)
            //PONER OBSTACULO HORIZONTAL
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.obstaculo);
        else
            //PONER OBSTACUL VERTICAL
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.obstaculo);
    }

    public void onSizeChanged(int a, int b, int c, int d) {
        mHeigth = getHeight() / divAlto;
        mWidth = getWidth() / divAncho;
        obstaculo = Bitmap.createScaledBitmap(bitmap, mWidth, mHeigth, true);

    }
    public int getPosY(){
        return this.posY;
    }
    public  int getPosX(){
        return  this.posX;
    }
    public int getmHeigth(){
        return this.mHeigth;
    }
    public int getmWidth(){
        return this.mWidth;
    }


    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(obstaculo, posX, posY, null);
    }
}
