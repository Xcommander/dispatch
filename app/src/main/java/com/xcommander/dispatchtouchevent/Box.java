package com.xcommander.dispatchtouchevent;

/**
 * Created by xulinchao on 2017/6/28.
 */

public class Box<T> {
    public T data;

    public Box(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
