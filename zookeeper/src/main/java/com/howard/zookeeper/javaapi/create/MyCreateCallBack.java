package com.howard.zookeeper.javaapi.create;

import org.apache.zookeeper.AsyncCallback;

/**
 * 异步回调
 * Created by Howard Yao on 2018/8/26.
 */
public class MyCreateCallBack implements AsyncCallback.StringCallback {

    /**
     *
     * @param rc 服务端响应码 0(ok) 接口调用成功 -4：客户端和服务端连接已断开 -110 指定节点已存在 -112 会话过期
     * @param path 节点路径
     * @param ctx 接口调用时传入的参数
     * @param name 实际在服务端创建的节点名
     */
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println("create path result: " + rc + ", " + path + "," + ctx + ", real path name:" + name);
    }
}
