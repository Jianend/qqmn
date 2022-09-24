package com.jianen.qqserver;

import com.jianen.qqcommon.Message;
import com.jianen.qqcommon.MessageType;
import com.jianen.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class QQServer {
    private ServerSocket ss =null;

//ConcurrentHashMap ���Դ������ļ��� û���̰߳�ȫ����
//    private static HashMap<String ,User> validUsers=new HashMap<>();
//    HashMap û�д����̰߳�ȫ ��˶��߳�������ǲ���ȫ��
//    ConcurrentHashMap  ������߳� ���߳�ͬ������ �ڶ��̵߳ļ��� û���̰߳�ȫ��
    private static ConcurrentHashMap<String ,User> validUsers=new ConcurrentHashMap<>();


    static {
        validUsers.put("100",new User("100","123456"));
        validUsers.put("200",new User("200","123456"));
        validUsers.put("300",new User("300","123456"));
        validUsers.put("400",new User("400","123456"));
        validUsers.put("����",new User("����","123456"));
        validUsers.put("500",new User("500","123456"));
    }

    /**
     * ��֤�û�������
     * @param userid  �û�
     * @param passwd  ����
     * @return  ��¼�Ƿ�ɹ�
     */
    private  boolean checkUser(String userid,String passwd){
        User user = validUsers.get(userid);
        if (user==null){
//            userid ������
            System.out.println("�û�������");
            return false;
        }
        if (!user.getPassword().equals(passwd)){
            System.out.println("�������");
            return false;
        }return true;
    }
    public  QQServer() throws IOException, ClassNotFoundException {
        System.out.println("��������9999�˿ڼ���");

        ss = new ServerSocket(9999);
        boolean food=true;

        while (food) {//����ĳ���ͻ������Ӻ��������
            Socket socket = ss.accept();//���û�пͻ��� ����
            ObjectInputStream ois  = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos  = new ObjectOutputStream(socket.getOutputStream());
            User o =(User) ois.readObject();
            //����Message���� ׼���ظ��ͻ���
            Message message = new Message();
            //��֤
            if (checkUser(o.getUserid(),o.getPassword())){
                //�Ϸ�
                message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);//��װ������
                oos.writeObject(message);//�ظ��ͻ���
                //�����̺߳Ϳͻ��˱���ͨ�ţ����̳߳���Socket
                ServerConnectThread serverConnectThread = new ServerConnectThread(socket, o.getUserid());
                serverConnectThread.start();//�����߳�
                //���̶߳��� ����һ�������н��й���
                ManageClientThreads.addClientThread(o.getUserid(),serverConnectThread);


            }else {
                //���Ϸ�
                System.out.println("�û�"+o.getUserid()+o.getPassword());
                message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);//����ʧ��
                oos.writeObject(message);//�ظ��ͻ���
                //�ر�Socket
                socket.close();
            }
            //����������˳�while ˵�����������ڼ��� ��˹ر�ServerSocket

        }
        ss.close();
    }
}
