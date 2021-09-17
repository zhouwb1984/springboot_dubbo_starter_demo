package com.zcode.dubbo.util;

import org.springframework.context.ApplicationContext;

/**
 * Created by zhouwb on 2018/10/15.
 */
public class MyUtil {
    private static ApplicationContext context = null;

    public static void setContext(ApplicationContext context) {
        MyUtil.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
