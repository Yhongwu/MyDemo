package com.howard.demo.multithreading.exercise05.future;

/**
 * FutureData
 * 返回FutureData，而真正的数据其实是RealData
 */
public class FutureData implements Data {

    protected RealData realData = null;
    protected boolean isReady = false;
    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }
    @Override
    public synchronized String getResult()  {
        while (!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result;
    }
}
