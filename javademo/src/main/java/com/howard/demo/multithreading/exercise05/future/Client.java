package com.howard.demo.multithreading.exercise05.future;

public class Client {
    public Data request(final String queryStr) {
        final FutureData future = new FutureData();
        new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                future.setRealData(realData);
            }
        }.start();
        //让线程去获取真实数据
        //主线程先立即返回一个futureData
        return future;
    }
}
