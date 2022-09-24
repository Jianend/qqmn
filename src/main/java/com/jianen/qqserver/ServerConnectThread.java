package com.jianen.qqserver;

import com.jianen.qqcommon.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * 该类的一个对象和某个客户端保持通信
 */
public class ServerConnectThread extends Thread {
    private Socket socket;
    private String userid; //连接服务器的用户id

    public ServerConnectThread(Socket socket, String userid) {
        this.socket = socket;
        this.userid = userid;
    }

    @Override
    public void run() {//这里线程处于run的状态可以发送/接收消息

        while (true) {
            try {
            System.out.println("服务器端和客户端 "+userid+"保持通信");
                ObjectInputStream ois  = new ObjectInputStream(socket.getInputStream());
               Message message= (Message)ois.readObject();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
