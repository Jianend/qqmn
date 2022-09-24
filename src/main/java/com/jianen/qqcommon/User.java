package com.jianen.qqcommon;

import java.io.Serializable;

/**
 * 用户信息
 *
 */
public class User implements Serializable {
    private static final long serialVersionUID = 4125096758372084309L;
   private String userid ;//用户名
   private String password;//密码

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
