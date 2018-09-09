package com.howard.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * zkClient操作zookeeper
 * 需要引入org.I0Itec.zkclient
 * Created by Howard Yao on 2018/9/1.
 */
public class ZkClientDemo {
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";

    public static void main(String[] args) throws InterruptedException {
        //getConnection();
        //createNode();
        //deleteNode();
        //getChildrenAndListen();
        dataOpAndExists();
    }

    /**
     * 创建会话
     * @return
     */
    public static void getConnection() {
        ZkClient zkClient = new ZkClient(connectString, 5000);
        System.out.println("zookeeper 连接已经建立...");
    }

    /**
     * 创建节点 & 获取子节点
     * getChildren
     */
    public static void createNode() {
        ZkClient zkClient = new ZkClient(connectString, 5000);
        //创建节点有多个重载方法
        // zkclient支持递归创建，只需将createParent参数设置为true，则会自动创建path前未被创建的节点
        zkClient.createPersistent("/zkclient-test1/a", true);
        zkClient.createPersistent("/zkclient-test1/b", true);
        System.out.println(zkClient.getChildren("/zkclient-test1"));
    }

    /**
     * 删除节点
     */
    public static void deleteNode() {
        ZkClient zkClient = new ZkClient(connectString, 5000);
        // 删除节点有诸如boolean delete(String path)等方法
        // deleteRecursive支持遍历删除子节点
        zkClient.deleteRecursive("/zkclient-test1");
    }

    /**
     * zkclient监听
     * zkclient的监听不是一次性的，一次注册永久有效
     * 可监听节点的新增、删除、变更
     * 可对不存在的节点进行监听
     * @throws InterruptedException
     */
    public static void getChildrenAndListen() throws InterruptedException {
        ZkClient zkClient = new ZkClient(connectString, 5000);
        String path = "/zkclient-test2";
        // 监听节点的新增、删除、变更
        // 不同于javaapi 可监听可节点 此时path节点为创建
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println(parentPath + " 's child change, current: " + currentChilds);
            }
        });

        // 为看到监听打印内容，暂停主线程2s

        // zkclient的节点数据内容不需要自己序列化
        zkClient.createPersistent(path, "zkclient-test2");
        TimeUnit.SECONDS.sleep(2);
        zkClient.createPersistent(path + "/a", "zkclient-test2/a");
        TimeUnit.SECONDS.sleep(2);
        zkClient.delete(path + "/a");
        TimeUnit.SECONDS.sleep(2);
        zkClient.delete(path);
        TimeUnit.SECONDS.sleep(5);
    }

    /**
     * readData 获取节点数据内容
     * writeData 更新节点数据
     * IZkDataListener 对节点数据变化进行监听
     * exists 检测节点是否存在
     * @throws InterruptedException
     */
    public static void dataOpAndExists() throws InterruptedException {
        ZkClient zkClient = new ZkClient(connectString, 5000);
        String path = "/zkclient-test3";
        zkClient.createEphemeral(path, "test3");

        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("node" + dataPath + " changed， now data : " + data);
            }
            // 节点删除
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("node" + dataPath + " deleted");
            }
        });

        System.out.println((String) zkClient.readData(path));
        zkClient.writeData(path, "test!test!");
        TimeUnit.SECONDS.sleep(2);
        System.out.println(zkClient.exists(path));
        zkClient.delete(path);
        TimeUnit.SECONDS.sleep(5);
        System.out.println(zkClient.exists(path));
    }

}
