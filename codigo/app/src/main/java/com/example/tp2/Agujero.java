package com.example.tp2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Agujero extends View {
    Paint paint;
    float centroX;
    float centroY;
    float radio;
    public Agujero(Context context) {
        super(context);
        paint=new Paint();
        //paint.setColor(Color.BLACK);

    }
    public void onDraw(Canvas canvas){
        radio=getWidth()/14+5;
        centroX=2*radio;
        centroY=getHeight()-2*radio;
        canvas.drawCircle(2*centroX,centroY,radio,paint);
    }
}
