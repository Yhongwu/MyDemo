package com.howard.demo.multithreading.exercise05.future;

/**
 * 模拟future模式
 * 当请求耗时的数据时，使用future模式可以先暂时让后台的线程去请求数据，而主线程则不阻塞，可以利用等待的时间去进行其它操作
 * 当需要数据的时候才调用方法去取，这时候如果数据已经取到，则立即返回，没有则会阻塞
 */
public class Main {
    public static void main(String[] args) {
        Client client = new Client();

        Data data = client.request("name");
        System.out.println("请求完成");
        //模拟利用等待的时间进行其它操作
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //获取真实数据
        //如果此时future获取的真实数据操作还未完成，这时候data.getResult()会阻塞
        System.out.println("数据:"+data.getResult());
    }
}
