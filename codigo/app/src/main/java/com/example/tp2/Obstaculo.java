package com.example.tp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class Obstaculo extends View {
    private  boolean horizontal;
    private Bitmap bitmap;
    private Bitmap obstaculo;
    private int mHeigth;
    private int mWidth;
    private int  posX;
    private int posY;

    public Obstaculo (Context context,int posX, int posY , boolean horizontal){
        super(context);
        this.posX=posX;
        this.posY=posY;
        this.horizontal=horizontal;
        if(horizontal)
            //PONER OBSTACULO HORIZONTAL
           // bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.);
        else
            //PONER OBSTACUL VERTICAL
            bitmap= BitmapFactory.decodeResource(getResources(),);
    }
    public void onSizeChanged(int a ,int b,int c,int d){
        mHeigth=getHeight()/10;
        mWidth=getWidth()/10;
        obstaculo=Bitmap.createScaledBitmap(bitmap,mWidth,mHeigth,true);

    }
    public void onDraw(Canvas canvas){
        canvas.drawBitmap(obstaculo,posX,posY,null);
    }
}
