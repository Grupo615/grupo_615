package com.example.tp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Tablero extends View {
    Bitmap bitmap, bitmap2;
    Bitmap tablero, pausa;
    private int mHeigth;
    private int mWidth;
    Paint paint;
    boolean play = true;


    public Tablero(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.madera);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.pausa);
        paint = new Paint();

    }

    public void onSizeChanged(int a, int b, int c, int d) {
        mHeigth = getHeight();
        mWidth = getWidth();
        tablero = Bitmap.createScaledBitmap(bitmap, mWidth, mHeigth, true);
        pausa = Bitmap.createScaledBitmap(bitmap2, mWidth / 4, mHeigth / 8, true);
    }

    public void setPlay(boolean play) {
        this.play = play;
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(tablero, 0, 0, null);
        if (!this.play)
            canvas.drawBitmap(pausa, getWidth() / 3, getHeight() / 3, paint);

    }
}
