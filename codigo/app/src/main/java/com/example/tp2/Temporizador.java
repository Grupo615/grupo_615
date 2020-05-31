package com.example.tp2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Temporizador extends View {
    private Paint paint;
    private int minutos = 0;
    private int segundos = 0;

    public Temporizador(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
    }

    public void onDraw(Canvas canvas) {
        canvas.drawText(minutos + ":" + segundos, canvas.getWidth() - 80, 50, paint);
    }

    public void setTiempo(int segundos) {
        this.minutos = segundos / 60;
        this.segundos = segundos % 60;
        invalidate();
    }

    public String getTiempo() {
        return minutos + ":" + segundos;
    }

}
