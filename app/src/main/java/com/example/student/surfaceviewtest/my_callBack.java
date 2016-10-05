package com.example.student.surfaceviewtest;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * Created by student on 2016/9/30.
 */

public class my_callBack implements SurfaceHolder.Callback {

    MainActivity app;

    public my_callBack(MainActivity app) {this.app = app;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas canvas;
        canvas = holder.lockCanvas();
        canvas.drawBitmap(
                BitmapFactory.decodeResource(app.getResources(), R.drawable.heroin),
                200.0f,
                200.0f,
                new Paint()
        );
        holder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
