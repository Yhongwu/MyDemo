package com.howard.demo.net.test05;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Howard Yao on 2018/8/6.
 */
public class RmiImpl extends UnicastRemoteObject implements Rmi {

    protected RmiImpl() throws RemoteException {
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello RMI : "+name;
    }
}
