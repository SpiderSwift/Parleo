package com.leathersoft.parleo.network.model;

public class LoginResponse {

    String token;
    String error;

    public LoginResponse() {
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
