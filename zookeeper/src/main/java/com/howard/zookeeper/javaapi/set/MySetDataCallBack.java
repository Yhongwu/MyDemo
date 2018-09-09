package com.howard.zookeeper.javaapi.set;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

/**
 * Created by Howard Yao on 2018/8/26.
 */
public class MySetDataCallBack implements AsyncCallback.StatCallback {

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        System.out.println(rc);
        if (rc == 0) {
            System.out.println("success");
        }
    }
}
