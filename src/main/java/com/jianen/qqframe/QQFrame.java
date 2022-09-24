package com.jianen.qqframe;

import com.jianen.qqserver.QQServer;

import java.io.IOException;

/**
 * 创建QQServer 对象 启动后台的服务
 */
public class QQFrame {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        new QQServer();
    }

}
