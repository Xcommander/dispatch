package com.xcommander.dispatchtouchevent;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by xulinchao on 2017/6/15.
 */

public class MyLineaLayout extends LinearLayout {
    public MyLineaLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(MyApplication.getTAG(this.getClass()), "dispatchTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(MyApplication.getTAG(this.getClass()), "dispatchTouchEvent: ACTION_UP" );
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(MyApplication.getTAG(this.getClass()), "dispatchTouchEvent: ACTION_MOVE");
                break;
            default:
                break;


        }
        requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){

            case MotionEvent.ACTION_DOWN:
                Log.e(MyApplication.getTAG(this.getClass()), "onInterceptTouchEvent: ACTION_DOWN");
                return false;
            case MotionEvent.ACTION_UP:
                Log.e(MyApplication.getTAG(this.getClass()), "onInterceptTouchEvent: ACTION_UP" );
                return false;
            case MotionEvent.ACTION_MOVE:
                Log.e(MyApplication.getTAG(this.getClass()), "onInterceptTouchEvent: ACTION_MOVE");
                return false;
            default:
                return false;


        }
//        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(MyApplication.getTAG(this.getClass()), "onTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(MyApplication.getTAG(this.getClass()), "onTouchEvent: ACTION_UP" );
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(MyApplication.getTAG(this.getClass()), "onTouchEvent: ACTION_MOVE");
                break;
            default:
                Log.e(MyApplication.getTAG(this.getClass()), "onInterceptTouchEvent: default");
                break;


        }

        return super.onTouchEvent(event);
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        Log.e(MyApplication.getTAG(this.getClass()), "requestDisallowInterceptTouchEvent: ");
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

}
