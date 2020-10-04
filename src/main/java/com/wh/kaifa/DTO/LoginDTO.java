package com.wh.kaifa.DTO;

/**
 * Created by wanghong on 2020/6/27.
 */
public class LoginDTO {

    private String username;
    private String password;
    private boolean autoLogin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
    }
}