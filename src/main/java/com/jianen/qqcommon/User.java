package com.jianen.qqcommon;

import java.io.Serializable;

/**
 * �û���Ϣ
 *
 */
public class User implements Serializable {
    private static final long serialVersionUID = 4125096758372084309L;
   private String userid ;//�û���
   private String password;//����

    public User(String userid, String password) {
        this.userid = userid;
        this.password = password;
    }

    public User() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
