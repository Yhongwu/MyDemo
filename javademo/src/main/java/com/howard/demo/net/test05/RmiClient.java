package com.howard.demo.net.test05;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Howard Yao on 2018/8/6.
 */
public class RmiClient {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        Rmi rmi = (Rmi) Naming.lookup("rmi://localhost:8888/sayHello");
        System.out.println(rmi);
        System.out.println(rmi.sayHello("howard"));
    }
}
