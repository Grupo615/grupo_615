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
    private float posX;
    private float posY;

    public Obstaculo(Context context, float posX, float posY, int divAlto, int divAncho, boolean horizontal) {
        super(context);
        this.posX = posX;
        this.posY = posY;
        this.horizontal = horizontal;
        this.divAlto = divAlto;
        this.divAncho = divAncho;

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

    public float getPosY() {
        return this.posY;
    }

    public float getPosX() {
        return this.posX;
    }

    public int getmHeigth() {
        return this.mHeigth;
    }

    public int getmWidth() {
        return this.mWidth;
    }


    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(obstaculo, posX, posY, null);
    }

    public boolean existeObstaculo(float posX, float posY) {
        if (posY >= this.posY && posY <= this.posY + this.mHeigth && posX >= this.posX && posX <= this.posX + mWidth)
            return true;
        return false;

    }
}
