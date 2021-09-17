package com.zcode.dubbo.callback;

/**
 * Created by zhouwb on 2018/10/15.
 */
public class BeanWraper<T> {
    private T content = null;

    public T get() {
        return content;
    }

    public void set(T content) {
        this.content = content;
    }
}
