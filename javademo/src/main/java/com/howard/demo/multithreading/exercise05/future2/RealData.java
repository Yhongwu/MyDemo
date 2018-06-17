package com.howard.demo.multithreading.exercise05.future2;

import java.util.concurrent.Callable;

/**
 * call()方法获取真实数据并返回
 */
public class RealData implements Callable<String>{
    private String data;

    public RealData(String data) {
        this.data = data;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0 ; i < 10; i ++ ) {
            sb.append(data);
            Thread.sleep(200);
        }
        return sb.toString();
    }
}
