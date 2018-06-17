package com.howard.demo.multithreading.exercise05.future;

/**
 * 真实数据
 */
public class RealData implements  Data{
    protected final String result;

    public RealData(String data) {
        //真实数据构造很慢
        //用sleep模拟慢
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i ++ ) {
            sb.append(data);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
