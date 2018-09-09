package com.howard.zookeeper.javaapi.get;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

/**
 * Created by Howard Yao on 2018/8/26.
 */
public class MyGetDataCallBack implements AsyncCallback.DataCallback{
    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        System.out.println("node data change :" + new String(data));
        System.out.println("cVersion: "+ stat.getCversion());
        System.out.println("Mzxid: "+ stat.getMzxid());
        System.out.println("Version: " + stat.getVersion());
        System.out.println("Czxid:" + stat.getCzxid());
    }
}
