package com.xcommander.dispatchtouchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 *
 * Created by xulinchao on 2017/6/14.
 *
 *
 DOWN时：
 a、首先设置标志为PREPRESSED，设置mHasPerformedLongPress=false ;然后发出一个115ms后的mPendingCheckForTap；
 b、如果115ms内没有触发UP，则将标志置为PRESSED，清除PREPRESSED标志，同时发出一个延时为500-115ms的，检测长按任务消息；
 c、如果500ms内（从DOWN触发开始算），则会触发LongClickListener:
 此时如果LongClickListener不为null，则会执行回调，同时如果LongClickListener.onClick返回true，才把mHasPerformedLongPress设置为true;否则mHasPerformedLongPress依然为false;
 MOVE时：
 主要就是检测用户是否划出控件，如果划出了：
 115ms内，直接移除mPendingCheckForTap；
 115ms后，则将标志中的PRESSED去除，同时移除长按的检查：removeLongPressCallback();
 UP时：
 a、如果115ms内，触发UP，此时标志为PREPRESSED，则执行UnsetPressedState，setPressed(false);会把setPress转发下去，可以在View中复写dispatchSetPressed方法接收；
 b、如果是115ms-500ms间，即长按还未发生，则首先移除长按检测，执行onClick回调；
 c、如果是500ms以后，那么有两种情况：
 i.设置了onLongClickListener，且onLongClickListener.onClick返回true，则点击事件OnClick事件无法触发；
 ii.没有设置onLongClickListener或者onLongClickListener.onClick返回false，则点击事件OnClick事件依然可以触发；
 d、最后执行mUnsetPressedState.run()，将setPressed传递下去，然后将PRESSED标识去除；
 * view的时间分发是
 * dispatchTouchEvent->onTouchListener->onTouchEvent
 * 在dispatchTouchEvent中会检查OnSetTouchListener，如果OnTouchListener为不为null，且返回为true，则表示事件被
 * 消费了（onTouchEvent不会被执行），否则执行onTouchEvent
 */

public class MyButton extends android.support.v7.widget.AppCompatButton {
    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * view 的触摸事件
     * **/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(MyApplication.getTAG(this.getClass()), "onTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(MyApplication.getTAG(this.getClass()), "onTouchEvent: ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(MyApplication.getTAG(this.getClass()), "onTouchEvent: ACTION_MOVE");
                break;
            default:
                Log.e(MyApplication.getTAG(this.getClass()), "onTouchEvent: default");
                break;
        }
//        return false;
        return super.onTouchEvent(event);
    }

    /**
     * view的分发
     * **/


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(MyApplication.getTAG(this.getClass()), "dispatchTouchEvent: ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(MyApplication.getTAG(this.getClass()), "dispatchTouchEvent: ACTION_UP");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(MyApplication.getTAG(this.getClass()), "dispatchTouchEvent: ACTION_MOVE");
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
