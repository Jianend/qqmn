package com.jianen.qqserver;

import com.jianen.qqcommon.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * �����һ�������ĳ���ͻ��˱���ͨ��
 */
public class ServerConnectThread extends Thread {
    private Socket socket;
    private String userid; //���ӷ��������û�id

    public ServerConnectThread(Socket socket, String userid) {
        this.socket = socket;
        this.userid = userid;
    }

    @Override
    public void run() {//�����̴߳���run��״̬���Է���/������Ϣ

        while (true) {
            try {
            System.out.println("�������˺Ϳͻ��� "+userid+"����ͨ��");
                ObjectInputStream ois  = new ObjectInputStream(socket.getInputStream());
               Message message= (Message)ois.readObject();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
