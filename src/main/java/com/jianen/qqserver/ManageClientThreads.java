package com.jianen.qqserver;

import java.util.HashMap;

/**
 * �������ڹ���ͻ��˵��߳�
 */
public class ManageClientThreads {
    private static HashMap<String ,ServerConnectThread> hm= new HashMap<>();
    //����̶߳���hm����
    public static void  addClientThread(String userid,ServerConnectThread serverConnectThread){
    hm.put(userid,serverConnectThread);
    }

    public  static ServerConnectThread  getThreads(String userid){
        return hm.get(userid);
    }


}
