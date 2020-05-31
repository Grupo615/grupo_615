package com.example.tp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;


public class Tablero extends View {
    private Bitmap bitmap;
    private Bitmap tablero;
    private int mHeigth;
    private int mWidth;
    private Paint paint;
    private boolean play = true;


    public Tablero(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.madera);
        paint = new Paint();

    }

    public void onSizeChanged(int a, int b, int c, int d) {
        mHeigth = getHeight();
        mWidth = getWidth();
        tablero = Bitmap.createScaledBitmap(bitmap, mWidth, mHeigth, true);

    }

    public void setPlay(boolean play) {
        this.play = play;
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(tablero, 0, 0, null);

    }
}
