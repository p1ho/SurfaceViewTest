package com.example.student.surfaceviewtest;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by student on 2016/9/30.
 */

public class my_surfaceView extends SurfaceView {
    SurfaceHolder sh;
    my_callBack handler;
    public my_surfaceView(Context context) {
        super(context);
        handler = new my_callBack((MainActivity)context);
        sh = this.getHolder();
        sh.addCallback(handler);
    }

}
