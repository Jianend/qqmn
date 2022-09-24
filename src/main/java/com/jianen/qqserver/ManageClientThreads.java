package com.jianen.qqserver;

import java.util.HashMap;

/**
 * 该类用于管理客户端的线程
 */
public class ManageClientThreads {
    private static HashMap<String ,ServerConnectThread> hm= new HashMap<>();
    //添加线程对象到hm集合
    public static void  addClientThread(String userid,ServerConnectThread serverConnectThread){
    hm.put(userid,serverConnectThread);
    }

    public  static ServerConnectThread  getThreads(String userid){
        return hm.get(userid);
    }


}
