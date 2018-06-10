package com.howard.demo.multithreading.exercise04;

/**
 * Created by hongwu on 2017/9/4.
 */
public class Main {
    private static int j = 0;
    public static synchronized void addJ() {
        j ++ ;
        System.out.println(j);
    }
    public static synchronized  void subJ() {
        j --;
        System.out.println(j);
    }

    public static void main(String[] args) {
        for (int i = 0 ; i < 2 ; i ++ ) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            Thread.sleep((long) (Math.random() * 10000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        addJ();
                    }

                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            Thread.sleep((long) (Math.random() * 10000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        subJ();
                    }

                }
            }).start();
        }
    }
}
