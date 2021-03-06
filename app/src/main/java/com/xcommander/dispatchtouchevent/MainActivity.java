package com.xcommander.dispatchtouchevent;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @see  MotionEvent.ACTION_DOWN
 *有自己的一整套流程
 * 但是 {@link MotionEvent.ACTION_MOVE } and {@link MotionEvent.ACTION_UP}这两个是类似于绑定了ACTION_DOWN，
 * 当ACTION_DOWN在哪个控件中消费了，那么这两个也在只会传到这个控件中。
 * 消费分为两种，一种是在dispatchTouchEvent消费，那么这两个就在这里停止传递。
 * 一种是在onTouchEvent中消费，那么这两个将会在这里面处理并且结束传递
 *
 *
 *
 * ACTION_DOWN事件的检查首先是根据延迟方法是否执行，执行的话则被认为是按下DOWN事件，然后在延迟长按执行是否执行，如果执行
 * 则被认为是长按事件，然后处理长按结果。
 *
 *
 * 在事件分发的过程中，return false or true是指第一次返回的值，而不是后面返回的
 *
 * * **/

public class MainActivity extends AppCompatActivity {
    private MyButton myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton=(MyButton)findViewById(R.id.MyButton);
        myButton.setOnTouchListener((View v, MotionEvent event)->{
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    Log.e(MyApplication.getTAG(MyButton.class), "onTouch : ACTION_DOWN");
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.e(MyApplication.getTAG(MyButton.class), "onTouch : ACTION_MOVE");
                    break;
                case MotionEvent.ACTION_UP:
                    Log.e(MyApplication.getTAG(MyButton.class), "onTouch : ACTION_UP");
                    break;
                default:
                    break;
            }
            return false;
        });
        myButton.setOnClickListener((v) ->{
            Box<Integer> box=new Box<Integer>(11);
            Box<Number> box1=new Box<Number>(33.0);
            Log.e("xulinchao", "box = "+box.getClass());
            Log.e("xulinchao", "box1 = "+box1.getClass());
            Ge(box);
            Ge(box1);
            Class<?> cls=getClassObject();
            Field[] fields=cls.getDeclaredFields();
            Log.e(Cat.TAG, "------------------获取声明的变量------------------");
            for(Field f:fields){
                Log.e(Cat.TAG, "f =  "+f);
            }

            Log.e(Cat.TAG, "------------------获取声明的方法------------------");
            Method[] methods=cls.getDeclaredMethods();
            for(Method m:methods){
                Log.e(Cat.TAG, "m =  "+m);

            }
            Log.e(Cat.TAG, "------------------由class对象来实例化具体类的对象，并且修改相关变量的值------------------");
            try {
                Cat c=(Cat) cls.newInstance();
                Field name=cls.getDeclaredField("name");
                Field age=cls.getDeclaredField("age");
                /**设定是否可以打开的开关---就是是否允许你修改变量的值,针对私有变量**/
                name.setAccessible(true);
                /**我们修改的一般来说是已有对象，而非我们通过class对象生成的，是通过new生成的对象的值**/
                Cat cat2=new Cat();
                cat2.setAge(12);
                cat2.setName("cat");
                Log.e(Cat.TAG, "------------------开始修改已有对象的值------------------");
                name.set(cat2,"xulinchao");
                age.set(cat2,24);
                Log.e(Cat.TAG, "name =  "+cat2.getName()+" age = "+cat2.getAge());

                Log.e(Cat.TAG, "------------------调用已有对象的方法------------------");
                try {
                    Method run=cls.getMethod("run",String.class);
                    run.invoke(cat2,"22");
                    Method runff=cls.getDeclaredMethod("runff",String.class,int.class);
                    /**runff 是私有的，所以要打开开关**/
                    runff.setAccessible(true);
                    runff.invoke(cat2,"jerk",15);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }


            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            Log.e(Cat.TAG, "--------------跳过泛型校验，运行时，通过反射给方法加入数值------------");
            List<Integer> list=new ArrayList<Integer>();
            list.add(11);
            Class<?> cls2=list.getClass();
            try {
                Method method=cls2.getMethod("add", Object.class);
                method.invoke(list,"xulinchao");
                Log.e(Cat.TAG,"list 1 = "+list.get(1));

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        });
//        myButton.setOnClickListener((View v)->{
//            Toast.makeText(this,"onclick listener",Toast.LENGTH_SHORT).show();
//        });
//        myButton.setOnLongClickListener((View v)->{
//            Toast.makeText(this,"long click listener",Toast.LENGTH_SHORT).show();
//            return false;
//        });
        /**
         * java &&如果前面为false，那么后面根本不会去执行。
         * **/
//        if(verify()&&false){
//
//        }else {
//            Log.e(MyApplication.getTAG(this.getClass()), "onCreate: " );
//
//        }
    }
//    public boolean verify(){
//        Log.e(MyApplication.getTAG(this.getClass()), "verify: ");
//        return false;
//    }
    public Class getClassObject(){
        /**1.通过class中的forName来获取**/
        Class<?> cls=null;
        try {
             cls=Class.forName("com.xcommander.dispatchtouchevent.Cat");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /**2. 通过实例去获取**/
        Class<?> cls1=new Cat().getClass();

        /**3. 直接通过类来获取**/
        Class<?> cls2=Cat.class;
        Log.e(Cat.TAG, "getClassObject: = "+" cls = "+cls+" cls1 = "+cls1+
        " cls2 = "+cls2);


        if(cls!=null){
            return cls;
        }else{
            return cls2;
        }
    }
    public void Temp(){
        List list=new ArrayList();
        list.add("11");
        list.add(2);
        for (Object o:list
             ) {
            Log.e(MyApplication.getTAG(MainActivity.class), "Temp: "+o);

        }
    }
    public void Ge(Box<? super Integer> box){
        Log.e("xulinchao", "Ge: "+ box.getData());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
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
        return super.dispatchTouchEvent(ev);
    }

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
        }
        return super.onTouchEvent(event);
    }
}
