package com.howard.zookeeper.javaapi.create;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 调用异步方法创建节点
 * Created by Howard Yao on 2018/8/26.
 */
public class CreateAsyncDemo implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static String connectString = "192.168.1.110:2181,192.168.1.111:2181,192.168.1.112:2181";
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        // 构造方法(有多个重载)：
        // public ZooKeeper(String connectString, int sessionTimeout, Watcher watcher, long sessionId, byte[] sessionPasswd, boolean canBeReadOnly)
        // connectString：连接的地址 ip:port,多个地址用逗号分隔。
        // sessionTimeout：会话超时时间
        // watcher： 事件处理器 监听
        // sessionId： 会话ID
        // sessionPasswd： 会话密钥 与sessionId一起传入来建立连接可实现之前的会话复用
        // canBeReadOnly： 标识是否支持read-only模式，默认情况下集群中超过一半的服务出现故障，则不再接受请求。但某些情况下，希望继续提供读服务

        // zookeeper的连接过程是异步的 所以建立连接后，不代表处于已连接状态
        // demo例子 所有异常暂往外抛
        ZooKeeper zooKeeper = new ZooKeeper(connectString, 5000, new CreateAsyncDemo());
        countDownLatch.await();

        // create
        // public void create(String path, byte[] data, List<ACL> acl, CreateMode createMode, StringCallback cb, Object ctx)
        // 该创建节点的方法返回void
        // path: 节点路径
        // data: 数据内容
        // acl：acl权限控制策略
        // createMode：节点类型 持久(PERSISTENT)、顺序持久(PERSISTENT_SEQUENTIAL)、临时(EPHEMERAL)、顺序临时(EPHEMERAL_SEQUENTIAL)
        // cb: 异步回调函数 创建完节点后调用
        // ctx： 用于传递一个对象
        // 不支持递归创建节点，即/z1/z2 则/z1必须已经存在
        zooKeeper.create("/create-test", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new MyCreateCallBack(), "znode1");
        zooKeeper.create("/create-test", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL,new MyCreateCallBack(), "znode2");

        // 异步创建 睡眠一段时间以看到效果
        TimeUnit.SECONDS.sleep(30);

        /**
         * watched event:WatchedEvent state:SyncConnected type:None path:null
         * create path result: 0, /create-test,znode1, real path name:/create-test
         * create path result: 0, /create-test,znode2, real path name:/create-test0000000005
         */
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("watched event:" + watchedEvent);
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            // 确认处于已连接的状态 才让countDownLatch减1 解除等待阻塞
            countDownLatch.countDown();
        }
    }
}
