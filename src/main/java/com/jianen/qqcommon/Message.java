package com.jianen.qqcommon;

import java.io.Serializable;

/**
 * ��ʾ�ͻ��˺ͷ�������ͨ��ʱ����Ϣ����
 */
public class Message  implements Serializable {
    private static final long serialVersionUID = 4125096758372084309L;
    private String  sender;//������
    private  String  getter;//������
    private  String  content;//��Ϣ����
    private  String  sendTime;//����ʱ��
    private String  mesType ;//��Ϣ�����ڽӿ��ж�����Ϣ����

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
