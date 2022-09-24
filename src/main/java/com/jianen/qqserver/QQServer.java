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

//ConcurrentHashMap 可以处理并发的集合 没有线程安全问题
//    private static HashMap<String ,User> validUsers=new HashMap<>();
//    HashMap 没有处理线程安全 因此多线程情况下是不安全的
//    ConcurrentHashMap  处理的线程 即线程同步处理 在多线程的集合 没有线程安全的
    private static ConcurrentHashMap<String ,User> validUsers=new ConcurrentHashMap<>();


    static {
        validUsers.put("100",new User("100","123456"));
        validUsers.put("200",new User("200","123456"));
        validUsers.put("300",new User("300","123456"));
        validUsers.put("400",new User("400","123456"));
        validUsers.put("至尊宝",new User("至尊宝","123456"));
        validUsers.put("500",new User("500","123456"));
    }

    /**
     * 验证用户和密码
     * @param userid  用户
     * @param passwd  密码
     * @return  登录是否成功
     */
    private  boolean checkUser(String userid,String passwd){
        User user = validUsers.get(userid);
        if (user==null){
//            userid 不存在
            System.out.println("用户名出错");
            return false;
        }
        if (!user.getPassword().equals(passwd)){
            System.out.println("密码出错");
            return false;
        }return true;
    }
    public  QQServer() throws IOException, ClassNotFoundException {
        System.out.println("服务器在9999端口监听");

        ss = new ServerSocket(9999);
        boolean food=true;

        while (food) {//当和某个客户端连接后继续监听
            Socket socket = ss.accept();//如果没有客户端 阻塞
            ObjectInputStream ois  = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos  = new ObjectOutputStream(socket.getOutputStream());
            User o =(User) ois.readObject();
            //创建Message对象 准备回复客户端
            Message message = new Message();
            //验证
            if (checkUser(o.getUserid(),o.getPassword())){
                //合法
                message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);//封装到对象
                oos.writeObject(message);//回复客户端
                //创建线程和客户端保存通信，该线程持有Socket
                ServerConnectThread serverConnectThread = new ServerConnectThread(socket, o.getUserid());
                serverConnectThread.start();//启动线程
                //吧线程对象 放入一个集合中进行管理
                ManageClientThreads.addClientThread(o.getUserid(),serverConnectThread);


            }else {
                //不合法
                System.out.println("用户"+o.getUserid()+o.getPassword());
                message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);//返回失败
                oos.writeObject(message);//回复客户端
                //关闭Socket
                socket.close();
            }
            //如果服务器退出while 说明服务器不在监听 因此关闭ServerSocket

        }
        ss.close();
    }
}
