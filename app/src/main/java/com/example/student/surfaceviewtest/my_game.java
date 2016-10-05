package com.example.student.surfaceviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.WindowManager;

/**
 * Created by student on 2016/9/30.
 */

public class my_game extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    MainActivity app;
    SurfaceHolder holder;
    int display_width, display_height;
    //TODO Constructor
    public my_game(Context context) {
        super(context);
        app = (MainActivity)context;
        holder = this.getHolder();
        holder.addCallback(this);
        Display display = app.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        display_width = size.x;
        display_height = size.y;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        new Thread(this).start();

    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void run() {
        Rect bg_src_size;
        Rect[] bg_ptr = new Rect[4];

        int[][] bg_x = new int[2][];
        int[][] bg_y = new int[2][];
        int bg_right_speed = 1;
        int bg_down_speed = -1;
        if (bg_right_speed > 0) {
            bg_x[0] = new int[]{0, -display_width,0, -display_width};
            bg_x[1] = new int[]{display_width, 0,display_width, 0};
        } else {
            bg_x[0] = new int[]{0, display_width,0, display_width};
            bg_x[1] = new int[]{display_width, display_width * 2,display_width, display_width * 2};
        }
        if (bg_down_speed > 0) {
            bg_y[0] = new int[]{0, 0, -display_height, -display_height };
            bg_y[1] = new int[]{display_height, display_height, 0, 0};
        } else{
            bg_y[0] = new int[]{0, 0, display_height, display_height };
            bg_y[1] = new int[]{display_height, display_height, display_height * 2, display_height * 2};
        }

        while(true) {
            Canvas canvas = holder.lockCanvas();
            Bitmap bg_src = BitmapFactory.decodeResource(app.getResources(), R.drawable.bg);
            bg_src_size = new Rect(0, 0, bg_src.getWidth(), bg_src.getHeight());
            for (int i = 0; i < bg_ptr.length; i++){
                bg_ptr[i] = new Rect(bg_x[0][i], bg_y[0][i], bg_x[1][i], bg_y[1][i]);
                if (canvas != null) {
                    canvas.drawBitmap(bg_src, bg_src_size, bg_ptr[i], new Paint());
                }
            }
            holder.unlockCanvasAndPost(canvas);
            for (int i = 0; i < bg_x.length; i++) {
                for (int j = 0; j < bg_x[i].length; j++) {
                    bg_x[i][j] += bg_right_speed;
                    bg_y[i][j] += bg_down_speed;
                    if (bg_right_speed > 0) {
                        if (bg_x[0][j] >= display_width) {
                            bg_x[0][j] = bg_x[0][j] - display_width * 2;
                            bg_x[1][j] = bg_x[1][j] - display_width * 2;
                        }
                    } else {
                        if (bg_x[0][j] <= -display_width) {
                            bg_x[0][j] = bg_x[0][j] + display_width * 2;
                            bg_x[1][j] = bg_x[1][j] + display_width * 2;
                        }
                    }
                    if (bg_down_speed > 0) {
                        if (bg_y[0][j] >= display_height) {
                            bg_y[0][j] = bg_y[0][j] - display_height * 2;
                            bg_y[1][j] = bg_y[1][j] - display_height * 2;
                        }
                    } else {
                        if (bg_y[0][j] <= -display_height){
                            bg_y[0][j] = bg_y[0][j] + display_height * 2;
                            bg_y[1][j] = bg_y[1][j] + display_height * 2;
                        }
                    }
                }
            }
            /*
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */

        }
    }
}
