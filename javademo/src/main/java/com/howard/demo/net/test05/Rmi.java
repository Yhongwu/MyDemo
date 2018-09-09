package com.howard.demo.net.test05;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Howard Yao on 2018/8/6.
 */
public interface Rmi extends Remote{
    public String sayHello(String name) throws RemoteException;
}
