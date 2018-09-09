package com.howard.zookeeper.javaapi.get;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * 获取子节点回调
 * Created by Howard Yao on 2018/8/26.
 */
public class MyGetChildrenCallBack implements AsyncCallback.Children2Callback {
    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
        System.out.println("code :" + rc + ", param path : " + path + ", ctx:" + ctx);
        System.out.println("get children list: " + children + ", stat: " + stat);
    }
}
