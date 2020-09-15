package com.example.udhyamaapp;

/**
 * Created by vishalverma on 27/01/18.
 */

public class Token {

    public Token(String token, boolean isServerToken) {
        this.token = token;
        this.isServerToken = isServerToken;
    }

    public Token() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isServerToken() {
        return isServerToken;
    }

    public void setServerToken(boolean serverToken) {
        isServerToken = serverToken;
    }

    public String token;
    public boolean isServerToken;
}
