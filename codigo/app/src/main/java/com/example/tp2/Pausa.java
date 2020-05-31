package com.example.tp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class Pausa extends View {
    private Paint paint;
    private boolean visible=false;
    Bitmap bitmap;
    Bitmap pausa;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        invalidate();
    }


    public Pausa(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pausa);
        paint = new Paint();
    }

    public void onSizeChanged(int a, int b, int c, int d) {
        int mHeigth = getHeight();
        int mWidth = getWidth();
        pausa = Bitmap.createScaledBitmap(bitmap, mWidth / 4, mHeigth / 8, true);
    }

    public void onDraw(Canvas canvas) {

        if (this.visible)
            canvas.drawBitmap(pausa, getWidth() / 3, getHeight() / 3, paint);

    }
}
