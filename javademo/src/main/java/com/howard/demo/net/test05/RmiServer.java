package com.howard.demo.net.test05;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * RMI(remote method invocation) , 可以认为是RPC的java版本
 * RMI使用的是JRMP（Java Remote Messageing Protocol）, JRMP是专门为java定制的通信协议，所以它是纯java的分布式解决方案
 * 如何实现一个RMI程序：
 * 1.	创建远程接口， 并且继承java.rmi.Remote接口
 * 2.	实现远程接口，并且继承：UnicastRemoteObject
 * 3.	创建服务器程序： createRegistry方法注册远程对象
 * 4.	创建客户端程序
 *
 * 由于各种原因 rmi现在用的很少 已经有很多第三方替代品
 * Created by Howard Yao on 2018/8/6.
 */
public class RmiServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        Rmi rmi = new RmiImpl();
        LocateRegistry.createRegistry(8888);
        Naming.bind("rmi://localhost:8888/sayHello",rmi);
        System.out.println("server start success");
    }
}
